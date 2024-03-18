package com.nitconf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nitconf.controller.PCMemberrepo;
import com.nitconf.model.CustomPCMember;
import com.nitconf.model.PCMember;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private PCMemberrepo PCrepo; 

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		PCMember user = PCrepo.findByUsername(username);
		
		if(user== null)
			throw new UsernameNotFoundException("Username Not Found");
		System.out.println("security user details");
		return new CustomPCMember(user);
	} 
	
	
}
