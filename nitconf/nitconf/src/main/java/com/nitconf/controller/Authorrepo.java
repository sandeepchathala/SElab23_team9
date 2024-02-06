package com.nitconf.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitconf.model.Author;

public interface Authorrepo extends JpaRepository<Author,Long>{
   //Author getById(Long id);
}
