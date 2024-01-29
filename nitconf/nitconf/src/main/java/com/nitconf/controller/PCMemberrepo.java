package com.nitconf.controller;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

import com.nitconf.model.PCMember;

public interface PCMemberrepo extends JpaRepository<PCMember ,Integer>{
	boolean existsByEmailAndPassword(String email, String password);
	PCMember findByEmail(String email);
	boolean existsByEmail(String email);
}
