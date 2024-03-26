package com.nitconf.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;

import com.nitconf.model.Paper;
import com.nitconf.model.Reviewer;

import jakarta.servlet.ServletException;

public class FilterControllerTest {

    @Mock
    private PaperStorerepo PSrepo;

    @Mock
    private Reviewerrepo Rrepo;

    @Mock
    private Model model;
    
    private Paper paper;
    
    @InjectMocks
    private FilterController filterController;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        paper = new Paper();
        paper.setId(1L);
        paper.setTitle("Sample Paper");
        paper.setTags("maths");
        paper.setAuthorid(1L);
        paper.setLink("pdf link");
        paper.setStatus(0);
        paper.setUploadeddate(LocalDate.now());
    }

    @Test
    void testProcessRequest() throws ServletException, IOException {
        // Mock data
        long paperId = 1L;

        List<Reviewer> reviewers = new ArrayList<>();
        for(Long i=0L;i<5L;i++) {
        	Reviewer reviewer = new Reviewer();
        	reviewer.setId(i+1L);
        	reviewer.setEmail("reviewer"+i+1+"@gmail.com");
        	reviewer.setName("reviewer"+i+1);
        	reviewer.setTags(paper.getTags());
        	reviewers.add(reviewer);
        }
        List<Reviewer> reviewers1 = reviewers;
    	Reviewer reviewer = new Reviewer();
    	reviewer.setId(6L);
    	reviewer.setEmail("reviewer6@gmail.com");
    	reviewer.setName("reviewer6");
    	reviewer.setTags("test");
    	reviewers1.add(reviewer);

        // Mock behavior
        when(PSrepo.findById(paperId)).thenReturn(Optional.of(paper));
        when(Rrepo.getReviewersbytag(paper.getTags())).thenReturn(reviewers);

        // Perform request
        ModelAndView modelAndView = null;
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        
            modelAndView = filterController.processRequest(model, request, response, paperId);


        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("reviewertags.jsp", modelAndView.getViewName());
        assertEquals(reviewers,request.getAttribute("tag_reviewers"));
        assertEquals(paper.getTitle(),request.getAttribute("paper_title"));
        assertEquals(paper.getId(), request.getAttribute("paper_id"));

        // Verify interactions
        verify(PSrepo).findById(paperId);
        verify(Rrepo).getReviewersbytag(paper.getTags());
    }
    
    @Test
    public void testProcessRequest_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        long paperId = 123; // Example paper ID
        Paper paper = new Paper();
        paper.setId(paperId);
        paper.setTitle("Test Paper");
        paper.setTags("test, review, tags");
        List<Reviewer> mockReviewers = new ArrayList<>(); // Mock list of reviewers
        doThrow(new RuntimeException("Test Exception")).when(PSrepo).findById(paperId);

        // Act
        ModelAndView modelAndView = filterController.processRequest(model, request, response, paperId);

        // Assert
        assertEquals("reviewertags.jsp", modelAndView.getViewName());
        // You can add further assertions based on your requirements
    }

    @Test
    void testFilterPapers() throws ServletException, IOException {
        // Mock data
        String selectedTag = "Java";
        List<Paper> papers = new ArrayList<>();
        for(Long i=0L;i<5L;i++) {
        	Paper paper = new Paper();
        	paper.setId(i+1L);
            paper.setTitle("Sample Paper");
            paper.setTags(selectedTag);
            paper.setAuthorid(i+1L);
            paper.setLink("pdf link");
            paper.setStatus(0);
            paper.setUploadeddate(LocalDate.now());
        	papers.add(paper);
        }
        // Mock behavior
        when(PSrepo.getpapersbytags(selectedTag)).thenReturn(papers);
        when(PSrepo.getalltags()).thenReturn(new ArrayList<>());

        // Perform request
        ModelAndView modelAndView = null;
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        modelAndView = filterController.filterPapers(selectedTag, request, response);

        List<Paper> respPapers= (List<Paper>)request.getAttribute("tag_papers");
        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("bytags.jsp", modelAndView.getViewName());
        assertEquals(papers.size(),respPapers.size());
        assertNotNull(request.getAttribute("alltags"));

        // Verify interactions
        verify(PSrepo).getpapersbytags(selectedTag);
        verify(PSrepo).getalltags();
    }
    
    @Test
    public void testFilterPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
    	String selectedTag = "Java";
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        long paperId = 123; // Example paper ID
        Paper paper = new Paper();
        paper.setId(paperId);
        paper.setTitle("Test Paper");
        paper.setTags("test, review, tags");
        List<Reviewer> mockReviewers = new ArrayList<>(); // Mock list of reviewers
        doThrow(new RuntimeException("Test Exception")).when(PSrepo).getpapersbytags(selectedTag);

        // Act
        ModelAndView modelAndView = filterController.filterPapers(selectedTag, request, response);

        // Assert
        assertEquals("bytags.jsp", modelAndView.getViewName());
        // You can add further assertions based on your requirements
    }

}
