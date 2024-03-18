package com.nitconf.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.nitconf.model.Paper;

class PapersControllerTest {

    @Mock
    private Model model;
    
    @Mock
    private PaperStorerepo paperStorerepo;

    @InjectMocks
    private PapersController papersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAssignedPapers() throws Exception {
        // Mock data
        List<Paper> assignedPapers = new ArrayList<>();
        when(paperStorerepo.findByStatus(2)).thenReturn(assignedPapers);

        // Mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call controller method
        ModelAndView modelAndView = papersController.getassignedpapers(model, request, response);

        // Verify behavior
        assertEquals("assignedpapers.jsp", modelAndView.getViewName());
        assertEquals(assignedPapers, request.getAttribute("assigned_papers"));
        verify(paperStorerepo, times(1)).findByStatus(2);
    }

    @Test
    void testGetReviewedPapers() throws Exception {
        List<Paper> reviewedPapers = new ArrayList<>();
        when(paperStorerepo.findByStatus(2)).thenReturn(reviewedPapers);

        // Mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call controller method
        ModelAndView modelAndView = papersController.getreviewedpapersa(model, request, response);

        // Verify behavior
        assertEquals("reviewedpapers.jsp", modelAndView.getViewName());
        assertEquals(reviewedPapers, request.getAttribute("reviewed_papers"));
        verify(paperStorerepo, times(1)).findByStatus(2);
    }

    @Test
    void testGetUnreviewedPapers() throws Exception {
        List<Paper> unreviewedPapers = new ArrayList<>();
        when(paperStorerepo.findByStatus(1)).thenReturn(unreviewedPapers);

        // Mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call controller method
        ModelAndView modelAndView = papersController.getunreviewedpapersa(model, request, response);

        // Verify behavior
        assertEquals("unreviewedpapers.jsp", modelAndView.getViewName());
        assertEquals(unreviewedPapers, request.getAttribute("unreviewed_papers"));
        verify(paperStorerepo, times(1)).findByStatus(1);
    }

    @Test
    void testGetUnassignedPapers() throws Exception {
            List<Paper> unassignedPapers = new ArrayList<>();
            when(paperStorerepo.findByStatus(0)).thenReturn(unassignedPapers);

            // Mock request and response
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();

            // Call controller method
            ModelAndView modelAndView = papersController.getUnassignedPapers(model, request, response);

            // Verify behavior
            assertEquals("unassignedpapers.jsp", modelAndView.getViewName());
            assertEquals(unassignedPapers, request.getAttribute("unassigned_papers"));
            verify(paperStorerepo, times(1)).findByStatus(0);
    }

    @Test
    void testGetAcceptedPapers() throws Exception {
        List<Paper> acceptedPapers = new ArrayList<>();
        when(paperStorerepo.findByStatus(3)).thenReturn(acceptedPapers);

        // Mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call controller method
        ModelAndView modelAndView = papersController.getAcceptedPapers(model, request, response);

        // Verify behavior
        assertEquals("acceptedpapers.jsp", modelAndView.getViewName());
        assertEquals(acceptedPapers, request.getAttribute("accepted_papers"));
        verify(paperStorerepo, times(1)).findByStatus(3);
    }

    @Test
    void testGetRejectedPapers() throws Exception {
            List<Paper> rejectedPapers = new ArrayList<>();
            when(paperStorerepo.findByStatus(4)).thenReturn(rejectedPapers);

            // Mock request and response
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();

            // Call controller method
            ModelAndView modelAndView = papersController.getRejectedPapers(model, request, response);

            // Verify behavior
            assertEquals("rejectedpapers.jsp", modelAndView.getViewName());
            assertEquals(rejectedPapers, request.getAttribute("rejected_papers"));
            verify(paperStorerepo, times(1)).findByStatus(4);
    }
}
