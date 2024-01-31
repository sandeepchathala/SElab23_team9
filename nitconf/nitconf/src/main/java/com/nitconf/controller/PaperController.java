


package com.nitconf.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nitconf.service.FileService;

@RestController
public class PaperController {
	
	@Autowired
	private FileService filesService;
	
	@PostMapping("/Uploadpaper")
	public ResponseEntity<String> storeFilesInDB(@RequestParam String author,@RequestParam("file") MultipartFile file) throws IOException {
		String response = filesService.storeFile(file,author);
		System.out.println("adfidfndif");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

			
			
	
	

}
