package com.LMS.Learning_Management_System.dto;

import com.LMS.Learning_Management_System.entity.UsersType;

import java.util.Date;

public class UsersDto {
    private int userId;
    private String email;
    private UsersType userType;
    private Date registrationDate;

    public UsersDto() {}

    public UsersDto(int userId, String email, UsersType userType, Date registrationDate) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
        this.registrationDate = registrationDate;
    }

    public UsersDto(int intExact, String email, String userType, Date registrationDate) {
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UsersType getUserType() {
        return userType;
    }
    public void setUserType(UsersType userType) {
        this.userType = userType;
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
