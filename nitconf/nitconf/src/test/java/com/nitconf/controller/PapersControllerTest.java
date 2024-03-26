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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.nitconf.model.Paper;

import jakarta.servlet.ServletException;

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
    public void testGetAssignedPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Paper> assignedPapers = new ArrayList<>();
        doThrow(new RuntimeException("Test Exception")).when(paperStorerepo).findByStatus(2);

        // Act
        ModelAndView modelAndView = papersController.getassignedpapers(model, request, response);

        // Assert
        assertEquals("assignedpapers.jsp", modelAndView.getViewName());
        // You can assert further based on the behavior you expect after catching the exception
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
    public void testGetReviewedPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Paper> assignedPapers = new ArrayList<>();
        doThrow(new RuntimeException("Test Exception")).when(paperStorerepo).findByStatus(2);

        // Act
        ModelAndView modelAndView = papersController.getreviewedpapersa(model, request, response);

        // Assert
        assertEquals("reviewedpapers.jsp", modelAndView.getViewName());
        // You can assert further based on the behavior you expect after catching the exception
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
    public void testGetUnreviewedPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Paper> assignedPapers = new ArrayList<>();
        doThrow(new RuntimeException("Test Exception")).when(paperStorerepo).findByStatus(1);

        // Act
        ModelAndView modelAndView = papersController.getunreviewedpapersa(model, request, response);

        // Assert
        assertEquals("unreviewedpapers.jsp", modelAndView.getViewName());
        // You can assert further based on the behavior you expect after catching the exception
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
    public void testGetUnassignedPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Paper> assignedPapers = new ArrayList<>();
        doThrow(new RuntimeException("Test Exception")).when(paperStorerepo).findByStatus(0);

        // Act
        ModelAndView modelAndView = papersController.getUnassignedPapers(model, request, response);

        // Assert
        assertEquals("unassignedpapers.jsp", modelAndView.getViewName());
        // You can assert further based on the behavior you expect after catching the exception
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
    public void testGetAcceptededPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Paper> assignedPapers = new ArrayList<>();
        doThrow(new RuntimeException("Test Exception")).when(paperStorerepo).findByStatus(3);

        // Act
        ModelAndView modelAndView = papersController.getAcceptedPapers(model, request, response);

        // Assert
        assertEquals("acceptedpapers.jsp", modelAndView.getViewName());
        // You can assert further based on the behavior you expect after catching the exception
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
    
    @Test
    public void testGetRejectedPapers_ExceptionHandling() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Paper> assignedPapers = new ArrayList<>();
        doThrow(new RuntimeException("Test Exception")).when(paperStorerepo).findByStatus(4);

        // Act
        ModelAndView modelAndView = papersController.getRejectedPapers(model, request, response);

        // Assert
        assertEquals("rejectedpapers.jsp", modelAndView.getViewName());
        // You can assert further based on the behavior you expect after catching the exception
    }
}
