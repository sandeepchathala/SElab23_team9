//package com.nitconf.controllertest;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.nitconf.controller.PCMemberController;
//import com.nitconf.controller.PCMemberrepo;
//import com.nitconf.model.PCMember;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//class PCMemberControllerTest {
//
//    @InjectMocks
//    private PCMemberController pcMemberController;
//
//    @Mock
//    private PCMemberrepo PCrepo;
//
//    @Test
//    void testGetLogin() {
//        ModelAndView mav = (ModelAndView) pcMemberController.getLogin();
//        assertEquals("login.jsp", mav.getViewName());
//    }
//
//    
//    @Test
//    void testGetDashboard_Success() {
//        // Given
//        String email = "sandeepchathala007@gmail.com";
//        String password = "1234";
//        PCMember pcMember = new PCMember(); // create a PCMember instance with appropriate data
//        pcMember.setName("Sandeep Chathala"); // set the name for the PCMember
//
//        // Mock the behavior of PCrepo
//        when(PCrepo.existsByEmailAndPassword(email, password)).thenReturn(true);
//        when(PCrepo.findByEmail(email)).thenReturn(pcMember);
//
//        // Create an instance of Model
//        Model model = mock(Model.class);
//
//        // When
//        ModelAndView mav = pcMemberController.getdashboard(email, password, model);
//
//        // Then
//        assertEquals("dashboard.jsp", mav.getViewName());
//        assertEquals("Sandeep Chathala", model.getAttribute("name"));
//        // Add more assertions based on your controller logic if needed
//    }
//
//
//    @Test
//    void testGetDashboard_Failure() {
//        when(PCrepo.existsByEmailAndPassword(anyString(), anyString())).thenReturn(false);
//
//        ModelAndView mav = pcMemberController.getdashboard("test@gmail.com", "wrongPassword", Mockito.mock(Model.class));
//
//        assertEquals("loginerror.jsp", mav.getViewName());
//    }
//
//    @Test
//    void testGetProfile() {
//        // Given
//        // Mock the current PCMember
//        PCMember currentpc = new PCMember();
//        currentpc.setName("Sandeep Chathala");
//        currentpc.setEmail("sandeepchathala007@example.com");
//        currentpc.setPassword("1234");
//
//        // Mock the behavior of Model
//        Model model = mock(Model.class);
//
//        // When
//        ModelAndView mav = pcMemberController.getprofile(model);
//
//        // Then
//        assertEquals("profile.jsp", mav.getViewName());
//        assertEquals("Sandeep Chathala", model.getAttribute("name"));
//        assertEquals("sandeepchathala007@example.com", model.getAttribute("email"));
//        assertEquals("1234", model.getAttribute("password"));
//    }
//
//    @Test
//    void testEditProfile() {
//        // Given
//        // Mock the behavior of Model
//        Model model = mock(Model.class);
//
//        // When
//        ModelAndView mav = pcMemberController.editprofile(model);
//
//        // Then
//        assertEquals("editprofile.jsp", mav.getViewName());
//        // Add additional assertions based on your controller logic
//    }
//
//
//    @Test
//    void testUpdateProfile_PasswordMatch() {
//        // Given
//        PCMember existingPCMember = new PCMember();
//        existingPCMember.setId(1L); // Set appropriate ID
//        existingPCMember.setName("Existing Name");
//        existingPCMember.setEmail("existing@example.com");
//        existingPCMember.setPassword("oldpassword"); // Set existing password
//
//        // Mock the behavior of PCrepo.findById()
//        PCMemberrepo PCrepo = mock(PCMemberrepo.class);
//        when(PCrepo.findById(anyLong())).thenReturn(Optional.of(existingPCMember));
//
//        // Set currentpc
//        PCMemberController pcMemberController = new PCMemberController();
//        pcMemberController.setCurrentpc(existingPCMember);
//
//        // Create an instance of Model
//        Model model = mock(Model.class);
//
//        // When
//        ModelAndView mav = pcMemberController.updateprofile(null, "New Name", "newemail@example.com", "newpassword", "newpassword", model);
//
//        // Then
//        assertEquals("redirect:/api/pcmember/Profile", mav.getViewName());
//        verify(PCrepo).setvalues(anyLong(), anyString(), anyString(), anyString());
//        verify(PCrepo).findById(anyLong());
//    }
//
//    @Test
//    void testUpdateProfile_PasswordMismatch() {
//        // Given
//        PCMember existingPCMember = new PCMember();
//        existingPCMember.setId(1L); // Set appropriate ID
//        existingPCMember.setName("Existing Name");
//        existingPCMember.setEmail("existing@example.com");
//        existingPCMember.setPassword("oldpassword"); // Set existing password
//
//        // Mock the behavior of PCrepo.findById()
//        PCMemberrepo PCrepo= mock(PCMemberrepo.class);
//        when(PCrepo.findById(anyLong())).thenReturn(Optional.of(existingPCMember));
//
//        // Set currentpc
//        PCMemberController pcMemberController = new PCMemberController();
//        pcMemberController.setCurrentpc(existingPCMember);
//
//        // Create an instance of Model
//        Model model = mock(Model.class);
//
//        // When
//        ModelAndView mav = pcMemberController.updateprofile(null, "New Name", "newemail@example.com", "newpassword", "wrongpassword", model);
//
//        // Then
//        assertEquals("confirmpassworderror.jsp", mav.getViewName());
//        verify(PCrepo, never()).setvalues(anyLong(), anyString(), anyString(), anyString());
//        verify(PCrepo, never()).findById(anyLong());
//    }
//
//    
//}

package com.nitconf.controllertest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nitconf.controller.PCMemberController;
import com.nitconf.controller.PCMemberrepo;
import com.nitconf.model.PCMember;

//import org.junit.Before;
//import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@WebMvcTest(PCMemberController.class)
class PCMemberControllerTest {

    @InjectMocks
    private PCMemberController pcmemcontroller;
	@Autowired
    private MockMvc mockMvc;	
	@MockBean
	private PCMemberrepo PCrepo;
    @Mock
    private ExtendedModelMap model;

    private PCMember currentpc;
    
    @BeforeEach
    public void setUp() {
    	
        MockitoAnnotations.openMocks(this);
        //mockMvc = MockMvcBuilders.standaloneSetup(pcmemcontroller).build();
        currentpc = new PCMember();
        currentpc.setId(1L);
        currentpc.setEmail("sandeep@gmail.com");
        currentpc.setName("sandeep");
        currentpc.setPassword("1234");
    }
    
    @Test
    void testGetLogin() {
        ModelAndView mav = pcmemcontroller.getLogin();
        assertEquals("login.jsp", mav.getViewName());
    }

    @Test
    void testGetDashboard_ValidCredentials() {// set the name for the PCMember

        // Mock the behavior of PCrepo
        when(PCrepo.existsByEmailAndPassword(currentpc.getEmail(), currentpc.getPassword())).thenReturn(true);
        when(PCrepo.findByEmail(currentpc.getEmail())).thenReturn(currentpc);

        // When
        ModelAndView mav = pcmemcontroller.getdashboard(currentpc.getEmail(), currentpc.getPassword(), model);

        // Then
        assertEquals("dashboard.jsp", mav.getViewName());
        // Add more assertions based on your controller logic if needed
    }

        @Test
        public void testGetDashboard_InvalidCredentials() {
            when(PCrepo.existsByEmailAndPassword(currentpc.getEmail(), currentpc.getPassword())).thenReturn(false);
            ModelAndView mav = pcmemcontroller.getdashboard(currentpc.getEmail(), currentpc.getPassword(), model);
            assertEquals("loginerror.jsp", mav.getViewName());
        }
        
        @Test
        public void testGetProfile() throws Exception {
            // Mocking currentpc object with test data
            pcmemcontroller.setCurrentpc(currentpc);

            // Mocking behavior for model object
            when(model.addAttribute("name", currentpc.getName())).thenReturn(model);
            when(model.addAttribute("email", currentpc.getEmail())).thenReturn(model);
            when(model.addAttribute("password", currentpc.getPassword())).thenReturn(model);

            // Act
            model = new ExtendedModelMap();
            ModelAndView modelAndView = pcmemcontroller.getprofile(model);
            //ExtendedModelMap m = 
            // Assert
            assertEquals("profile.jsp", modelAndView.getViewName());
            assertEquals(currentpc.getName(), model.getAttribute("name"));
            assertEquals(currentpc.getEmail(), model.getAttribute("email"));
            assertEquals(currentpc.getPassword(), model.getAttribute("password"));
        }


    @Test
    public void testEditProfile() throws Exception {
        pcmemcontroller.setCurrentpc(currentpc);

        // Mocking behavior for model object
        when(model.addAttribute("name", currentpc.getName())).thenReturn(model);
        when(model.addAttribute("email", currentpc.getEmail())).thenReturn(model);
        when(model.addAttribute("password", currentpc.getPassword())).thenReturn(model);

        // Act
        model = new ExtendedModelMap();
        ModelAndView modelAndView = pcmemcontroller.getprofile(model);
        //ExtendedModelMap m = 
        // Assert
        assertEquals("profile.jsp", modelAndView.getViewName());
        assertEquals(currentpc.getName(), model.getAttribute("name"));
        assertEquals(currentpc.getEmail(), model.getAttribute("email"));
        assertEquals(currentpc.getPassword(), model.getAttribute("password"));
    }

    @Test
    public void testUpdateProfile_Success() throws Exception {
    	String c_password="123";
    	String name="sandeep123";
    	String email="sandeep123@gmail.com";
    	String password="123";
        //Mockito.
    	if(password.equals(c_password));
        pcmemcontroller.setCurrentpc(currentpc);
        when(PCrepo.setvalues(currentpc.getId(), name, email, password)).thenReturn(true);
        when(PCrepo.findById(currentpc.getId())).thenReturn(Optional.of(currentpc));
        ModelAndView m = pcmemcontroller.updateprofile( name, email, c_password, password, model);
        assertEquals("redirect:/api/pcmember/Profile",m.getViewName());
    }
    @Test
    public void testUpdateProfile_Failure() throws Exception {
    	String c_password="1234";
    	String name="sandeep123";
    	String email="sandeep123@gmail.com";
    	String password="123";
        if(!password.equals(c_password));
        ModelAndView m = pcmemcontroller.updateprofile( name, email, c_password, password, model);
        assertEquals("confirmpassworderror.jsp",m.getViewName());
    
    }
}
