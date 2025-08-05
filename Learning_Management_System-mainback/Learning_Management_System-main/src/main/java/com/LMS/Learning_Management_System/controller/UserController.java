package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.UsersDto;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsersService userService;

    @Autowired
    public UserController(UsersService userService) {
        this.userService = userService;
    }

    // ✅ Register new user (admin only)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user, HttpServletRequest request) {
        try {
            userService.registerUser(user, request);
            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Get all users (admin only)
    @GetMapping("/all")
    public ResponseEntity<List<UsersDto>> getAllUsers(HttpServletRequest request) {
        try {
            List<UsersDto> users = userService.getAllUsers(request);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ✅ Get single user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId, HttpServletRequest request) {
        try {
            UsersDto user = userService.getUserById(userId, request);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Update user details
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable int userId,
            @RequestBody Users updatedUser,
            HttpServletRequest request) {
        try {
            userService.updateUser(userId, updatedUser, request);
            return ResponseEntity.ok("User updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Delete user by ID (admin only)
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId, HttpServletRequest request) {
        try {
            userService.deleteUser(userId, request);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
