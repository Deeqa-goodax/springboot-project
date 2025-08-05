package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.NotificationDto;
import com.LMS.Learning_Management_System.entity.Notifications;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.repository.NotificationRepository;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationsService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UsersRepository usersRepository;

    // Dummy method to simulate current user from request
    private Users getAuthenticatedUser(HttpServletRequest request) {
        int dummyUserId = 1;
        return usersRepository.findById((long) dummyUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void sendNotification(NotificationDto dto, HttpServletRequest request) {
        Users user = usersRepository.findById((long) dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Notifications notification = new Notifications();
        notification.setUserId(user);
        notification.setMessage(dto.getMessage());
        notification.setCreatedTime(new Date());
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    public List<NotificationDto> getNotificationsForUser(HttpServletRequest request) {
        Users user = getAuthenticatedUser(request);
        List<Notifications> notifications = notificationRepository.findByUserIdOrderByCreatedTimeDesc(user);

        return notifications.stream()
                .map(n -> new NotificationDto(
                        n.getNotificationsId(),
                        Math.toIntExact(n.getUserId().getUserId()),
                        n.getMessage(),
                        n.getCreatedTime(),
                        n.isRead()))
                .collect(Collectors.toList());
    }

    public void deleteNotification(int notificationId, HttpServletRequest request) {
        Users user = getAuthenticatedUser(request);

        Optional<Notifications> optional = notificationRepository.findById(notificationId);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Notification not found");
        }

        Notifications notification = optional.get();
        if (notification.getUserId().getUserId() != user.getUserId()) {
            throw new IllegalArgumentException("Unauthorized access");
        }

        notificationRepository.delete(notification);
    }
}
