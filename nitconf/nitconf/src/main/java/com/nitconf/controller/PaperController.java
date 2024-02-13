/**
 * The PaperController class handles requests related to papers.
 * This controller manages endpoints related to storing papers and displaying upload forms.
 */
package com.nitconf.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;

/**
 * RestController for handling paper-related requests.
 * This class manages endpoints related to papers and interacts with the PaperStorerepo repository.
 */
@RestController
@RequestMapping(value="/Paper")
public class PaperController {
    
    /** The paper store repository. */
    @Autowired
    PaperStorerepo PSrepo;
    
    /**
     * Stores a paper.
     * 
     * @param title the title of the paper
     * @param tags the tags associated with the paper
     * @param link the link to the paper
     * @return the ModelAndView object for success view
     * This method accepts parameters representing the title, tags, and link of a paper.
     * It creates a new Paper object with the provided details, sets its status to 0 (indicating unprocessed),
     * sets the upload date to the current date, and saves it using the PaperStorerepo repository.
     * Finally, it returns a ModelAndView object for the success view.
     */
    @PostMapping("/StorePaper")
    public Object storePaper(
            @RequestParam String title,
            @RequestParam String tags,
            @RequestParam String link) {
        Paper paper = new Paper(); // Creating a new Paper object
        paper.setTitle(title); // Setting the title
        paper.setTags(tags); // Setting the tags
        paper.setLink(link); // Setting the link
        paper.setStatus(0); // Setting the status to unprocessed
        paper.setUploadeddate(LocalDate.now()); // Setting the upload date to the current date
        PSrepo.save(paper); // Saving the paper using the PaperStorerepo repository
        ModelAndView m= new ModelAndView("success.jsp"); // Creating a new ModelAndView object for success view
        return m; // Returning the ModelAndView object
    }
    
    /**
     * Displays the upload page.
     * 
     * @return the ModelAndView object for upload view
     * This method simply returns a ModelAndView object for the upload view.
     */
    @GetMapping("/upload")
    public Object upload() {
        ModelAndView mav = new ModelAndView("upload.jsp"); // Creating a new ModelAndView object for upload view
        return mav; // Returning the ModelAndView object
    }
}

