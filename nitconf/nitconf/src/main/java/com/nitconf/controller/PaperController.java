/**
 * The PaperController class handles requests related to papers.
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
     */
    @PostMapping("/StorePaper")
    public ModelAndView storePaper(
            @RequestParam String title,
            @RequestParam String tags,
            @RequestParam String link) {
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setTags(tags);
        paper.setLink(link);
        paper.setStatus(0);
        paper.setUploadedDate(LocalDate.now());
        PSrepo.save(paper);
        ModelAndView m= new ModelAndView("success.jsp");
        return m;
    }
    
    /**
     * Displays the upload page.
     * 
     * @return the ModelAndView object for upload view
     */
    @GetMapping("/upload")
    public ModelAndView upload() {
        ModelAndView mav = new ModelAndView("upload.jsp");
        return mav;
    }
}
