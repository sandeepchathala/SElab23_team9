/**
 * Controller class for managing operations related to reviewers in the API.
 */
package com.nitconf.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;
import com.nitconf.model.PaperReviewer;
import com.nitconf.model.Reviewer;
import com.nitconf.services.EmailSenderService;
import com.nitconf.services.ReviewerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RestController for handling reviewer-related operations.
 */
@RestController
@RequestMapping("/api/reviewers")
public class ReviewerController {

    @Autowired
    private ReviewerService RService;
    @Autowired
    private Reviewerrepo R_repo;
    @Autowired
    private PaperReviewerrepo PRrepo;
    @Autowired
    private PaperStorerepo PS_repo;
    @Autowired
    private EmailSenderService senderservice;

    /**
     * Endpoint for assigning reviewers to a paper.
     *
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for redirection to the unassigned papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
    @Transactional
    @PostMapping("/assign")
    public ModelAndView assignReviewers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            String selectedReviewersParam = request.getParameter("selectedReviewers");
            String paper_idparam = request.getParameter("paper_id");
            if (selectedReviewersParam != null && !selectedReviewersParam.isEmpty()) {
                // Split the parameter into a list of reviewer IDs
                List<String> selectedReviewers = Arrays.asList(selectedReviewersParam.split(","));
                List<Long> reviewerlist = new ArrayList<>();
                for (String s : selectedReviewers)
                    reviewerlist.add(Long.valueOf(s));
                Long paper_id = Long.parseLong(paper_idparam);
                System.out.println(selectedReviewers);
                Paper pp = new Paper();
                Optional<Paper> p = PS_repo.findById(paper_id);
                if (p.isPresent()) {
                    pp = p.get();
                    System.out.println(pp.getTitle());
                    Long id = paper_id;
                    PS_repo.setstatus(id, 1);
                }
                for (Long r_id : reviewerlist) {
                    PaperReviewer pr = new PaperReviewer();
                    pr.setPaper_id(paper_id);
                    pr.setReviewer_id(r_id);
                    pr.setAssigneddate(LocalDate.now());
                    PRrepo.save(pr);
                    Optional<Reviewer> R = RService.findbyid(r_id);
                    if (R.isPresent()) {
                        Reviewer rr = R.get();
                        String bodyofmail = "You are assigned to a new paper to review. Paper Title = "
                                + pp.getTitle() + ".";
                        senderservice.sendEmail(rr.getEmail(), "New Paper is ready to review", bodyofmail);
                    }
                }
                ModelAndView m = new ModelAndView("redirect:/api/papers/unassignedpapers");
                return m;
            } else {
                ModelAndView m = new ModelAndView("noreviewersselected.jsp");
                return m;
            }
        } finally {
            //out.close();
        }
    }

    /**
     * Endpoint for displaying reviews for a paper.
     *
     * @param paper_id ID of the paper.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the reviews page.
     */
    @GetMapping("/reviews")
    public ModelAndView getReviews(@RequestParam Long paper_id, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Optional<Paper> p = PS_repo.findById(paper_id);
            if (p.isPresent()) {
                request.setAttribute("paper_title", p.get().getTitle());
                List<Object[]> list_pr = (p.get().getStatus() == 3) ? PRrepo.getreview_accept(paper_id)
                        : PRrepo.getreview_reject(paper_id);
                request.setAttribute("reviews", list_pr);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
        return new ModelAndView("reviews.jsp");
    }

    /**
     * Endpoint for displaying review details for a paper.
     *
     * @param paper_id ID of the paper.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the show review page.
     */
    @GetMapping("/showreview")
    public ModelAndView showReview(@RequestParam Long paper_id, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        ModelAndView m = new ModelAndView("showreview.jsp");
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setAttribute("showreviewlist", PRrepo.showreview(paper_id));
            Optional<Paper> p = PS_repo.findById(paper_id);
            if (p.isPresent()) {
                request.setAttribute("paper_title", p.get().getTitle());
                request.setAttribute("paper_id", p.get().getId());
            }
            request.getRequestDispatcher("showreview.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
}
