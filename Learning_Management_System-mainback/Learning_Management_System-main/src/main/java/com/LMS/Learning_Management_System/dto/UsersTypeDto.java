package com.LMS.Learning_Management_System.dto;

public class UsersTypeDto {
    private int userTypeId;
    private String userTypeName;

    public UsersTypeDto() {}

    public UsersTypeDto(int userTypeId, String userTypeName) {
        this.userTypeId = userTypeId;
        this.userTypeName = userTypeName;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}
