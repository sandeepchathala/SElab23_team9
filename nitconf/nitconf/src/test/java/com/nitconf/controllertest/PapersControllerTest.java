package com.nitconf.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PapersController.class)
class PapersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAssignedPapers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/assignedpapers"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("assignedpapers.jsp"));
    }

    @Test
    void testGetReviewedPapers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/reviewedpapers"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("reviewedpapers.jsp"));
    }

    @Test
    void testGetUnreviewedPapers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/unreviewedpapers"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("unreviewedpapers.jsp"));
    }

    @Test
    void testGetUnassignedPapers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/unassignedpapers"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("unassignedpapers.jsp"));
    }

        @Test
    void testGetAcceptedPapers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/acceptedpapers"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("acceptedpapers.jsp"));
    }

    @Test
    void testGetRejectedPapers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/rejectedpapers"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("rejectedpapers.jsp"));
    }
}
