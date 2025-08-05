package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all-users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            adminService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/make-admin/{userId}")
    public ResponseEntity<String> makeAdmin(@PathVariable int userId) {
        try {
            adminService.makeAdmin(userId);
            return ResponseEntity.ok("User promoted to admin.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
