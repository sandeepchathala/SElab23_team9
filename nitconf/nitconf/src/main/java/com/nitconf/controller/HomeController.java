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
    		model.addAttribute("name",f.getName());
    	//System.out.println("arree ");
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
    		repo.save(data);//System.out.print("success");
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
    @GetMapping(value="/ReviewedPapers")
    public String ReviewedPaper() {
    	//System.out.println("why are you here....");
    	return "reviewedpapers.jsp";
    }
    @GetMapping("/unreviewedpapers")
	public String getallpapers(Model model) {
		List<Files> all = filerepository.findAll();	
		
		for (Files paper : all) {
//	        System.out.println("Paper ID: " + paper.getId());
//	        System.out.println("Paper Title: " + paper.getTitle());
		model.addAttribute("id",paper.getId());
		model.addAttribute("title",paper.getTitle());
		model.addAttribute("tags",paper.getTags());
		model.addAttribute("status",paper.getStatus());
		model.addAttribute("link",paper.getLink());
//	        // Print other fields as needed
	    }
		//long r=1;
		//Collection<Paper> paper=PSrepo.findAll();
		//model.add
		
				//if (t.isPresent()) {
				    //Paper paper = t.get();
				    // Now you can safely call methods on the Paper object
		//model.addAllAttributes(paper);
		
//				    model.addAttribute("id",paper.getId());
//					model.addAttribute("title",paper.getTitle());
//					model.addAttribute("tags",paper.getTags());
//					model.addAttribute("status",paper.getStatus());
//					model.addAttribute("link",paper.getLink());
				    // ... other methods
				//} else {
				    // Handle the case when the Optional is empty
				//}

		
		
		return "unreviewedpapers.jsp";
	}
}
