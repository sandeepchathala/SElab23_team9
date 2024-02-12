/**
 * The FilterController class handles filtering operations for papers.
 */
package com.nitconf.controller;

import com.nitconf.model.Paper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * RestController for filtering papers.
 */
@RestController
@RequestMapping("/api/filter")
public class FilterController {
    
    /** The paper store repository. */
    @Autowired
    PaperStorerepo PSrepo;
    
    /** The tag to filter papers by. */
    public String tag;
    
    /**
     * Processes the request for filtering papers by reviewer tags.
     * 
     * @param model the model
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @param paperid the ID of the paper to filter by
     * @return the ModelAndView object
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @GetMapping("/reviewertags")
    public ModelAndView processRequest(Model model, HttpServletRequest request, HttpServletResponse response,
            @RequestParam long paperid) throws ServletException, IOException {
        ModelAndView m = new ModelAndView("reviewertags.jsp");
        response.setContentType("text/html;charset=UTF-8");
        try {
            Paper p = PSrepo.findById(paperid);
            System.out.println("filterbytag " + p.getTitle());
            request.setAttribute("paper_tag", p.getTags());
            request.setAttribute("paper_title", p.getTitle());
            request.setAttribute("paper_id", p.getId());
            RequestDispatcher dispatcher = request.getRequestDispatcher("reviewertags.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
    
    /**
     * Filters papers based on selected tags.
     * 
     * @param selectedTag the selected tag to filter papers by
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the ModelAndView object
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @PostMapping("/bytags")
    public ModelAndView filterPapers(@RequestParam String selectedTag, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        ModelAndView m = new ModelAndView("bytags.jsp");
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setAttribute("selectedTag", selectedTag);
            System.out.println(selectedTag);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bytags.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
}
