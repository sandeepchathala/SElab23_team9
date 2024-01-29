package com.nitconf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitconf.model.PCMember;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping(value="/")
	public String getLandingpage() {
		return "landingpage.jsp";
	}
	@GetMapping(value="/Login")
	public String getLogin() {
		return "login.jsp";
	}
//    @PostMapping(value="/Dashboard")
//    public String getdashboard(@RequestParam String email,@RequestParam String password,Model model) {
//    	if (repo.existsByEmailAndPassword(email,password)) {
//    		f=repo.findByEmail(email);
//    		model.addAttribute("name",f.getName());
//    	System.out.println("arree ");
//    		return "dashboard.jsp";}
//    	else {
//    		return "Loginerror.jsp";}
//    }
}
