package com.SElab23_team9.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SElab23_team9.demo.model.PCMember;

public interface PCMemberrepo extends JpaRepository<PCMember,Integer>{
	boolean existsByEmailAndPassword(String email, String password);
	PCMember findByEmail(String email);
	boolean existsByEmail(String email);

}

