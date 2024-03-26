package com.nitconf.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.nitconf.model.Author;
import com.nitconf.model.Paper;
import com.nitconf.services.EmailSenderService;

@ExtendWith(MockitoExtension.class)
public class ActionsControllerTest {

    @Mock
    private PaperStorerepo PSrepo;

    @Mock
    private Authorrepo Arepo;

    @Mock
    private EmailSenderService senderservice;

    @InjectMocks
    private ActionsController controller;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private Paper samplePaper;
    private Author sampleAuthor;

    @BeforeEach
    public void setUp() {
        // Initialize sample paper and author for testing
        sampleAuthor = new Author();
        sampleAuthor.setId(1L);
        sampleAuthor.setName("John Doe");
        sampleAuthor.setEmail("john.doe@example.com");

        samplePaper = new Paper();
        samplePaper.setId(1L);
        samplePaper.setTitle("Sample Paper");
        samplePaper.setUploadeddate(LocalDate.now());
        samplePaper.setAuthorid(sampleAuthor.getId());
    }
    @Test
    public void testAccept() throws ServletException, IOException {
        // Mock repository methods
        when(PSrepo.findById(any(Long.class))).thenReturn(Optional.of(samplePaper));
        when(Arepo.findById(any(Long.class))).thenReturn(Optional.of(sampleAuthor));

        // Test controller method
        ModelAndView result = controller.accept(1L, request, response);

        // Verify interactions
        verify(senderservice).sendEmail(sampleAuthor.getEmail(), "Paper is Accepted",
                "Dear John Doe, Your Paper ( Title = Sample Paper ) which was submitted in NITCONF website on "
                        + LocalDate.now() + " is accepted. Please go through the reviews for more details");
        verify(PSrepo).setstatus(1L, 3);
    }




    @Test
    public void testReject() throws ServletException, IOException {
        // Mock repository methods
        when(PSrepo.findById(any())).thenReturn(Optional.of(samplePaper)); // Corrected stubbing
        when(Arepo.findById(any())).thenReturn(Optional.of(sampleAuthor)); // Stub author lookup

        // Test controller method
        ModelAndView result = controller.reject(1L, request, response);

        // Verify interactions
        verify(senderservice).sendEmail(sampleAuthor.getEmail(), "Paper is Rejected",
                "Dear John Doe, Your Paper ( Title = Sample Paper ) which was submitted in NITCONF website on "
                        + LocalDate.now() + " is rejected. Please go through the reviews for more details");
        verify(PSrepo).setstatus(1L, 4);
    }


    @Test
    public void testFun_accept_PaperNotFound() throws ServletException, IOException {
        // Mock repository methods
        when(PSrepo.findById(any())).thenReturn(Optional.empty()); // Mock PSrepo to return Paper not found

        // Test controller method
        ModelAndView result = controller.fun_accept(1L, request, response);

        // Verify interactions
        verify(senderservice, never()).sendEmail(anyString(), anyString(), anyString()); // Ensure no email is sent
        verify(PSrepo, never()).setstatus(anyLong(), anyInt()); // Ensure status is not updated
        assertEquals("Paper is not Found", result.getViewName()); // Verify the view name
    }

    @Test
    public void testFun_accept_AuthorNotFound() throws ServletException, IOException {
        // Mock repository methods
        when(PSrepo.findById(any())).thenReturn(Optional.of(samplePaper)); // Stub PSrepo to return a sample paper
        when(Arepo.findById(any())).thenReturn(Optional.empty()); // Mock Arepo to return Author not found

        // Test controller method
        ModelAndView result = controller.fun_accept(1L, request, response);

        // Verify interactions
        verify(senderservice, never()).sendEmail(anyString(), anyString(), anyString()); // Ensure no email is sent
        verify(PSrepo, never()).setstatus(anyLong(), anyInt()); // Ensure status is not updated
        assertEquals("Author is not Found", result.getViewName()); // Verify the view name
    }
    @Test
    public void testFun_reject_AuthorNotFound() throws ServletException, IOException {
        // Arrange
        when(PSrepo.findById(any())).thenReturn(Optional.of(new Paper()));
        when(Arepo.findById(any())).thenReturn(Optional.empty());

        // Act
        ModelAndView result = controller.reject(1L, request, response);

        // Assert
        assertEquals("Author is not Found", result.getViewName());
    }

    @Test
    public void testFun_reject_PaperNotFound() throws ServletException, IOException {
        // Arrange
        when(PSrepo.findById(any())).thenReturn(Optional.empty());

        // Act
        ModelAndView result = controller.reject(1L, request, response);

        // Assert
        assertEquals("Paper is not Found", result.getViewName());
    }

}
