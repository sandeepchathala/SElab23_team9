package com.nitconf.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

import com.nitconf.model.PCMember;
/**
 * Controller class for managing PCMember operations.
 */
@Controller
@RequestMapping("/api/pcmember")
@SessionAttributes(("user"))
public class PCMemberController {
    
    @Autowired
    private PCMemberrepo PCrepo;    

    @ModelAttribute("user")
    public PCMember user() {
        return new PCMember();
    }
    @GetMapping("/Dashboard")
    public ModelAndView getdashboard(Principal principal, Model model, HttpSession session) {
        PCMember user = PCrepo.findByUsername(PCrepo.findByUsername(principal.getName()).getUsername());
        model.addAttribute("user", user);
        System.out.println(user.getName());
        return new ModelAndView("dashboard.jsp");
    }
    
    @GetMapping("/Profile")
    public ModelAndView getprofile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		PCMember currentpc = PCrepo.findByUsername(username);
    	model.addAttribute("name",currentpc.getName());
    	model.addAttribute("email",currentpc.getUsername());
    	model.addAttribute("phone",currentpc.getPhone());
    	ModelAndView mav = new ModelAndView("profile.jsp");
		return mav;
    }
    @GetMapping("/EditProfile")
    public ModelAndView editprofile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		PCMember currentpc = PCrepo.findByUsername(username);
    	model.addAttribute("name",currentpc.getName());
    	model.addAttribute("email",currentpc.getUsername());
    	model.addAttribute("phone",currentpc.getPhone());
    	model.addAttribute("password",currentpc.getPassword());
    	ModelAndView mav = new ModelAndView("editprofile.jsp");
		return mav;
    }
    
    @Transactional
    @PostMapping("/Updateprofile")
    public ModelAndView updateprofile(HttpSession session,PCMember data,@RequestParam String name,@RequestParam String email,@RequestParam Long phone,@RequestParam String confirmpassword,@RequestParam String password,Model model) {
    	System.out.println("update");String username = SecurityContextHolder.getContext().getAuthentication().getName();
		PCMember currentpc = PCrepo.findByUsername(username);
		System.out.println("update");
    	if(!password.equals(confirmpassword)) {
    		session.setAttribute("confirmpassword_error_msg", "Entered passwords are not matching");
    		return new ModelAndView("redirect:/api/pcmember/EditProfile");
    	}
    	else {
    		id=currentpc.getId();
    		PCrepo.setvalues(id,name,email,phone,password);
    		Optional<PCMember> pp=PCrepo.findById(id);
    		if(pp.isPresent()) {
    			currentpc=pp.get();
    		}
    		return new ModelAndView("redirect:/api/pcmember/Profile");
    	}
    }
	
}

