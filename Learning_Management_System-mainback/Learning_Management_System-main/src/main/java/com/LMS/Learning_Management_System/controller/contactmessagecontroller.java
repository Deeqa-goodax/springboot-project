package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.ContactMessageDto;
import com.LMS.Learning_Management_System.entity.ContactMessage;
import com.LMS.Learning_Management_System.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// ContactMessageController.java
@RestController
@RequestMapping("/api/contact")
public class contactmessagecontroller {

    @Autowired
    private ContactMessageRepository repository;

    @PostMapping
    public ResponseEntity<String> submitMessage(@RequestBody ContactMessageDto dto) {
        ContactMessage msg = new ContactMessage(dto.getName(), dto.getEmail(), dto.getMessage());
        repository.save(msg);
        return ResponseEntity.ok("Message sent successfully");
    }
}
