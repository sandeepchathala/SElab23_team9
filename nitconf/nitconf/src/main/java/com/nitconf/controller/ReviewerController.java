/**
 * Controller class for handling operations related to reviewers in the API.
 */
package com.nitconf.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import jakarta.servlet.RequestDispatcher;
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
    private PaperReviewerrepo PRrepo;
    @Autowired
    private PaperStorerepo PS_repo;
    @Autowired
    private EmailSenderService senderservice;

    /**
     * Private method to handle the assignment of reviewers to a paper.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return ModelAndView object for redirecting to the unassigned papers page or displaying an error message.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private ModelAndView fun_assignReviewers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String selectedReviewersParam = request.getParameter("selectedReviewers");
            String paper_idparam = request.getParameter("paper_id");
            if (selectedReviewersParam != null && !selectedReviewersParam.isEmpty()) {
                // Split the parameter into a list of reviewer IDs
                List<String> selectedReviewers = Arrays.asList(selectedReviewersParam.split(","));
                List<Long> reviewerlist = new ArrayList<>();
                for (String s : selectedReviewers) reviewerlist.add(Long.valueOf(s));
                Long paper_id = Long.parseLong(paper_idparam);
                Paper pp = new Paper();
                Optional<Paper> p = PS_repo.findById(paper_id);
                if (p.isPresent()) {
                    pp = p.get();
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
                        String bodyofmail = "You are assigned to a new paper to review. Paper Title = " + pp.getTitle() + ".";
                        senderservice.sendEmail(rr.getEmail(), "New Paper is ready to review", bodyofmail);
                    }
                }
                return new ModelAndView("redirect:/api/papers/unassignedpapers");
            } else {
                return new ModelAndView("noreviewersselected.jsp");
            }
        } finally {
            // Cleanup resources if needed
        }
    }

    /**
     * Endpoint for assigning reviewers to a paper.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return ModelAndView object for redirecting to the unassigned papers page or displaying an error message.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Transactional
    @PostMapping("/assign")
    public Object assignReviewers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return fun_assignReviewers(request, response);
    }
	 
	 /**
	     * Private method to display the reviews of a selected paper.
	     *
	     * @param paper_id Paper ID for which the review details are displayed.
	     * @param request  HttpServletRequest object
	     * @param response HttpServletResponse object
	     * @return ModelAndView object for displaying the review details page.
	     * @throws ServletException If a servlet-specific error occurs.
	     * @throws IOException      If an I/O error occurs.
	     */
	    private ModelAndView fun_showreview(Long paper_id, HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        try {
	            request.setAttribute("paper_id", paper_id);
	            Optional<Paper> p = PS_repo.findById(paper_id);
	            if (p.isPresent()) {
	                Paper pp = p.get();
	                String paper_title = pp.getTitle();
	                request.setAttribute("paper_title", paper_title);
	            }
	            RequestDispatcher dispatcher = request.getRequestDispatcher("showreview.jsp");
	            dispatcher.forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return new ModelAndView("showreview.jsp");
	    }

	    /**
	     * Endpoint for showing the reviews for a selected paper.
	     * It takes paper_id as input and other inputs as HttpServletRequest request and HttpServletResponse response
	     * and displays all the reviews of selected paper
	     *
	     * @param paper_id Paper ID for which the review details are displayed.
	     * @param request  HttpServletRequest object
	     * @param response HttpServletResponse object
	     * @return ModelAndView object for displaying the review details page.
	     * @throws ServletException If a servlet-specific error occurs.
	     * @throws IOException      If an I/O error occurs.
	     */
	    @GetMapping("/showreview")
	    public Object showreview(@RequestParam Long paper_id, HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        return fun_showreview(paper_id, request, response);
	    }
	 
}

