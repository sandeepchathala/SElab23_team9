package com.nitconf.mailController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitconf.services.EmailSenderService;

@Controller
@SpringBootApplication
public class EmailSenderApplication {

    @Autowired
    private EmailSenderService senderservice;

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApplication.class, args);
    }

//    @PostMapping("/api/actions/accept")
//    public String submitAcceptForm(@RequestParam("toEmail") String toEmail,
//                                   @RequestParam("subject") String subject,
//                                   @RequestParam("body") String body,
//                                   Model model) {
//        senderservice.sendEmail(toEmail, subject, body);
//        System.out.println("Email sent successfully!");
//        return "accept";
//    }
}