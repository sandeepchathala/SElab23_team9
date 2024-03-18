package com.nitconf.controllertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nitconf.controller.PapersController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

class PapersControllerTest {

    @InjectMocks
    private PapersController papersController;

    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();
    }

    @Test
    void testGetAssignedPapers() throws Exception {
        mockMvc.perform(get("/api/papers/assignedpapers"))
               .andExpect(view().name("assignedpapers.jsp"));
    }

    @Test
    void testGetReviewedPapers() throws Exception {
        mockMvc.perform(get("/api/papers/reviewedpapers"))
               .andExpect(view().name("reviewedpapers.jsp"));
    }

    @Test
    void testGetUnreviewedPapers() throws Exception {
        mockMvc.perform(get("/api/papers/unreviewedpapers"))
               .andExpect(view().name("unreviewedpapers.jsp"));
    }

    @Test
    void testGetUnassignedPapers() throws Exception {
        mockMvc.perform(get("/api/papers/unassignedpapers"))
               .andExpect(view().name("unassignedpapers.jsp"));
    }

    @Test
    void testGetAcceptedPapers() throws Exception {
        mockMvc.perform(get("/api/papers/acceptedpapers"))
               .andExpect(view().name("acceptedpapers.jsp"));
    }

    @Test
    void testGetRejectedPapers() throws Exception {
        mockMvc.perform(get("/api/papers/rejectedpapers"))
               .andExpect(view().name("rejectedpapers.jsp"));
    }
}