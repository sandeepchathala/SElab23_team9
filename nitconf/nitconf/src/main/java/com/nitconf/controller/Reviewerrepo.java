package com.nitconf.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitconf.model.Reviewer;

public interface Reviewerrepo extends JpaRepository<Reviewer,Long>{
     List<Reviewer> findByTags(String tags);
}

