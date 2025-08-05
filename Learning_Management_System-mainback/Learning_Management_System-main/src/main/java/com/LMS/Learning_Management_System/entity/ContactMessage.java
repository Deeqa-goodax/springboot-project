package com.LMS.Learning_Management_System.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_messages")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(length = 1000)
    private String message;

    private String sentAt = java.time.LocalDateTime.now().toString();

    public ContactMessage() {
    }

    public ContactMessage(Long id, String name, String email, String message, String sentAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.sentAt = sentAt;
    }

    public ContactMessage(String name, String email, String message) {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}
