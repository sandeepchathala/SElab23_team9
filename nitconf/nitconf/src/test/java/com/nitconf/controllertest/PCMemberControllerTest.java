package com.nitconf.controllertest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.controller.PCMemberController;
import com.nitconf.controller.PCMemberrepo;
import com.nitconf.model.PCMember;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PCMemberControllerTest {

    @InjectMocks
    private PCMemberController pcMemberController;

    @Mock
    private PCMemberrepo PCrepo;

    @Test
    void testGetLogin() {
        ModelAndView mav = (ModelAndView) pcMemberController.getLogin();
        assertEquals("login.jsp", mav.getViewName());
    }

    
    @Test
    void testGetDashboard_Success() {
        // Given
        String email = "sandeepchathala007@gmail.com";
        String password = "1234";
        PCMember pcMember = new PCMember(); // create a PCMember instance with appropriate data
        pcMember.setName("Sandeep Chathala"); // set the name for the PCMember

        // Mock the behavior of PCrepo
        when(PCrepo.existsByEmailAndPassword(email, password)).thenReturn(true);
        when(PCrepo.findByEmail(email)).thenReturn(pcMember);

        // Create an instance of Model
        Model model = mock(Model.class);

        // When
        ModelAndView mav = pcMemberController.getdashboard(email, password, model);

        // Then
        assertEquals("dashboard.jsp", mav.getViewName());
        assertEquals("Sandeep Chathala", model.getAttribute("name"));
        // Add more assertions based on your controller logic if needed
    }


    @Test
    void testGetDashboard_Failure() {
        when(PCrepo.existsByEmailAndPassword(anyString(), anyString())).thenReturn(false);

        ModelAndView mav = pcMemberController.getdashboard("test@gmail.com", "wrongPassword", Mockito.mock(Model.class));

        assertEquals("loginerror.jsp", mav.getViewName());
    }

    @Test
    void testGetProfile() {
        // Given
        // Mock the current PCMember
        PCMember currentpc = new PCMember();
        currentpc.setName("Sandeep Chathala");
        currentpc.setEmail("sandeepchathala007@example.com");
        currentpc.setPassword("1234");

        // Mock the behavior of Model
        Model model = mock(Model.class);

        // When
        ModelAndView mav = pcMemberController.getprofile(model);

        // Then
        assertEquals("profile.jsp", mav.getViewName());
        assertEquals("Sandeep Chathala", model.getAttribute("name"));
        assertEquals("sandeepchathala007@example.com", model.getAttribute("email"));
        assertEquals("1234", model.getAttribute("password"));
    }

    @Test
    void testEditProfile() {
        // Given
        // Mock the behavior of Model
        Model model = mock(Model.class);

        // When
        ModelAndView mav = pcMemberController.editprofile(model);

        // Then
        assertEquals("editprofile.jsp", mav.getViewName());
        // Add additional assertions based on your controller logic
    }


    @Test
    void testUpdateProfile_PasswordMatch() {
        // Given
        PCMember existingPCMember = new PCMember();
        existingPCMember.setId(1L); // Set appropriate ID
        existingPCMember.setName("Existing Name");
        existingPCMember.setEmail("existing@example.com");
        existingPCMember.setPassword("oldpassword"); // Set existing password

        // Mock the behavior of PCrepo.findById()
        PCMemberrepo PCrepo = mock(PCMemberrepo.class);
        when(PCrepo.findById(anyLong())).thenReturn(Optional.of(existingPCMember));

        // Set currentpc
        PCMemberController pcMemberController = new PCMemberController();
        pcMemberController.setCurrentpc(existingPCMember);

        // Create an instance of Model
        Model model = mock(Model.class);

        // When
        ModelAndView mav = pcMemberController.updateprofile(null, "New Name", "newemail@example.com", "newpassword", "newpassword", model);

        // Then
        assertEquals("redirect:/api/pcmember/Profile", mav.getViewName());
        verify(PCrepo).setvalues(anyLong(), anyString(), anyString(), anyString());
        verify(PCrepo).findById(anyLong());
    }

    @Test
    void testUpdateProfile_PasswordMismatch() {
        // Given
        PCMember existingPCMember = new PCMember();
        existingPCMember.setId(1L); // Set appropriate ID
        existingPCMember.setName("Existing Name");
        existingPCMember.setEmail("existing@example.com");
        existingPCMember.setPassword("oldpassword"); // Set existing password

        // Mock the behavior of PCrepo.findById()
        PCMemberrepo PCrepo= mock(PCMemberrepo.class);
        when(PCrepo.findById(anyLong())).thenReturn(Optional.of(existingPCMember));

        // Set currentpc
        PCMemberController pcMemberController = new PCMemberController();
        pcMemberController.setCurrentpc(existingPCMember);

        // Create an instance of Model
        Model model = mock(Model.class);

        // When
        ModelAndView mav = pcMemberController.updateprofile(null, "New Name", "newemail@example.com", "newpassword", "wrongpassword", model);

        // Then
        assertEquals("confirmpassworderror.jsp", mav.getViewName());
        verify(PCrepo, never()).setvalues(anyLong(), anyString(), anyString(), anyString());
        verify(PCrepo, never()).findById(anyLong());
    }

    
}
