package com.nitconf.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.PCMember;
import com.nitconf.model.Paper;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


//@Controller
@RestController
@RequestMapping(value="/api")
public class HomeController {
	Long id;
	@Autowired
	PCMemberrepo repo;
	@Autowired
	PaperStorerepo PSrepo;

//	@GetMapping("/hh")
//	ResponseEntity<String> hello() {
//	    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
//	}
	
	@GetMapping(value="/")
	public ModelAndView getLandingPage() {
		ModelAndView mav = new ModelAndView("landing.jsp");
		return mav;
	}
    
}