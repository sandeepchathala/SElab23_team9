package com.nitconf.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

public class FilterControllerTest {

    @Mock
    private PaperStorerepo PSrepo;

    @Mock
    private Reviewerrepo Rrepo;

    @Mock
    Model model;
    
    @InjectMocks
    private FilterController filterController;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessRequest() {
        // Mock data
        long paperId = 1L;
        Paper paper = new Paper();
        paper.setId(paperId);
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
        List<Reviewer> reviewers1 = reviewers;
    	Reviewer reviewer = new Reviewer();
    	reviewer.setId(6L);
    	reviewer.setEmail("reviewer6@gmail.com");
    	reviewer.setName("reviewer6");
    	reviewer.setTags("test");
    	reviewers1.add(reviewer);

        // Mock behavior
        when(PSrepo.findById(paperId)).thenReturn(paper);
        when(Rrepo.getReviewersbytag(paper.getTags())).thenReturn(reviewers);

        // Perform request
        ModelAndView modelAndView = null;
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        try {
            modelAndView = filterController.processRequest(model, request, response, paperId);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

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
    void testFilterPapers() {
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
        try {
            modelAndView = filterController.filterPapers(selectedTag, request, response);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

        // Verify ModelAndView
        assertNotNull(modelAndView);
        assertEquals("bytags.jsp", modelAndView.getViewName());
        assertEquals(papers,request.getAttribute("tag_papers"));
        assertNotNull(request.getAttribute("alltags"));

        // Verify interactions
        verify(PSrepo).getpapersbytags(selectedTag);
        verify(PSrepo).getalltags();
    }
}
