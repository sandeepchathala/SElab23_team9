package com.nitconf.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitconf.model.PaperReviewer;

public interface PaperReviewerrepo extends JpaRepository<PaperReviewer,Long>{

}
