package com.nitconf.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nitconf.controller.FileRepository;
import com.nitconf.model.Files;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public String storeFile(MultipartFile file,String auth )throws IOException {
        try {
            // Validate if the file is empty
            if (file.isEmpty()) {
                return "File is empty";
            }

            // Create a new Files entity using builder pattern
            Files files = Files.builder()
                    .title(file.getOriginalFilename())
                    .author(auth) // Set the author value as needed
                    .tags(file.getContentType())
                    .paperData(file.getBytes())
                    .build();

            // Save the entity to the database
            files = fileRepository.save(files);

            if (files.getId() != 0) {
            	System.out.println("ascashncasuxc asuc");
                return "File uploaded successfully";
            } else {
                return "Failed to upload file";
            }
        } catch (IOException e) {
            // Handle the IO exception (e.g., log it) and return an error message
            return "Error storing the file";
        }
    }
}