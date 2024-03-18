package com.nitconf.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.nitconf.model.PaperReviewer;

public interface PaperReviewerrepo extends JpaRepository<PaperReviewer,Long>{
	@Query("SELECT t,p,r FROM PaperReviewer t "+
	        "JOIN Paper p ON p.id=t.paper_id "+
		    "JOIN Reviewer r ON r.id=t.reviewer_id "+
	        "WHERE t.paper_id =:paper_id AND p.status = 3")
    public List<Object[]> getreview_accept(Long paper_id);
    
	@Query("SELECT t,p,r FROM PaperReviewer t "+
	        "JOIN Paper p ON p.id=t.paper_id "+
		    "JOIN Reviewer r ON r.id=t.reviewer_id "+
	        "WHERE t.paper_id =:paper_id AND p.status = 4")
    public List<Object[]> getreview_reject(Long paper_id);
    
	@Query("SELECT t,r FROM PaperReviewer t "+
	        "JOIN Paper p ON p.id=t.paper_id "+
		    "JOIN Reviewer r ON r.id=t.reviewer_id "+
	        "WHERE t.paper_id =:paper_id AND p.status = 2")
    public List<Object[]> showreview(Long paper_id);
}
