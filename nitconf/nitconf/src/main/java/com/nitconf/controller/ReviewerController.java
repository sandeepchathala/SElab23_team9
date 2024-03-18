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
                Paper pp = PS_repo.findById(paper_id).orElse(null);
                if (pp!=null) {
                    PS_repo.setstatus(paper_id, 1);
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
                }
            }
        } finally {
            //out.close();
        }
        return new ModelAndView("noreviewersselected.jsp");
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

/**
 * EndPoint for showing addtional reviewers to assign
 * 
 * @param model    the Model object for adding attributes to be exposed to the view
 * @param request  the HttpServletRequest object containing the HTTP request
 * @param response the HttpServletResponse object containing the HTTP response
 * @param paper_id the ID of the paper for which extra reviewers need to be assigned
 * @return         a ModelAndView object representing the view to be rendered
 * @throws ServletException if the request cannot be handled due to a servlet-related issue
 * @throws IOException      if an I/O error occurs while processing the request
 */
@GetMapping("/extrareviewer")
public ModelAndView assignextrat(Model model, HttpServletRequest request, 
        HttpServletResponse response, @RequestParam long paper_id) 
    throws ServletException, IOException {
    // Set content type for the response
    response.setContentType("text/html;charset=UTF-8");
    
    try {
        // Retrieve the paper details from the repository
        Paper p = PS_repo.findById(paper_id);
        
        // Set attributes in the request
        request.setAttribute("extra_reviewers", R_repo.getextrareviewers(p.getId(), p.getTags()));
        request.setAttribute("paper_title", p.getTitle());
        request.setAttribute("paper_id", p.getId());
        
        // Forward the request to the "extrareviewers.jsp" view for rendering
        request.getRequestDispatcher("extrareviewers.jsp").forward(request, response);
    } catch (Exception e) {
        // Handle exceptions if needed
        e.printStackTrace();
    }
    
    // Return a ModelAndView object representing the view to be rendered
    return new ModelAndView("extrareviewers.jsp");
}

    
    /**
 * Endpoint for assigning extra reviewer for a paper.
 * 
 * @param request  the HttpServletRequest object containing the HTTP request
 * @param response the HttpServletResponse object containing the HTTP response
 * @return         a ModelAndView object representing the view to be rendered
 * @throws ServletException if the request cannot be handled due to a servlet-related issue
 * @throws IOException      if an I/O error occurs while processing the request
 */
@Transactional
@PostMapping("/assignextrareviewers")
public ModelAndView assignExtraReviewers(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    try {
        // Retrieve parameters from the request
        String selectedReviewersParam = request.getParameter("selectedReviewers");
        String paper_idparam = request.getParameter("paper_id");
        
        // Check if selectedReviewers parameter is not empty
        if (selectedReviewersParam != null && !selectedReviewersParam.isEmpty()) {
            // Convert comma-separated string of reviewer IDs to a list of Long
            List<String> selectedReviewers = Arrays.asList(selectedReviewersParam.split(","));
            List<Long> reviewerlist = new ArrayList<>();
            for (String s : selectedReviewers)
                reviewerlist.add(Long.valueOf(s));
            
            // Parse paper_id parameter to Long
            Long paper_id = Long.parseLong(paper_idparam);
            System.out.println(selectedReviewers);
            // Retrieve the paper from the repository
            Paper pp = PS_repo.findById(paper_id).orElse(null);
            // If paper exists
            if (pp != null) {
                // Iterate through the list of selected reviewers
                for (Long r_id : reviewerlist) {
                    PaperReviewer pr = new PaperReviewer();
                    pr.setPaper_id(paper_id);
                    pr.setReviewer_id(r_id);
                    pr.setAssigneddate(LocalDate.now());
                    // Save the assignment details to the database
                    PRrepo.save(pr);
                    // Retrieve the reviewer by ID
                    Optional<Reviewer> R = RService.findbyid(r_id);
                    if (R.isPresent()) {
                        Reviewer rr = R.get();
                        // Compose email body
                        String bodyofmail = "You are assigned to a new paper to review. Paper Title = "
                                + pp.getTitle() + ".";
                        // Send email notification to the assigned reviewer
                        senderservice.sendEmail(rr.getEmail(), "New Paper is ready to review", bodyofmail);
                    }
                }
                // Redirect to the list of assigned papers
                return new ModelAndView("redirect:/api/papers/assignedpapers");
            }
        }
    } finally {
        // No action required in the finally block
    }
    // Return ModelAndView indicating that no reviewers were selected
    return new ModelAndView("noreviewersselected.jsp");
}
}
