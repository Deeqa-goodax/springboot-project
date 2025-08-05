package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.UsersTypeDto;
import com.LMS.Learning_Management_System.entity.UsersType;
import com.LMS.Learning_Management_System.service.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-type")
public class UserTypeController {

    private final UsersTypeService userTypeService;

    @Autowired
    public UserTypeController(UsersTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    // ✅ Add new user type (admin only)
    @PostMapping("/add")
    public ResponseEntity<String> addUserType(@RequestBody UsersType userType, HttpServletRequest request) {
        try {
            userTypeService.addUserType(userType, request);
            return ResponseEntity.ok("User type added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Get all user types (admin only)
    @GetMapping("/all")
    public ResponseEntity<List<UsersTypeDto>> getAllUserTypes(HttpServletRequest request) {
        try {
            List<UsersTypeDto> userTypes = userTypeService.getAllUserTypes(request);
            return ResponseEntity.ok(userTypes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ✅ Get user type by ID
    @GetMapping("/{typeId}")
    public ResponseEntity<?> getUserTypeById(@PathVariable int typeId, HttpServletRequest request) {
        try {
            UsersTypeDto userType = userTypeService.getUserTypeById(typeId, request);
            return ResponseEntity.ok(userType);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Update user type
    @PutMapping("/update/{typeId}")
    public ResponseEntity<String> updateUserType(
            @PathVariable int typeId,
            @RequestBody UsersType updatedUserType,
            HttpServletRequest request) {
        try {
            userTypeService.updateUserType(typeId, updatedUserType, request);
            return ResponseEntity.ok("User type updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Delete user type (admin only)
    @DeleteMapping("/delete/{typeId}")
    public ResponseEntity<String> deleteUserType(@PathVariable int typeId, HttpServletRequest request) {
        try {
            userTypeService.deleteUserType(typeId, request);
            return ResponseEntity.ok("User type deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
