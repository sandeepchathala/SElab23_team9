package com.nitconf.controller;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitconf.model.Paper;

public interface PaperStorerepo extends JpaRepository<Paper,Long>{
     Paper findById(long id);
     
     @Modifying
     @Query("UPDATE Paper p SET p.status =:status WHERE p.id=:id")
     void setstatus(@Param("id") long id,@Param("status") int status);
}