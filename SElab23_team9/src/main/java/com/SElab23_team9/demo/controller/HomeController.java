package com.SElab23_team9.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SElab23_team9.demo.model.PCMember;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	PCMember f;
	int tt;
	@Autowired
	PCMemberrepo repo;
	@GetMapping(value="/")
	public String getLandingPage() {
		return "landingpage.jsp";
	}
	@GetMapping(value="/Login")
	public String getLogin() {
		return "login.jsp";
	}
    @PostMapping(value="/Dashboard")
    public String getdashboard(@RequestParam String email,@RequestParam String password,Model model) {
    	if (repo.existsByEmailAndPassword(email,password)) {
    		f=repo.findByEmail(email);
    		return "dashboard.jsp";}
    	else {
    		return "Loginerror.jsp";}
    }
    @GetMapping(value="/Register")
	public String getRegister() {
		return "register.jsp";
	}
    @PostMapping(value="/Success")
	public String Register(PCMember data,@RequestParam String email,@RequestParam String confirmpassword,@RequestParam String password,Model model) {
    	if(!password.equals(confirmpassword)) {
    		return "confirmpassworderror.jsp";
    	}
    	else if(repo.existsByEmail(email)) {
    		return "emailexisterror.jsp";
    	}
    	else {
    		repo.save(data);
    		return "success.jsp";
    	}
	}
    @GetMapping(value="/Profile")
    //import /tester/src/main/webapp/dashboard.jsp;
    public String getprofile(Model model) {
    	model.addAttribute("name",f.getName());
    	model.addAttribute("phonenumber",f.getPhonenumber());
    	model.addAttribute("email",f.getEmail());
    	model.addAttribute("password",f.getPassword());
    	return "profile.jsp";
    }
    @GetMapping(value="/EditProfile")
    public String editprofile(Model model) {
    	model.addAttribute("name",f.getName());
    	model.addAttribute("phonenumber",f.getPhonenumber());
    	model.addAttribute("email",f.getEmail());
    	model.addAttribute("password",f.getPassword());
    	return "editprofile.jsp";
    }
    @PostMapping(value="/UpdateProfile")
    public String updateprofile(PCMember data,@RequestParam String email,@RequestParam String confirmpassword,@RequestParam String password,Model model) {
    	if(!password.equals(confirmpassword)) {
    		return "confirmpassworderror.jsp";
    	}
//    	else if(repo.existsByEmail(email)) {
//    		return "emailexisterror.jsp";
//    	}
    	else {
    		tt=f.getId();
    		repo.deleteById(tt);
    		f=repo.save(data);
//    		f.setName(data.getName());
//    		f.setPhonenumber(data.getPhonenumber());
//    		f.setEmail(data.getEmail());
//    		f.setPassword(data.getPassword());
//    		model.addAttribute("name",f.getName());
//        	model.addAttribute("phonenumber",f.getPhonenumber());
//        	model.addAttribute("email",f.getEmail());
//        	model.addAttribute("password",f.getPassword());
    		System.out.println(f.getName()+" "+f.getEmail()+" "+f.getPhonenumber()+" "+data.getPassword());
    	    return "redirect:/Profile";
    	}
    }
}