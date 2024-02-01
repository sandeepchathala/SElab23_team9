package com.nitconf.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitconf.model.Files;

public interface FileRepository extends JpaRepository<Files,Long> {
//
}
