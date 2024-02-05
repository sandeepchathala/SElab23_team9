package com.nitconf.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.PCMember;

@RestController
@RequestMapping(value="/api/pcmember")
public class PCMemberController {
    Long id;
    public PCMember currentpc;
    @Autowired
    private PCMemberrepo PCrepo;
//	@Autowired
//	public PCMember currentpc;
    

//	@GetMapping("/Logout")
//	public 
	@GetMapping(value="/Login")
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView("login.jsp");
		return mav;
	}
    @PostMapping(value="/Dashboard")
    public ModelAndView getdashboard(@RequestParam String email,@RequestParam String password,Model model) {
    	if (PCrepo.existsByEmailAndPassword(email,password)) {
    		currentpc=PCrepo.findByEmail(email);
    		model.addAttribute("name",currentpc.getName());
    	ModelAndView mav = new ModelAndView("dashboard.jsp");
		return mav;
    	}
    	else {
    		ModelAndView mav = new ModelAndView("loginerror.jsp");
    		return mav;
    		}
    }
    
	@GetMapping(value="/Profile")
    public ModelAndView getprofile(Model model) {
    	model.addAttribute("name",currentpc.getName());
    	model.addAttribute("email",currentpc.getEmail());
    	model.addAttribute("password",currentpc.getPassword());
    	ModelAndView mav = new ModelAndView("profile.jsp");
		return mav;
    }
    @GetMapping(value="/EditProfile")
    public ModelAndView editprofile(Model model) {
    	model.addAttribute("name",currentpc.getName());
    	model.addAttribute("email",currentpc.getEmail());
    	model.addAttribute("password",currentpc.getPassword());
    	ModelAndView mav = new ModelAndView("editprofile.jsp");
		return mav;
    }
    @Transactional
    @PostMapping(value="/UpdateProfile")
    public ModelAndView updateprofile(PCMember data,@RequestParam String name,@RequestParam String email,@RequestParam String confirmpassword,@RequestParam String password,Model model) {
    	if(!password.equals(confirmpassword)) {
    		ModelAndView mav = new ModelAndView("confirmpassworderror.jsp");
    		return mav;
    	}
    	else {
    		id=currentpc.getId();
    		PCrepo.setvalues(id,name,email,password);
    		Optional<PCMember> pp=PCrepo.findById(id);
    		if(pp.isPresent()) {
    			currentpc=pp.get();
    		}
    		ModelAndView mav = new ModelAndView("redirect:/api/pcmember/Profile");
    		return mav;
    	}
    }
	
}
