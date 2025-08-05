package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.NotificationDto;
import com.LMS.Learning_Management_System.service.NotificationsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private final NotificationsService notificationService;

    public NotificationController(NotificationsService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto,
                                                   HttpServletRequest request) {
        try {
            notificationService.sendNotification(notificationDto, request);
            return ResponseEntity.ok("Notification sent successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getNotificationsForUser(HttpServletRequest request) {
        try {
            List<NotificationDto> notifications = notificationService.getNotificationsForUser(request);
            return ResponseEntity.ok(notifications);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable int notificationId,
                                                     HttpServletRequest request) {
        try {
            notificationService.deleteNotification(notificationId, request);
            return ResponseEntity.ok("Notification deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
