package com.nitconf.controller;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nitconf.model.Reviewer;

public interface Reviewerrepo extends JpaRepository<Reviewer,Long>{
     List<Reviewer> findByTags(String tags);

     @Query("SELECT r FROM Reviewer r WHERE r.tags=:selectedtag")
     public List<Reviewer> getReviewersbytag(String selectedtag);
}

