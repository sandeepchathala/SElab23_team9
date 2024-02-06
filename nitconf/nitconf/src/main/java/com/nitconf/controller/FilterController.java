package com.nitconf.controller;

import com.nitconf.model.Paper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.security.auth.message.callback.SecretKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
//YourController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/filter")
public class FilterController {
     @Autowired
     PaperStorerepo PSrepo;
	
     public String tag;
     //@SuppressWarnings("null")
     @GetMapping("/reviewertags")
     public ModelAndView processRequest(Model model,HttpServletRequest request, 
             HttpServletResponse response,@RequestParam long paperid) 
         throws ServletException, IOException 
     { 
    	 ModelAndView m= new ModelAndView("reviewertags.jsp");
     response.setContentType("text/html;charset=UTF-8");
     try {
    	 
	     Paper p = PSrepo.findById(paperid);
	     //model.addAttribute("papertitle",p.getTitle());
	     //HttpServletRequest request = null;
	     System.out.println("filterbytag "+p.getTitle());
	     //request.setAttribute("paper_title",p.getTitle());
	     request.setAttribute("paper_tag",p.getTags());
	     request.setAttribute("paper_title",p.getTitle());
	     request.setAttribute("paper_id",p.getId());
	     RequestDispatcher dispatcher = request.getRequestDispatcher("reviewertags.jsp");
         dispatcher.forward(request,response);
     
     }
     catch (Exception e) {
         // Handle exceptions if needed
         e.printStackTrace();
     }
     return m;
     }
     
     @PostMapping("/bytags")
     public ModelAndView filterPapers(@RequestParam String selectedTag,HttpServletRequest request, 
             HttpServletResponse response) throws ServletException, IOException{
          ModelAndView m = new ModelAndView("bytags.jsp");
          response.setContentType("text/html;charset=UTF-8");
          try {
     	     request.setAttribute("selectedTag",selectedTag);
     	     System.out.println(selectedTag);
     	     RequestDispatcher dispatcher = request.getRequestDispatcher("bytags.jsp");
              dispatcher.forward(request,response);       
          }
          catch (Exception e) {
              e.printStackTrace();
          }
          return m;
     }
}
