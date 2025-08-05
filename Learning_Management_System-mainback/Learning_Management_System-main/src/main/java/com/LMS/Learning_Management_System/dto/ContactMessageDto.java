package com.LMS.Learning_Management_System.dto;

public class ContactMessageDto {

    private String name;
    private String email;
    private String message;

    public ContactMessageDto() {
    }

    public ContactMessageDto(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    // Getters and Setters

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
}
