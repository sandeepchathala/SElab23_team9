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
@RestController
@RequestMapping("/api/pcmember")
@SessionAttributes(("user"))
public class PCMemberController {

    @Autowired
    private PCMemberrepo PCrepo; 
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    /**
     * Creates a new PCMember object to be used as a session attribute.
     *
     * @return PCMember object.
     */
    @ModelAttribute("user")
    public PCMember user() {
        return new PCMember();
    }
    
    /**
     * Retrieves the dashboard view for the PCMember.
     *
     * @param principal Principal object representing the currently authenticated user.
     * @param model Model object to hold attributes for the view.
     * @param session HttpSession object for managing session attributes.
     * @return ModelAndView object representing the dashboard view.
     */
    @GetMapping("/Dashboard")
    public ModelAndView getdashboard(Principal principal, Model model, HttpSession session) {
        PCMember user = PCrepo.findByUsername(PCrepo.findByUsername(principal.getName()).getUsername());
        model.addAttribute("user", user);
        System.out.println(user.getName());
        return new ModelAndView("dashboard.jsp");
    }
    
    /**
     * Retrieves the profile view for the PCMember.
     *
     * @param model Model object to hold attributes for the view.
     * @return ModelAndView object representing the profile view.
     */
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
    
    /**
     * Retrieves the edit profile view for the PCMember.
     *
     * @param model Model object to hold attributes for the view.
     * @return ModelAndView object representing the edit profile view.
     */
    @GetMapping("/EditProfile")
    public ModelAndView editprofile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		PCMember currentpc = PCrepo.findByUsername(username);
    	model.addAttribute("name",currentpc.getName());
    	model.addAttribute("email",currentpc.getUsername());
    	model.addAttribute("phone",currentpc.getPhone());
    	ModelAndView mav = new ModelAndView("editprofile.jsp");
		return mav;
    }
    
    /**
     * Updates the profile of the PCMember.
     *
     * @param session HttpSession object for managing session attributes.
     * @param data PCMember object containing updated profile data.
     * @param name Updated name of the PCMember.
     * @param email Updated email of the PCMember.
     * @param phone Updated phone number of the PCMember.
     * @param confirmpassword Confirmation password entered by the PCMember for password update.
     * @param password New password entered by the PCMember.
     * @param model Model object to hold attributes for the view.
     * @return ModelAndView object representing the redirect to the profile view.
     */
    @Transactional
    @PostMapping("/Updateprofile")
    public ModelAndView updateprofile(HttpSession session,PCMember data,@RequestParam String name,@RequestParam String email,@RequestParam Long phone,@RequestParam String confirmpassword,@RequestParam String password,Model model) {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		PCMember currentpc = PCrepo.findByUsername(username);
    	if(!password.equals(confirmpassword)) {
    		session.setAttribute("confirmpassword_error_msg", "Entered passwords are not matching");
    		return new ModelAndView("redirect:/api/pcmember/EditProfile");
    	}
    	else {
            // Update email address in SecurityContext holder
            Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(email, currentAuthentication.getCredentials(), currentAuthentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            
    		PCrepo.setvalues(currentpc.getId(),name,email,phone,passwordEncoder.encode(password));
    		return new ModelAndView("redirect:/api/pcmember/Profile");
    	}
    }
	
}
