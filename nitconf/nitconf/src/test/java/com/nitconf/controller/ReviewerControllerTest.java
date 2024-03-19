package com.nitconf.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;
import com.nitconf.model.PaperReviewer;
import com.nitconf.model.Reviewer;
import com.nitconf.controller.PaperReviewerrepo;
import com.nitconf.controller.PaperStorerepo;
import com.nitconf.controller.Reviewerrepo;
import com.nitconf.services.EmailSenderService;
import com.nitconf.services.ReviewerService;

class ReviewerControllerTest {

    @Mock
    private ReviewerService RService;
    
    @Mock
    private Reviewerrepo R_repo;
    
    @Mock
    private PaperReviewerrepo PRrepo;
    
    @Mock
    private PaperStorerepo PS_repo;
    
    @Mock
    private EmailSenderService senderservice;

    @InjectMocks
    private ReviewerController reviewerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignReviewers() throws ServletException, IOException {
        // Mock data
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        String selectedReviewersParam = "1,2,3";
        String paper_idparam = "1";
        Paper paper = new Paper();
        paper.setId(1L);
        paper.setTitle("Sample Paper");
        paper.setTags("maths");
        paper.setAuthorid(1L);
        paper.setLink("pdf link");
        paper.setStatus(0);
        paper.setUploadeddate(LocalDate.now());

        List<Reviewer> reviewers = new ArrayList<>();
        for(Long i=0L;i<5L;i++) {
        	Reviewer reviewer = new Reviewer();
        	reviewer.setId(i+1L);
        	reviewer.setEmail("reviewer"+i+1+"@gmail.com");
        	reviewer.setName("reviewer"+i+1);
        	reviewer.setTags(paper.getTags());
        	reviewers.add(reviewer);
        }
        Optional<Paper> pape =null;
        // Mock behavior
        when(request.getParameter("selectedReviewers")).thenReturn(selectedReviewersParam);
        when(request.getParameter("paper_id")).thenReturn(paper_idparam);
        when(PS_repo.findById(paper.getId())).thenReturn(Optional.of(paper));
        when(R_repo.getextrareviewers(1L, paper.getTags())).thenReturn(reviewers);

        // Perform request
        ModelAndView modelAndView = reviewerController.assignReviewers(request, response);

        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("redirect:/api/papers/unassignedpapers", modelAndView.getViewName());

        // Verify interactions
        verify(PS_repo).setstatus(1L, 1);
        verify(PRrepo, times(3)).save(any(PaperReviewer.class));
        verify(senderservice, times(3)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testGetReviews() {
        // Mock data
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Long paper_id = 1L;
        Paper paper = new Paper();
        paper.setId(paper_id);
        paper.setTitle("Sample Paper");
        paper.setStatus(3);
        Object[] review1 = { 1L, "Reviewer 1", "Positive review" };
        Object[] review2 = { 2L, "Reviewer 2", "Negative review" };
        List<Object[]> reviews = Arrays.asList(review1, review2);

        // Mock behavior
        when(request.getParameter("paper_id")).thenReturn(String.valueOf(paper_id));
        when(PS_repo.findById(paper_id)).thenReturn(Optional.of(paper));
        when(PRrepo.getreview_accept(paper_id)).thenReturn(reviews);

        // Perform request
        ModelAndView modelAndView = reviewerController.getReviews(paper_id, request, response);

        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("reviews.jsp", modelAndView.getViewName());
        assertEquals(paper.getTitle(), request.getAttribute("paper_title"));
        assertEquals(reviews, request.getAttribute("reviews"));
    }

    @Test
    void testShowReview() throws ServletException, IOException {
        // Mock data
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Long paper_id = 1L;
        Paper paper = new Paper();
        paper.setId(paper_id);
        paper.setTitle("Sample Paper");
        List<Object[]> reviewList = new ArrayList<>();
        Object[] review1 = { "Reviewer 1", "Positive review" };
        Object[] review2 = { "Reviewer 2", "Negative review" };
        reviewList.add(review1);
        reviewList.add(review2);

        // Mock behavior
        when(request.getParameter("paper_id")).thenReturn(String.valueOf(paper_id));
        when(PS_repo.findById(paper_id)).thenReturn(Optional.of(paper));
        when(PRrepo.showreview(paper_id)).thenReturn(reviewList);

        // Perform request
        ModelAndView modelAndView = reviewerController.showReview(paper_id, request, response);

        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("showreview.jsp", modelAndView.getViewName());
        assertEquals(paper.getTitle(), request.getAttribute("paper_title"));
        assertEquals(paper_id, request.getAttribute("paper_id"));
        assertEquals(reviewList, request.getAttribute("showreviewlist"));
    }

    @Test
    void testAssignExtraReviewers() throws ServletException, IOException {
        // Mock data
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        String selectedReviewersParam = "4,5,6";
        String paper_idparam = "2";
        Paper paper = new Paper();
        paper.setId(1L);
        paper.setTitle("Sample Paper");
        paper.setTags("maths");
        paper.setAuthorid(1L);
        paper.setLink("pdf link");
        paper.setStatus(0);
        paper.setUploadeddate(LocalDate.now());

        List<Reviewer> reviewers = new ArrayList<>();
        for(Long i=0L;i<5L;i++) {
        	Reviewer reviewer = new Reviewer();
        	reviewer.setId(i+1L);
        	reviewer.setEmail("reviewer"+i+1+"@gmail.com");
        	reviewer.setName("reviewer"+i+1);
        	reviewer.setTags(paper.getTags());
        	reviewers.add(reviewer);
        }

        // Mock behavior
        when(request.getParameter("selectedReviewers")).thenReturn(selectedReviewersParam);
        when(request.getParameter("paper_id")).thenReturn(paper_idparam);
        Optional<Paper> pp =
        when(PS_repo.findById(2L)).thenReturn(Optional.of(paper));
        when(R_repo.getextrareviewers(2L, "physics")).thenReturn(reviewers);

        // Perform request
        ModelAndView modelAndView = reviewerController.assignExtraReviewers(request, response);

        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("redirect:/api/papers/assignedpapers", modelAndView.getViewName());

        // Verify interactions
        verify(PRrepo, times(3)).save(any(PaperReviewer.class));
        verify(senderservice, times(3)).sendEmail(anyString(), anyString(), anyString());
    }
}

