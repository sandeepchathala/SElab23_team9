package com.nitconf.controller;

//import java.util.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;
//import com.nitconf.services.PaperStoreService;


@RestController
@RequestMapping(value="/Paper")
public class PaperController {
	
	@Autowired
	PaperStorerepo PSrepo;
	
    @PostMapping("/StorePaper")
    public ModelAndView storepaper( @RequestParam String title,
            @RequestParam String tags,
            @RequestParam String link) {
    	Paper paper = new Paper();
    	paper.setTitle(title);
        paper.setTags(tags);
        paper.setLink(link);
        paper.setStatus(0);
        paper.setUploadeddate(LocalDate.now());
    	PSrepo.save(paper);
    	ModelAndView m= new ModelAndView("success.jsp");
    	return m;
    }
    @GetMapping("/upload")
    public ModelAndView upload() {
    	ModelAndView mav = new ModelAndView("upload.jsp");
    	return mav;
    }
}