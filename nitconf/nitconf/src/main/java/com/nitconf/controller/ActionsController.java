/**
 * Controller class for handling actions related to papers in the API.
 */
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

/**
 * RestController for handling actions related to papers.
 */
@RestController
@RequestMapping("/api/actions")
public class ActionsController {

    @Autowired
    private PaperStorerepo PSrepo;
    @Autowired
    private Authorrepo Arepo;
    @Autowired
    private EmailSenderService senderservice;

    /**
     * Private method to handle the acceptance of a paper.
     *
     * @param paper_id Paper ID for which the acceptance action is performed.
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return ModelAndView object for redirecting to the assigned papers page or displaying an error message.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */ 
    ModelAndView fun_accept(Long paper_id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Paper> p = PSrepo.findById(paper_id);
        if (p.isPresent()) {
            Paper pp = p.get();
            Optional<Author> a = Arepo.findById(pp.getAuthorid());
            if (a.isPresent()) {
                Author aa = a.get();
                String emailsubject = "Paper is Accepted";
                String emailbody = "Dear " + aa.getName() + ", Your Paper ( Title = " + pp.getTitle() +
                        " ) which was submitted in NITCONF website on " + pp.getUploadeddate() +
                        " is accepted. Please go through the reviews for more details";
                senderservice.sendEmail(aa.getEmail(), emailsubject, emailbody);
                PSrepo.setstatus(pp.getId(), 3);
                return new ModelAndView("redirect:/api/papers/assignedpapers");
            }
            return new ModelAndView("Author is not Found");
        }
        return new ModelAndView("Paper is not Found");
    }

    /**
     * Endpoint for accepting a paper.
     *
     * @param paper_id Paper ID for which the acceptance action is performed.
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return ModelAndView object for redirecting to the assigned papers page or displaying an error message.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
   @Transactional
    @GetMapping("/accept")
    public ModelAndView accept(@RequestParam Long paper_id, HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        return fun_accept(paper_id, request, response);
    }

    /**
     * Private method to handle the rejection of a paper.
     *
     * @param paper_id Paper ID for which the rejection action is performed.
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return ModelAndView object for redirecting to the assigned papers page or displaying an error message.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private ModelAndView fun_reject(Long paper_id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Paper> p = PSrepo.findById(paper_id);
        if (p.isPresent()) {

            Paper pp = p.get();
            Optional<Author> a = Arepo.findById(pp.getAuthorid());
            if (a.isPresent()) {
                Author aa = a.get();
                String emailsubject = "Paper is Rejected";
                String emailbody = "Dear " + aa.getName() + ", Your Paper ( Title = " + pp.getTitle() +
                        " ) which was submitted in NITCONF website on " + pp.getUploadeddate() +
                        " is rejected. Please go through the reviews for more details";
                senderservice.sendEmail(aa.getEmail(), emailsubject, emailbody);
                PSrepo.setstatus(pp.getId(), 4);
                return new ModelAndView("redirect:/api/papers/assignedpapers");
            }
            return new ModelAndView("Author is not Found");

        }
        return new ModelAndView("Paper is not Found");
    }

    /**
     * Endpoint for rejecting a paper.
     *
     * @param paper_id Paper ID for which the rejection action is performed.
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return ModelAndView object for redirecting to the assigned papers page or displaying an error message.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Transactional
    @GetMapping("/reject")
    public ModelAndView reject(@RequestParam Long paper_id, HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        return fun_reject(paper_id, request, response);
    }

}
