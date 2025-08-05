package com.LMS.Learning_Management_System.dto;

public class StudentDto {
    private int userAccountId;
    private String firstName;
    private String lastName;
    private String email;

    public StudentDto() {}

    public StudentDto(int userAccountId, String firstName, String lastName, String email) {
        this.userAccountId = userAccountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
