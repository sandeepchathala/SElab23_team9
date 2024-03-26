package com.nitconf.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.Paper;
import com.nitconf.model.Reviewer;
import com.nitconf.services.EmailSenderService;
import com.nitconf.services.ReviewerService;

//@ExtendWith(MockitoExtension.class)
public class ReviewerControllerTest {

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

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ReviewerController controller;
    
    //@Mock
    private Paper mockPaper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockPaper = new Paper();
        mockPaper.setId(1L);
        mockPaper.setAuthorid(1L);
        mockPaper.setLink("paper-link");
        mockPaper.setStatus(0);
        mockPaper.setTags("maths");
        mockPaper.setTitle("test");
        mockPaper.setUploadeddate(LocalDate.now());
    }
    @Test
    public void testAssignReviewers_Success() throws ServletException, IOException {
        // Mock request parameters
        //when(request.getParameter("selectedReviewers")).thenReturn("1,2,3");
        //when(request.getParameter("paper_id")).thenReturn("1");

    	MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("paper_id", String.valueOf(mockPaper.getId()));
        request.setParameter("selectedReviewers", String.valueOf("1,2,3"));
        // Mock repository methods
        //Paper mockPaper = new Paper();
        when(PS_repo.findById(mockPaper.getId())).thenReturn(Optional.of(mockPaper)); // Change this line
        //when(PS_repo.setstatus(1L, 1)).thenReturn(true);
//        List<Reviewer> reviewers = new ArrayList<>();
//        for(Long i=1L;i<=3L;i++) {
//        	Reviewer reviewer = new Reviewer();
//        	reviewer.setId(1L);
//        	reviewer.setEmail("reviewer"+i+"@gmail.com");
//        	reviewer.setName("reviewer"+i);
//        	reviewer.setTags(mockPaper.getTags());
//        	reviewers.add(reviewer);
//        	when(R_repo.findById(i)).thenReturn(Optional.of(reviewer));
//        }
        Reviewer mockReviewer1 = new Reviewer();
        mockReviewer1.setEmail("reviewer1@example.com");
        mockReviewer1.setTags(mockPaper.getTags());
        mockReviewer1.setId(1L);
        when(R_repo.findById(mockReviewer1.getId())).thenReturn(Optional.of(mockReviewer1));

        Reviewer mockReviewer2 = new Reviewer();
        mockReviewer2.setId(2L);
        mockReviewer2.setEmail("reviewer2@example.com");
        mockReviewer2.setTags(mockPaper.getTags());
        when(R_repo.findById(mockReviewer2.getId())).thenReturn(Optional.of(mockReviewer2));

        Reviewer mockReviewer3 = new Reviewer();
        mockReviewer3.setId(3L);
        mockReviewer3.setEmail("reviewer3@example.com");
        mockReviewer3.setTags(mockPaper.getTags());
        when(R_repo.findById(mockReviewer3.getId())).thenReturn(Optional.of(mockReviewer3));

        // Call the controller method
        ModelAndView modelAndView = controller.assignReviewers(request, response);

     // Capture invocations
        //ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        //ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        assertEquals("redirect:/api/papers/unassignedpapers", modelAndView.getViewName());
        verify(PS_repo).findById(any());
        verify(PS_repo).setstatus(any(), anyInt()); 
        verify(PRrepo, times(3)).save(any());
        verify(senderservice, times(3)).sendEmail(anyString(), anyString(), anyString());
        //verify(senderservice, times(3)).sendEmail(emailCaptor.capture(), anyString(), anyString());
        // Assert captured values
       // assertEquals(1L, idCaptor.getValue().longValue());
        //assertEquals(3, emailCaptor.getAllValues().size());

        
    }

    @Test
    public void testAssignReviewers_NoSelectedReviewers() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("selectedReviewers")).thenReturn("");
        when(request.getParameter("paper_id")).thenReturn("1");

        // Call the controller method
        ModelAndView modelAndView = controller.assignReviewers(request, response);

        // Verify behavior
        verifyZeroInteractions(PS_repo, PRrepo, RService, senderservice);

        assertEquals("noreviewersselected.jsp", modelAndView.getViewName());
    }

    @Test
    public void testAssignReviewers_PaperNotFound() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("selectedReviewers")).thenReturn("1,2,3");
        when(request.getParameter("paper_id")).thenReturn("1");

        // Mock repository methods
        when(PS_repo.findById(1L)).thenReturn(Optional.empty());

        // Call the controller method
        ModelAndView modelAndView = controller.assignReviewers(request, response);

        // Verify behavior
        verifyZeroInteractions(PRrepo, RService, senderservice);

        assertEquals("noreviewersselected.jsp", modelAndView.getViewName());
    }

    @Test
    public void testGetReviews_Success_accept() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("paper_id")).thenReturn("1");

        // Mock repository methods
        Paper mockPaper = new Paper();
        mockPaper.setStatus(3); // Assuming status 3 indicates accepted paper
        when(PS_repo.findById(any())).thenReturn(Optional.of(mockPaper));
        List<Object[]> mockReviews = Arrays.asList(new Object[]{"Review 1"}, new Object[]{"Review 2"});
        when(PRrepo.getreview_accept(any())).thenReturn(mockReviews);

        // Call the controller method
        ModelAndView modelAndView = controller.getReviews(1L, request, response);

        // Verify behavior
        verify(request).setAttribute("paper_title", mockPaper.getTitle());
        verify(request).setAttribute("reviews", mockReviews);

        assertEquals("reviews.jsp", modelAndView.getViewName());
    }
    
    @Test
    public void testGetReviews_Success_reject() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("paper_id")).thenReturn("1");

        // Mock repository methods
        //Paper mockPaper = new Paper();
        mockPaper.setStatus(4);
        // Assuming status 3 indicates accepted paper
        when(PS_repo.findById(mockPaper.getId())).thenReturn(Optional.of(mockPaper));
        List<Object[]> mockReviews = Arrays.asList(new Object[]{"Review 1"}, new Object[]{"Review 2"});
        when(PRrepo.getreview_reject(any())).thenReturn(mockReviews);

        // Call the controller method
        ModelAndView modelAndView = controller.getReviews(1L, request, response);

        assertEquals(mockReviews,request.getAttribute("reviews"));
        // Verify behavior
        verify(request).setAttribute("paper_title", mockPaper.getTitle());
        verify(request).setAttribute("reviews", mockReviews);

        assertEquals("reviews.jsp", modelAndView.getViewName());
    }

    @Test
    public void testShowReview_Success() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("paper_id")).thenReturn("1");

        // Mock repository methods
        Paper mockPaper = new Paper();
        mockPaper.setTitle("Sample Paper");
        when(PS_repo.findById(1L)).thenReturn(Optional.of(mockPaper));
        List<Object[]> mockReviewList = Arrays.asList(new Object[]{"Reviewer 1", "Review 1"}, new Object[]{"Reviewer 2", "Review 2"});
        when(PRrepo.showreview(1L)).thenReturn(mockReviewList);

        // Call the controller method
        ModelAndView modelAndView = controller.showReview(1L, request, response);

        // Verify behavior
        verify(request).setAttribute("showreviewlist", mockReviewList);
        verify(request).setAttribute("paper_title", mockPaper.getTitle());
        verify(request).setAttribute("paper_id", mockPaper.getId());

        assertEquals("showreview.jsp", modelAndView.getViewName());
    }

    @Test
    public void testAssignExtraReviewers_Success() throws ServletException, IOException {
    	// Mock repository methods
    	Paper mockPaper = new Paper();
    	when(PS_repo.findById(anyLong())).thenReturn(Optional.of(mockPaper)); // Use anyLong() matcher

    	// Remaining mock setups for reviewers and service
    	Reviewer mockReviewer1 = new Reviewer();
    	mockReviewer1.setEmail("reviewer1@example.com");
    	when(RService.findbyid(1L)).thenReturn(Optional.of(mockReviewer1));

    	Reviewer mockReviewer2 = new Reviewer();
    	mockReviewer2.setEmail("reviewer2@example.com");
    	when(RService.findbyid(2L)).thenReturn(Optional.of(mockReviewer2));

    	// Call the controller method
    	ModelAndView modelAndView = controller.assignExtraReviewers(request, response);

    	// Verify behavior
    	// Verify that PRrepo.save(pr) is called once for each reviewer
    	verify(PRrepo, times(2)).save(any());

    	verify(senderservice, times(2)).sendEmail(anyString(), anyString(), anyString());

    	assertEquals("redirect:/api/papers/assignedpapers", modelAndView.getViewName());

    }


    @Test
    public void testAssignExtraReviewers_NoSelectedReviewers() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("selectedReviewers")).thenReturn("");
        when(request.getParameter("paper_id")).thenReturn("1");

        // Call the controller method
        ModelAndView modelAndView = controller.assignExtraReviewers(request, response);

        // Verify behavior
        verifyZeroInteractions(PS_repo, PRrepo, RService, senderservice);

        assertEquals("noreviewersselected.jsp", modelAndView.getViewName());
    }

    @Test
    public void testAssignExtraReviewers_PaperNotFound() throws ServletException, IOException {
        // Mock request parameters
        // Mock request parameters
        when(request.getParameter("selectedReviewers")).thenReturn("1,2");
        when(request.getParameter("paper_id")).thenReturn("1");

        // Mock repository methods
        when(PS_repo.findById(1L)).thenReturn(Optional.empty());

        // Call the controller method
        ModelAndView modelAndView = controller.assignExtraReviewers(request, response);

        // Verify behavior
        verifyZeroInteractions(PRrepo, RService, senderservice);

        assertEquals("noreviewersselected.jsp", modelAndView.getViewName());
    }
}