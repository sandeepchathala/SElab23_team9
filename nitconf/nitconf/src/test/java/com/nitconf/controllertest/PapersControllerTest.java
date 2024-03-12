package com.nitconf.controllertest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nitconf.controller.PapersController;

class PapersControllerTest {

    @Test
    void testGetAssignedPapers() throws Exception {
        PapersController papersController = new PapersController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/assignedpapers"))
               .andExpect(MockMvcResultMatchers.view().name("assignedpapers.jsp"));
    }

    @Test
    void testGetReviewedPapers() throws Exception {
        PapersController papersController = new PapersController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/reviewedpapers"))
               .andExpect(MockMvcResultMatchers.view().name("reviewedpapers.jsp"));
    }

    @Test
    void testGetUnreviewedPapers() throws Exception {
        PapersController papersController = new PapersController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/unreviewedpapers"))
               .andExpect(MockMvcResultMatchers.view().name("unreviewedpapers.jsp"));
    }

    @Test
    void testGetUnassignedPapers() throws Exception {
        PapersController papersController = new PapersController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/unassignedpapers"))
               .andExpect(MockMvcResultMatchers.view().name("unassignedpapers.jsp"));
    }

    @Test
    void testGetAcceptedPapers() throws Exception {
        PapersController papersController = new PapersController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/acceptedpapers"))
               .andExpect(MockMvcResultMatchers.view().name("acceptedpapers.jsp"));
    }

    @Test
    void testGetRejectedPapers() throws Exception {
        PapersController papersController = new PapersController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(papersController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/rejectedpapers"))
               .andExpect(MockMvcResultMatchers.view().name("rejectedpapers.jsp"));
    }
}
