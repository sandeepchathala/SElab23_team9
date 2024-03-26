package com.nitconf.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {
	
	private LoginController loginController = new LoginController();

    @Test
    public void testLogin() {
        ModelAndView modelAndView = loginController.login();
        assertEquals("login.jsp", modelAndView.getViewName());
    }

    @Test
    public void testLogout() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ModelAndView modelAndView = loginController.onLogoutSuccess(request);
        assertEquals("redirect:/login", modelAndView.getViewName());
    }
}
