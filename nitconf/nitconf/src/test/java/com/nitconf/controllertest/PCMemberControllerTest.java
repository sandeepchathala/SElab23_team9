package com.nitconf.controller;

import java.util.Optional;
import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.nitconf.model.PCMember;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import jakarta.servlet.http.HttpSession;
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@WebMvcTest(PCMemberController.class)
class PCMemberControllerTest {

  @InjectMocks
  private PCMemberController controller;
	
	@MockBean
	private PCMemberrepo PCrepo;
  @Mock
  private Model model;
  @MockBean
  private PCMember currentpc;
  
  @BeforeEach
  public void setUp() {
      MockitoAnnotations.openMocks(this);
      currentpc = new PCMember();
      currentpc.setId(1L);
      currentpc.setUsername("banothbalaji1729@gmail.com");
      currentpc.setName("balaji");
      currentpc.setPassword("1234");
      currentpc.setPhone(1234567890L);
  }
  

  @Test
  void testGetDashboard_ValidCredentials() {
      // Given
      String email = "banothbalaji1729@gmail.com";
      String password = "1234";
      

      // Mock the behavior of PCrepo
      when(PCrepo.existsByUsernameAndPassword(email, password)).thenReturn(true);
 
      // When
      Principal principal; 
      HttpSession session;
      ModelAndView mav = controller.getdashboard(principal, model, session);
      //controller.currentpc=currentpc;
      // Then
      assertEquals("dashboard.jsp", mav.getViewName());
      // assertEquals(email, currentpc.getEmail());
      // assertEquals(password, currentpc.getPassword());
      
  }

      @Test
      public void testGetDashboard_InvalidCredentials() {
          String email = "banothbalaji1729@gmail.com";
          String password = "1234";

          // Mock behavior of repository
          // Mock the beavior of PCrepo
          when(PCrepo.existsByUsernameAndPassword(email, password)).thenReturn(false);
         Principal principal; 
         HttpSession session;
         ModelAndView mav = controller.getdashboard(principal, model, session);
         assertEquals("loginerror.jsp", mav.getViewName());
      }
      @Test
      public void testGetProfile_Success() {
          // Mocking email
          PCMember dummyPCMember = new PCMember();
	      dummyPCMember.setId(1L);
	      dummyPCMember.setName("dummy");
	      dummyPCMember.setUsername("dummy@gmail.com");
	      dummyPCMember.setPhone(1234567890L);
	      dummyPCMember.setPassword("1234");
          // Stubbing the behavior of PCrepo to return the dummy PCMember when findByEmail is called
            when(PCrepo.findByUsername(dummyPCMember.setUsername())).thenReturn(currentpc);
            ModelAndView mav = controller.getprofile(new ExtendedModelMap()); // Use ModelMap here

          // Asserting the expected view name
          assertEquals("profile.jsp", mav.getViewName());
          // Asserting that the attributes are added to the model correctly
          assertEquals(dummyPCMember.getName(), currentpc.getName());
          assertEquals(dummyPCMember.getUsername(), currentpc.getUsername());
          assertEquals(dummyPCMember.getPassword(),currentpc.getPassword());
	  assertEquals(dummyPCMember.getPhone(),currentpc.getPhone());
      }
      @Test
      public void testEditProfile_Success() {
          // Stubbing the behavior of currentpc
          //controller.currentpc = currentpc;

          // Create a ModelMap and add attributes
          ExtendedModelMap model = new ExtendedModelMap();
          ModelAndView mav = controller.editprofile(model);

          // Assert the view name
          assertEquals("editprofile.jsp", mav.getViewName());
          // Assert the attributes added to the model
          assertEquals(currentpc.getName(), model.get("name"));
          assertEquals(currentpc.getUsername(), model.get("email"));
          assertEquals(currentpc.getPhone(), model.get("phone"));
      }
      @Test
      public void testUpdateProfile_Success() {
          // Mocking the request parameters
          String name = "balaji";
          String email = "banothbalaji1729@gmail.com";
	      Long phone = 1234567890L;
          String password = "1234";
          String confirmPassword = "1234";

          //controller.currentpc =currentpc;

          // Stubbing the behavior of PCrepo
          when(PCrepo.findById(currentpc.getId())).thenReturn(Optional.of(currentpc));
          ExtendedModelMap model = new ExtendedModelMap();
          HttpSession session;
          // Perform the request to update the profile
          ModelAndView mav = controller.updateprofile(session,new PCMember(), name, email,phone, confirmPassword, password, new ExtendedModelMap());

          // Assert the expected redirection view name
          assertEquals("redirect:/api/pcmember/Profile", mav.getViewName());

          // Assert that the profile data is updated correctly
          assertEquals(name, currentpc.getName());
          assertEquals(email, currentpc.getUsername());
	      assertEquals(phone, currentpc.getPhone());
          assertEquals(password, currentpc.getPassword());
      }
      @Test
      public void testUpdateProfile_ConfirmPasswordError() {
          // Mocking the request parameters
          String name = "balaji";
          String email = "banothbalaji1729@gmail.com";
          String password = "1234";
	      Long phone = 1234567890L;
          String confirmPassword = "123";

          // Stubbing the behavior of currentpc
          //controller.currentpc = currentpc;
          HttpSession session;
          // Perform the request to update the profile
          ModelAndView mav = controller.updateprofile(session,currentpc, name, email,phone, confirmPassword, password, new ExtendedModelMap());

          // Assert the expected view name for password confirmation error
          assertEquals("confirmpassworderror.jsp", mav.getViewName());
          assertEquals(name, currentpc.getName());
          assertEquals(email, currentpc.getUsername());
	      assertEquals(phone, currentpc.getPhone());
          assertEquals(password, currentpc.getPassword());
          
      } 

}
