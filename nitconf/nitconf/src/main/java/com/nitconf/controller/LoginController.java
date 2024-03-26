package com.nitconf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for handling login-related requests.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    //private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);

    /**
     * Handles the GET request for "/login" endpoint.
     *
     * @return The view name for the login page.
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login.jsp");
    }

    /**
     * Handles the GET request for "/logout" endpoint.
     * Invalidates the session and redirects to the login page.
     *
     * @param request HttpServletRequest object representing the HTTP request.
     * @return ModelAndView object representing the redirect to the login page.
     */
    @GetMapping("/logout")
    public ModelAndView onLogoutSuccess(HttpServletRequest request){
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}
}
