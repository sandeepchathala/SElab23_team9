package com.nitconf.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/papers")
public class PapersController {
     
	
	@GetMapping("/assignedpapers")
    public ModelAndView getassignedpapers(Model model) {
		ModelAndView m = new ModelAndView("assignedpapers.jsp");
        return m;
    }
	@GetMapping("/reviewedpapers")
    public ModelAndView getreviewedpapersa(Model model) {
		ModelAndView m = new ModelAndView("reviewedpapers.jsp");
        return m;
    }
	@GetMapping("/unreviewedpapers")
    public ModelAndView getunreviewedpapersa(Model model) {
		ModelAndView m = new ModelAndView("unreviewedpapers.jsp");
        return m;
    }
	
	@GetMapping("/unassignedpapers")
    public ModelAndView getreviewedData() {
		ModelAndView model = new ModelAndView("unassignedpapers.jsp");
        return model;
    }
	@GetMapping("/acceptedpapers")
    public ModelAndView getacceptedData() {
		ModelAndView model = new ModelAndView("acceptedpapers.jsp");
        return model;
    }
	@GetMapping("/rejectedpapers")
    public ModelAndView getrejectedData() {
		ModelAndView model = new ModelAndView("rejectedpapers.jsp");
        return model;
    }
	
}
