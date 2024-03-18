/**
 * Controller class for managing filter operations in the API.
 */
package com.nitconf.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;

/**
 * Controller class for managing filter operations in the API.
 */
@Controller
@RequestMapping("/api/filter")
public class FilterController {
    
    @Autowired
    private PaperStorerepo PSrepo;
    @Autowired
    private Reviewerrepo Rrepo;
    
    /**
     * Process request to retrieve reviewers by paper tags.
     *
     * @param model Model object to hold attributes.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @param paperid ID of the paper.
     * @return ModelAndView object for the reviewertags.jsp page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
    @GetMapping("/reviewertags")
    public ModelAndView processRequest(Model model, HttpServletRequest request, 
            HttpServletResponse response, @RequestParam long paperid) 
        throws ServletException, IOException { 
        ModelAndView m = new ModelAndView("reviewertags.jsp");
        response.setContentType("text/html;charset=UTF-8");
        try {
            Paper p = PSrepo.findById(paperid);
            request.setAttribute("tag_reviewers", Rrepo.getReviewersbytag(p.getTags()));
            request.setAttribute("paper_title", p.getTitle());
            request.setAttribute("paper_id", p.getId());
            request.getRequestDispatcher("reviewertags.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle exceptions if needed
            e.printStackTrace();
        }
        return m;
    }
    
    /**
     * Filter papers by tag.
     *
     * @param selectedTag Selected tag to filter papers.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the bytags.jsp page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
    @PostMapping("/bytag")
    public ModelAndView filterPapers(@RequestParam String selectedTag, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        ModelAndView m = new ModelAndView("bytags.jsp");
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setAttribute("tag_papers", PSrepo.getpapersbytags(selectedTag));
            request.setAttribute("alltags", PSrepo.getalltags());
            System.out.println(selectedTag);
            request.getRequestDispatcher("bytags.jsp").forward(request, response);       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
}
