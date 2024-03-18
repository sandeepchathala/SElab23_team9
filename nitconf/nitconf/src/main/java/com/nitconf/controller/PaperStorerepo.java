package com.nitconf.controller;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.nitconf.model.Paper;

public interface PaperStorerepo extends JpaRepository<Paper,Long>{
     Paper findById(long id);
     
     @Modifying
     @Query("UPDATE Paper p SET p.status =:status WHERE p.id=:id")
     void setstatus(Long id,int status);

     List<Paper> findByStatus(int status);
     
     @Query("SELECT DISTINCT p.tags FROM Paper p")
     List<String> getalltags();
     
     @Query("SELECT p FROM Paper p WHERE p.tags=:selectedTag AND p.status=0")
     public List<Paper> getpapersbytags(String selectedTag);
}
