/**
 * Controller class for handling home-related operations in the API.
 */
package com.nitconf.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.PCMember;
import com.nitconf.model.Paper;

/**
 * RestController for handling home-related operations.
 */
@RestController
@RequestMapping(value="/api")
public class HomeController {

    /**
     * Endpoint for accessing the landing page.
     *
     * @return ModelAndView object for the landing page.
     */
    @GetMapping(value="/")
    public ModelAndView getLandingPage() {
        ModelAndView mav = new ModelAndView("landing.jsp");
        return mav;
    }
    
    // The following may be later used for clarification
//    /**
//     * Example endpoint for testing purposes.
//     *
//     * @return ResponseEntity object with a "Hello World!" message and HTTP status OK.
//     */
//    @GetMapping("/hh")
//    ResponseEntity<String> hello() {
//        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
//    }

}
