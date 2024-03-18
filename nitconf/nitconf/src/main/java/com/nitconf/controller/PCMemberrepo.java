package com.nitconf.controller;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nitconf.model.PCMember;

public interface PCMemberrepo extends JpaRepository<PCMember ,Long>{
	boolean existsByUsernameAndPassword(String email, String password);
	PCMember findByUsername(String email);
	boolean existsByUsername(String username);
	
	@Modifying
	@Query("UPDATE PCMember SET name=:name,username=:username,phone=:phone,password=:password WHERE id=:id")
	void setvalues(@Param("id") long id,@Param("name") String name,@Param("username") String username,@Param("phone") Long phone,@Param("password") String password);
	
}
