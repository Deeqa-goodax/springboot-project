package com.LMS.Learning_Management_System.dto;

import java.util.Date;

public class NotificationDto {
    private int notificationId;
    private int userId;
    private String message;
    private Date createdTime;
    private boolean read;

    public NotificationDto() {}

    public NotificationDto(int notificationId, int userId, String message, Date createdTime, boolean read) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.createdTime = createdTime;
        this.read = read;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
