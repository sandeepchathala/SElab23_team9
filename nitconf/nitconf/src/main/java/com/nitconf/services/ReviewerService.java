package com.nitconf.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitconf.controller.Reviewerrepo;
import com.nitconf.model.Reviewer;

@Service
public class ReviewerService {
	@Autowired
	Reviewerrepo Rrepo;
	
	public List<Reviewer> findReviewersByTags(String tags) {
        // Your logic to fetch reviewers based on tags from the database
        // This can be a JPA repository call or any other method
        // Example: return reviewerRepository.findByTags(tags);
		return Rrepo.findByTags(tags);
        //return list; // Placeholder, replace with actual logic
   
    }
	public Optional<Reviewer> findbyid(Long id){
		return Rrepo.findById(id);
	}
} 