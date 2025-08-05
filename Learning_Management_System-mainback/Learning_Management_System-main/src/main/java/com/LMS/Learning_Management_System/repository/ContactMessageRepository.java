package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
