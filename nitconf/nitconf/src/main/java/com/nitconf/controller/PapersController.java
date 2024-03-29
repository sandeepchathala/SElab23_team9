/**
 * Controller class for handling operations related to papers in the API.
 */
package com.nitconf.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.nitconf.model.Paper;

/**
 * RestController for handling paper-related operations.
 */
@RestController
@RequestMapping("/api/papers")
public class PapersController {

    @Autowired
    private PaperStorerepo PSrepo;
    /**
     * Endpoint for accessing the assigned papers page.
     *
     * @param model Model object to hold attributes for the assigned papers page.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the assigned papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
	@GetMapping("/assignedpapers")
    public ModelAndView getassignedpapers(Model model,HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try{
			List<Paper> assigned_papers=PSrepo.findByStatus(2);
		request.setAttribute("assigned_papers",assigned_papers);
		request.getRequestDispatcher("assignedpapers.jsp").forward(request, response);
		}
		catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
		return new ModelAndView("assignedpapers.jsp");
    }

    /**
     * Endpoint for accessing the reviewed papers page.
     *
     * @param model Model object to hold attributes for the reviewed papers page.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the reviewed papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
	@GetMapping("/reviewedpapers")
    public ModelAndView getreviewedpapersa(Model model,HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try{
			List<Paper> reviewed_papers=PSrepo.findByStatus(2);
		request.setAttribute("reviewed_papers",reviewed_papers);
		request.getRequestDispatcher("reviewedpapers.jsp").forward(request, response);
		}
		catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
		return new ModelAndView("reviewedpapers.jsp");
    }

    /**
     * Endpoint for accessing the unreviewed papers page.
     *
     * @param model Model object to hold attributes for the unreviewed papers page.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the unreviewed papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
	@GetMapping("/unreviewedpapers")
    public ModelAndView getunreviewedpapersa(Model model,HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try{
			List<Paper> unreviewed_papers=PSrepo.findByStatus(1);
		request.setAttribute("unreviewed_papers",unreviewed_papers);
		request.getRequestDispatcher("unreviewedpapers.jsp").forward(request, response);
		}
		catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
		return new ModelAndView("unreviewedpapers.jsp");
    }

    /**
     * Endpoint for accessing the unassigned papers page.
     *
     * @param model Model object to hold attributes for the unassigned papers page.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the unassigned papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
    @GetMapping("/unassignedpapers")
    public ModelAndView getUnassignedPapers(Model model, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        // Setting response content type
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Paper> unassignedPapers = PSrepo.findByStatus(0);
            request.setAttribute("unassigned_papers", unassignedPapers);
            request.setAttribute("alltags", PSrepo.getalltags());
            request.getRequestDispatcher("unassignedpapers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
        System.out.println("unassigned");
        return new ModelAndView("unassignedpapers.jsp");
    }

    /**
     * Endpoint for accessing the accepted papers page.
     *
     * @param model Model object to hold attributes for the accepted papers page.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the accepted papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
    @GetMapping("/acceptedpapers")
    public ModelAndView getAcceptedPapers(Model model, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        // Setting response content type
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Paper> acceptedPapers = PSrepo.findByStatus(3);
            request.setAttribute("accepted_papers", acceptedPapers);
            request.getRequestDispatcher("acceptedpapers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
        return new ModelAndView("acceptedpapers.jsp");
    }

    /**
     * Endpoint for accessing the rejected papers page.
     *
     * @param model Model object to hold attributes for the rejected papers page.
     * @param request HttpServletRequest object for handling HTTP request.
     * @param response HttpServletResponse object for handling HTTP response.
     * @return ModelAndView object for the rejected papers page.
     * @throws ServletException if there is a servlet related exception.
     * @throws IOException if there is an I/O related exception.
     */
    @GetMapping("/rejectedpapers")
    public ModelAndView getRejectedPapers(Model model, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        // Setting response content type
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Paper> rejectedPapers = PSrepo.findByStatus(4);
            request.setAttribute("rejected_papers", rejectedPapers);
            request.getRequestDispatcher("rejectedpapers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }
        return new ModelAndView("rejectedpapers.jsp");
    }
}
