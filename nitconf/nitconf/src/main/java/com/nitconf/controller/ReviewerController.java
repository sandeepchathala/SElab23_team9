package com.nitconf.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;
import com.nitconf.model.PaperReviewer;
import com.nitconf.model.Reviewer;
import com.nitconf.services.EmailSenderService;
import com.nitconf.services.ReviewerService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/reviewers")
public class ReviewerController {

	 @Autowired
	 private ReviewerService RService;
	 @Autowired
	 private PaperReviewerrepo PRrepo;
	 @Autowired
	 private PaperStorerepo PS_repo;
	 @Autowired
	 private EmailSenderService senderservice;
 
	 @Transactional
	 @PostMapping("/assign")
	 public ModelAndView assignReviewers(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		 
	     try {
	         String selectedReviewersParam = request.getParameter("selectedReviewers");
	         String paper_idparam = request.getParameter("paper_id");
	         if (selectedReviewersParam != null && !selectedReviewersParam.isEmpty()) {
	             // Split the parameter into a list of reviewer IDs
	             List<String> selectedReviewers = Arrays.asList(selectedReviewersParam.split(","));
	             List<Long> reviewerlist= new ArrayList<>();
	             for(String s : selectedReviewers) reviewerlist.add(Long.valueOf(s));
	             //if(reviewerlist.size()==0) {
	             Long paper_id = Long.parseLong(paper_idparam);
	             System.out.println(selectedReviewers);
	             Paper pp = new Paper();
	             Optional <Paper> p=PS_repo.findById(paper_id);
	             if(p.isPresent()) {
	            	 pp=p.get();
	            	 System.out.println(pp.getTitle());
	            	 //int status =1;
	            	 Long id=paper_id;
	            	 PS_repo.setstatus(id,1);
	             }
	             for(Long r_id : reviewerlist) {
	            	 PaperReviewer pr = new PaperReviewer();
	            	 pr.setPaper_id(paper_id);
	            	 pr.setReviewer_id(r_id);
	            	 pr.setAssigneddate(LocalDate.now());
	            	 PRrepo.save(pr);
	            	 Optional <Reviewer> R=RService.findbyid(r_id);
		             if(R.isPresent()) {
		            	 Reviewer rr=R.get();
		            	 String bodyofmail="You are assigned to a new paper to review. Paper Title = "+pp.getTitle()+".";
		            	 senderservice.sendEmail(rr.getEmail(),"New Paper is ready to review",bodyofmail);
		             }
	            	 
	             }
	             ModelAndView m= new ModelAndView("redirect:/api/papers/unassignedpapers");
	             return m;
	             //}
	         } else {
	        	 ModelAndView m= new ModelAndView("noreviewersselected.jsp");
	             return m;
	         }
	     } finally {
	         //out.close();
	     }
	 }
	 
}

