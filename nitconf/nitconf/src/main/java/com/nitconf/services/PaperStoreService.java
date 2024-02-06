package com.nitconf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nitconf.controller.PaperStorerepo;
import com.nitconf.model.Paper;

@Service
public class PaperStoreService {
	
    @Autowired
    PaperStorerepo PSrepo;
    
    public List<Paper>getallfiles(){
    	List<Paper> ee=PSrepo.findAll();
        return ee;
    }
}
