
package com.nitconf.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.security.Principal;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.PCMember;

public class PCMemberControllerTest {
    
    @Mock
    private PCMemberrepo PCrepo;

    @InjectMocks
    private PCMemberController controller;
    
    //@Mock
    private PCMember mockedUser;
    
    @Mock
    private Principal principal;

    @Mock
    private SecurityContextHolder securityContextHolder;
    
    @Mock
    private Authentication authent;
    
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    
    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        
        mockedUser = new PCMember();
        mockedUser.setName("sandeep");
        mockedUser.setId(1L);
        mockedUser.setUsername("sandeep@gmail.com");
        mockedUser.setPhone(1234567890L);
        mockedUser.setPassword("1234");
        //authent.
        //securityContext.setAuthentication(mockedUser);
        authent.setAuthenticated(true);
        securityContextHolder.getContext().setAuthentication(authent);
    }

    @Test
    void testGetdashboard() {
        
    	when(principal.getName()).thenReturn(mockedUser.getUsername());
        when(PCrepo.findByUsername("sandeep@gmail.com")).thenReturn(mockedUser);

        //Principal principal = mock(Principal.class);
        Model model = mock(Model.class);
        //HttpSession session = mock(HttpSession.class);

        ModelAndView mav = controller.getdashboard(principal, model, session);

        verify(model).addAttribute("user", mockedUser);
        // Assuming "dashboard.jsp" is the correct view name
        assertEquals("dashboard.jsp",mav.getViewName());
    }

    @Test
    void testGetprofile() {
    	when(securityContextHolder.getContext().getAuthentication().getName()).thenReturn(mockedUser.getUsername());
        when(PCrepo.findByUsername(mockedUser.getUsername())).thenReturn(mockedUser);

        Model model = mock(Model.class);

        ModelAndView mav = controller.getprofile(model);

        verify(model).addAttribute("name", "sandeep");
        verify(model).addAttribute("email", "sandeep@gmail.com");
        verify(model).addAttribute("phone", 1234567890L);
        // Assuming "profile.jsp" is the correct view name
        assertEquals("profile.jsp",mav.getViewName());
    }

    @Test
    void testEditprofile() {
    	when(securityContextHolder.getContext().getAuthentication().getName()).thenReturn(mockedUser.getUsername());
        when(PCrepo.findByUsername(mockedUser.getUsername())).thenReturn(mockedUser);
        
        Model model = mock(Model.class);

        ModelAndView mav = controller.editprofile(model);

        verify(model).addAttribute("name", "sandeep");
        verify(model).addAttribute("email", "sandeep@gmail.com");
        verify(model).addAttribute("phone", 1234567890L);
        // Assuming "editprofile.jsp" is the correct view name
        assertEquals("editprofile.jsp",mav.getViewName());
    }

    @Test
    void testUpdateprofile_Success() {
    	PCMember newpc = new PCMember();
        newpc.setName("New Name");
        newpc.setUsername("newemail@example.com");
        newpc.setPhone(1234567890L);
        newpc.setPassword("password");
        String confirmpassword = "password";
        
        //HttpSession session = mock(HttpSession.class);
    	when(securityContextHolder.getContext().getAuthentication().getName()).thenReturn(mockedUser.getUsername());
        when(PCrepo.findByUsername(mockedUser.getUsername())).thenReturn(mockedUser);
        when(passwordEncoder.encode("password")).thenReturn("password");
        
        ModelAndView mav = controller.updateprofile(session, newpc, newpc.getName(),newpc.getUsername(),newpc.getPhone(),confirmpassword,newpc.getPassword(), mock(Model.class));
        
        verify(PCrepo).setvalues(eq(1L), eq(newpc.getName()), eq(newpc.getUsername()), eq(newpc.getPhone()), eq(newpc.getPassword()));
        assertEquals("redirect:/api/pcmember/Profile",mav.getViewName());
    }
    @Test
    void testUpdateprofile_Failure() {
    	when(securityContextHolder.getContext().getAuthentication().getName()).thenReturn(mockedUser.getUsername());
        when(PCrepo.findByUsername(mockedUser.getUsername())).thenReturn(mockedUser);

        //HttpSession session = mock(HttpSession.class);
        PCMember updatedData = new PCMember();
        ModelAndView mav = controller.updateprofile(session, updatedData, "ch sandeep", "sandeep@gmail.com", 1234567890L, "confirmpassword", "password", mock(Model.class));
        
        //assertEquals("Entered passwords are not matching",session.getAttribute("confirmpassword_error_msg"));
        //assert session.getAttribute("confirmpassword_error_msg").equals("Entered passwords are not matching");
        //verify(session, never()).setAttribute(eq("confirmpassword_error_msg"), eq("Entered passwords are not matching"));        
        //verify(session, never()).setAttribute(anyString(), anyString());
        //verify(session, never()).setAttribute(eq("confirmpassword_error_msg"), anyString());
        assertEquals("redirect:/api/pcmember/EditProfile",mav.getViewName());
    }

}
