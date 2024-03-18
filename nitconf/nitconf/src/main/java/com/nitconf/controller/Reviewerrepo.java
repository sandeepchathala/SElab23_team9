package com.nitconf.controller;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nitconf.model.Reviewer;

public interface Reviewerrepo extends JpaRepository<Reviewer,Long>{
     List<Reviewer> findByTags(String tags);

     @Query("SELECT r FROM Reviewer r WHERE r.tags=:selectedtag")
     public List<Reviewer> getReviewersbytag(String selectedtag);
     
 	@Query("SELECT r FROM Reviewer r "+
 	       "WHERE r.tags=:tag AND r NOT IN "+
 		   "(SELECT rr FROM PaperReviewer t "+
 	       "JOIN Paper p ON p.id=t.paper_id "+
 		   "JOIN Reviewer rr ON rr.id=t.reviewer_id "+
 	       "WHERE t.paper_id=:paper_id)")
    public List<Reviewer> getextrareviewers(Long paper_id,String tag);
}
