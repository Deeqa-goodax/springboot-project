package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.ContactMessageDto;
import com.LMS.Learning_Management_System.entity.ContactMessage;
import com.LMS.Learning_Management_System.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository repository;

    public void saveMessage(ContactMessageDto dto) {
        ContactMessage msg = new ContactMessage();
        msg.setName(dto.getName());
        msg.setEmail(dto.getEmail());
        msg.setMessage(dto.getMessage());
        repository.save(msg);
    }
}
