package com.nitconf.controller;

import java.io.IOException;
import java.time.LocalDate;
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

import com.nitconf.model.Author;
import com.nitconf.model.Paper;
import com.nitconf.services.EmailSenderService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/actions")
public class ActionsController {
	
	@Autowired
	private PaperStorerepo PSrepo;
	@Autowired
	private Authorrepo Arepo;
	@Autowired
	private EmailSenderService senderservice;
	
	@Transactional
	@GetMapping("/accept")
	public ModelAndView accept(@RequestParam Long paper_id,HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException 
            { 
           	System.out.println("accept");
           	Optional<Paper> p= PSrepo.findById(paper_id);
           	if(p.isPresent()){
           		
           		Paper pp = p.get();
           		Optional<Author> a = Arepo.findById(pp.getAuthorid());
           		if(a.isPresent()) {
           			ModelAndView m = new ModelAndView("redirect:/api/papers/assignedpapers");
           			Author aa = a.get();
           			System.out.println(aa.getEmail());
           			String emailsubject = "Paper is Accepted";
           			String emailbody = "Dear "+aa.getName()+", Your Paper ( Title = "+pp.getTitle()+" ) which was submitted in NITCONF website on "+pp.getUploadeddate()+" is accepted. Please go through the reviews for more details";
           			senderservice.sendEmail(aa.getEmail(),emailsubject,emailbody);
           			System.out.println("accept");
           			PSrepo.setstatus(pp.getId(),3);
           			return m;
           		}
           		ModelAndView m = new ModelAndView("Author is not Found");
           		return m;
           		
           	}
           	ModelAndView m = new ModelAndView("Paper is not Found");
           	return m; 	
    }
	
	@Transactional
	@GetMapping("/reject")
	public ModelAndView reject(@RequestParam Long paper_id,HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException 
            { 
           	 
           	Optional<Paper> p= PSrepo.findById(paper_id);
           	if(p.isPresent()){
           		
           		Paper pp = p.get();
           		Optional<Author> a = Arepo.findById(pp.getAuthorid());
           		if(a.isPresent()) {
           			ModelAndView m = new ModelAndView("redirect:/api/papers/assignedpapers");
           			Author aa = a.get();
           			String emailsubject = "Paper is Rejected";
           			String emailbody = "Dear "+aa.getName()+", Your Paper ( Title = "+pp.getTitle()+" ) which was submitted in NITCONF website on "+pp.getUploadeddate()+" is rejected. Please go through the reviews for more details";
           			senderservice.sendEmail(aa.getEmail(),emailsubject,emailbody);
           			PSrepo.setstatus(pp.getId(),4);
           			return m;
           		}
           		ModelAndView m = new ModelAndView("Author is not Found");
           		return m;
           		
           	}
           	ModelAndView m = new ModelAndView("Paper is not Found");
           	return m;
           	
    }
	
}
