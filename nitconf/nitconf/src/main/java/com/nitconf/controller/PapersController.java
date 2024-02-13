/**
 * Controller class for handling operations related to papers in the API.
 */
package com.nitconf.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * RestController for handling paper-related operations.
 */
@RestController
@RequestMapping("/api/papers")
public class PapersController {

    /**
     * Endpoint for accessing the assigned papers page.
     *
     * @param model Model object to hold attributes for the assigned papers page.
     * @return ModelAndView object for the assigned papers page.
     */
    @GetMapping("/assignedpapers")
    public Object getassignedpapers(Model model) {
        return new ModelAndView("assignedpapers.jsp");
    }

    /**
     * Endpoint for accessing the reviewed papers page.
     *
     * @param model Model object to hold attributes for the reviewed papers page.
     * @return ModelAndView object for the reviewed papers page.
     */
    @GetMapping("/reviewedpapers")
    public Object getreviewedpapersa(Model model) {
        return new ModelAndView("reviewedpapers.jsp");
    }

    /**
     * Endpoint for accessing the unreviewed papers page.
     *
     * @param model Model object to hold attributes for the unreviewed papers page.
     * @return ModelAndView object for the unreviewed papers page.
     */
    @GetMapping("/unreviewedpapers")
    public Object getunreviewedpapersa(Model model) {
        return new ModelAndView("unreviewedpapers.jsp");
    }

    /**
     * Endpoint for accessing the unassigned papers page.
     *
     * @return ModelAndView object for the unassigned papers page.
     */
    @GetMapping("/unassignedpapers")
    public Object getreviewedData() {
        return new ModelAndView("unassignedpapers.jsp");
    }

    /**
     * Endpoint for accessing the accepted papers page.
     *
     * @return ModelAndView object for the accepted papers page.
     */
    @GetMapping("/acceptedpapers")
    public Object getacceptedData() {
        return new ModelAndView("acceptedpapers.jsp");
    }

    /**
     * Endpoint for accessing the rejected papers page.
     *
     * @return ModelAndView object for the rejected papers page.
     */
    @GetMapping("/rejectedpapers")
    public Object getrejectedData() {
        return new ModelAndView("rejectedpapers.jsp");
    }
}
