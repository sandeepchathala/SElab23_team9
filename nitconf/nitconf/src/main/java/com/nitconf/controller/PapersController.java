/**
 * The PapersController class handles requests related to papers.
 */
package com.nitconf.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * RestController for handling paper-related requests.
 */
@RestController
@RequestMapping("/api/papers")
public class PapersController {
    
    /**
     * Retrieves assigned papers.
     * 
     * @param model the model
     * @return the ModelAndView object for assigned papers view
     */
    @GetMapping("/assignedpapers")
    public ModelAndView getAssignedPapers(Model model) {
        ModelAndView m = new ModelAndView("assignedpapers.jsp");
        return m;
    }
    
    /**
     * Retrieves reviewed papers.
     * 
     * @param model the model
     * @return the ModelAndView object for reviewed papers view
     */
    @GetMapping("/reviewedpapers")
    public ModelAndView getReviewedPapers(Model model) {
        ModelAndView m = new ModelAndView("reviewedpapers.jsp");
        return m;
    }
    
    /**
     * Retrieves unreviewed papers.
     * 
     * @param model the model
     * @return the ModelAndView object for unreviewed papers view
     */
    @GetMapping("/unreviewedpapers")
    public ModelAndView getUnreviewedPapers(Model model) {
        ModelAndView m = new ModelAndView("unreviewedpapers.jsp");
        return m;
    }
    
    /**
     * Retrieves unassigned papers.
     * 
     * @return the ModelAndView object for unassigned papers view
     */
    @GetMapping("/unassignedpapers")
    public ModelAndView getUnassignedPapers() {
        ModelAndView model = new ModelAndView("unassignedpapers.jsp");
        return model;
    }
    
    /**
     * Retrieves accepted papers.
     * 
     * @return the ModelAndView object for accepted papers view
     */
    @GetMapping("/acceptedpapers")
    public ModelAndView getAcceptedPapers() {
        ModelAndView model = new ModelAndView("acceptedpapers.jsp");
        return model;
    }
    
    /**
     * Retrieves rejected papers.
     * 
     * @return the ModelAndView object for rejected papers view
     */
    @GetMapping("/rejectedpapers")
    public ModelAndView getRejectedPapers() {
        ModelAndView model = new ModelAndView("rejectedpapers.jsp");
        return model;
    }
}
