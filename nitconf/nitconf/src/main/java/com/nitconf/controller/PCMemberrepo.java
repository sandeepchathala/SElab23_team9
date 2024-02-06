package com.nitconf.controller;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nitconf.model.PCMember;

public interface PCMemberrepo extends JpaRepository<PCMember ,Long>{
	boolean existsByEmailAndPassword(String email, String password);
	PCMember findByEmail(String email);
	boolean existsByEmail(String email);
	
	@Modifying
	@Query("UPDATE PCMember SET name=:name,email=:email,password=:password WHERE id=:id")
	void setvalues(@Param("id") long id,@Param("name") String name,@Param("email") String email,@Param("password") String password);
}
