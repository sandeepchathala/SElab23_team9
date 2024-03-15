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

import jakarta.servlet.http.HttpSession;

/**
 * Controller class for managing PCMember operations.
 */
@RestController
@RequestMapping("/api/pcmember")
public class PCMemberController {

    /** The ID of the current PCMember. */
    Long id;

    /** The current PCMember. */
    public PCMember currentpc;

    /** The repository for PCMember entities. */
    @Autowired
    private PCMemberrepo PCrepo;

    /**
     * Handles GET request for login page.
     * @return ModelAndView object for the login page.
     */
    @GetMapping("/Login")
    public ModelAndView getLogin() {
        ModelAndView mav = new ModelAndView("login.jsp");
        return mav;
    }

    /**
     * Handles POST request for dashboard page.
     * @param email The email of the PCMember.
     * @param password The password of the PCMember.
     * @param model The Model object.
     * @return ModelAndView object for the dashboard page.
     */
    @PostMapping("/Dashboard")
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

    /**
     * Setter method for current PCMember.
     * @param currentpc The current PCMember.
     */
    public void setCurrentpc(PCMember currentpc) {
        this.currentpc = currentpc;
    }

    /**
     * Handles GET request for profile page.
     * @param model The Model object.
     * @return ModelAndView object for the profile page.
     */
    @GetMapping("/Profile")
    public ModelAndView getprofile(Model model) {
       if (currentpc != null) {
            model.addAttribute("name", currentpc.getName());
            model.addAttribute("email", currentpc.getEmail());
            model.addAttribute("password", currentpc.getPassword());
        }
        ModelAndView mav = new ModelAndView("profile.jsp");
        return mav;
    }

    /**
     * Handles GET request for edit profile page.
     * @param model The Model object.
     * @return ModelAndView object for the edit profile page.
     */
    @GetMapping("/EditProfile")
    public ModelAndView editprofile(Model model) {
        if(currentpc != null) {
            model.addAttribute("name",currentpc.getName());
            model.addAttribute("email",currentpc.getEmail());
            model.addAttribute("password",currentpc.getPassword());
        }
        ModelAndView mav = new ModelAndView("editprofile.jsp");
        return mav;
    }

    /**
     * Handles POST request for updating profile.
     * @param data The PCMember data.
     * @param name The name of the PCMember.
     * @param email The email of the PCMember.
     * @param confirmpassword The confirmed password.
     * @param password The password.
     * @param model The Model object.
     * @return ModelAndView object for the profile page.
     */
    @Transactional
    @PostMapping("/UpdateProfile")
    public ModelAndView updateprofile(@RequestParam String name,@RequestParam String email,@RequestParam String confirmpassword,@RequestParam String password,Model model) {
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

    /**
     * Setter method for PCrepo field.
     * @param PCrepo The PCMember repository.
     */
    public void setPCrepo(PCMemberrepo PCrepo) {
        this.PCrepo = PCrepo;
    }

}
