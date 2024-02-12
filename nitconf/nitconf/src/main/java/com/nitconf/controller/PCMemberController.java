/**
 * Controller class for handling the operations related to PCMembers in the API.
 */
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

/**
 * RestController for handling PCMember-related operations.
 */
@RestController
@RequestMapping(value="/api/pcmember")
public class PCMemberController {

    Long id;
    public PCMember currentpc;
    @Autowired
    private PCMemberrepo PCrepo;

    /**
     * Endpoint for accessing the login page.
     *
     * @return ModelAndView object for the login page.
     */
    @GetMapping(value="/Login")
    public ModelAndView getLogin() {
        return new ModelAndView("login.jsp");
    }

    /**
     * Method to handle the login validation process
     * redirect to the dashboard if entered details are valid or else
     * displays an error page.
     *
     * @param email PCMember's email
     * @param password PCMember's password
     * @return ModelAndView object for the dashboard or login error page.
     */
    private ModelAndView fun_dashboard(String email, String password) {
        if (PCrepo.existsByEmailAndPassword(email, password)) {
            currentpc = PCrepo.findByEmail(email);
            return new ModelAndView("dashboard.jsp");
        }
        return new ModelAndView("loginerror.jsp");
    }

    /**
     * Endpoint for accessing the dashboard.
     * It has a private method(fun_dashboard) which takes input two strings ( email and password )
     * and handle the login validation process
     * redirect to the dashboard if entered details are valid or else
     * displays an error page
     * 
     * @param email PCMember's email
     * @param password PCMember's password.
     * @return ModelAndView object for the dashboard or login error page.
     */
    @PostMapping(value="/Dashboard")
    public ModelAndView getdashboard(@RequestParam String email, @RequestParam String password) {
        return fun_dashboard(email, password);
    }

    /**
     * Method to display the basic details of the current login PC member in the profile page.
     * @param model Model object to hold attributes for the profile page.
     * @return ModelAndView object for the profile page.
     */
    private ModelAndView fun_profile(Model model) {
        model.addAttribute("name", currentpc.getName());
        model.addAttribute("email", currentpc.getEmail());
        model.addAttribute("password", currentpc.getPassword());
        return new ModelAndView("profile.jsp");
    }

    /**
     * Endpoint for accessing the basic details of the current login PC member in the profile page.
     *
     * @param model Model object to hold attributes for the profile page.
     * It has a private method(fun_profile) which takes input Model and displays all the basic details of the pcmember and
     * @return ModelAndView object for the profile page.
     */
    @GetMapping(value="/Profile")
    public ModelAndView getprofile(Model model) {
        return fun_profile(model);
    }

    /**
     * Method to display the edit profile page.
     *
     * @param model Model object to hold attributes for the edit profile page.
     * @return ModelAndView object for the edit profile page.
     */
    private ModelAndView fun_editprofile(Model model) {
        model.addAttribute("name", currentpc.getName());
        model.addAttribute("email", currentpc.getEmail());
        model.addAttribute("password", currentpc.getPassword());
        return new ModelAndView("editprofile.jsp");
    }

    /**
     * Endpoint for accessing the edit profile page.
     *
     * @param model Model object to hold attributes for the edit profile page.
     * It has a private method (fun_editprofile) which takes input Model and
     * @return ModelAndView object for the edit profile page containing all the details provided by the PC member.
     */
    @GetMapping(value="/EditProfile")
    public ModelAndView editprofile(Model model) {
        return fun_editprofile(model);
    }

    /**
     * Method to update the PCMember's profile information.
     * 
     * @param data PCMember object containing current data
     * @param name New name for the PCMember
     * @param email New email for the PCMember
     * @param confirmpassword Confirmation password for the update
     * @param password New password for the PCMember
     * @return ModelAndView object for redirecting to the profile page or confirmation password error page.
     */
    private ModelAndView fun_updateprofile(PCMember data, @RequestParam String name, @RequestParam String email,
                                           @RequestParam String confirmpassword, @RequestParam String password) {
        if (!password.equals(confirmpassword)) {
            return new ModelAndView("confirmpassworderror.jsp");
        }
        id = currentpc.getId();
        PCrepo.setvalues(id, name, email, password);
        Optional<PCMember> pp = PCrepo.findById(id);
        if (pp.isPresent()) {
            currentpc = pp.get();
        }
        return new ModelAndView("redirect:/api/pcmember/Profile");
    }

    /**
     * Endpoint for updating the PCMember's profile information.
     * It has a private method(fun_updateprofile)  which takes input same as udateprofile method
     * and validadates if confirmpassword and password or equal or not. If equals it updates the details in the database and
     * redirects to the profile page containing updated details. else returns confirmation password error page
     * 
     * @param data PCMember object containing current data
     * @param name New name for the PCMember
     * @param email New email for the PCMember
     * @param confirmpassword Confirmation password for the update
     * @param password New password for the PCMember
     * @return ModelAndView object for redirecting to the profile page or confirmation password error page.
     */
    @Transactional
    @PostMapping(value="/UpdateProfile")
    public ModelAndView updateprofile(PCMember data, @RequestParam String name, @RequestParam String email,
                                      @RequestParam String confirmpassword, @RequestParam String password) {
        return fun_updateprofile(data, name, email, confirmpassword, password);
    }
}
