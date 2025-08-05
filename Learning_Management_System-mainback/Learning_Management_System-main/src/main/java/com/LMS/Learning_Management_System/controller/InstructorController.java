package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.InstructorDto;
import com.LMS.Learning_Management_System.entity.Instructor;
import com.LMS.Learning_Management_System.service.InstructorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Autowired
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addInstructor(@RequestBody Instructor instructor, HttpServletRequest request) {
        try {
            instructorService.addInstructor(instructor, request);
            return ResponseEntity.ok("Instructor added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInstructors(HttpServletRequest request) {
        try {
            List<InstructorDto> instructors = instructorService.getAllInstructors(request);
            return ResponseEntity.ok(instructors);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable int id, HttpServletRequest request) {
        try {
            Instructor instructor = instructorService.getInstructorById(id, request);
            return ResponseEntity.ok(instructor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateInstructor(@PathVariable int id, @RequestBody Instructor updatedInstructor, HttpServletRequest request) {
        try {
            instructorService.updateInstructor(id, updatedInstructor, request);
            return ResponseEntity.ok("Instructor updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable int id, HttpServletRequest request) {
        try {
            instructorService.deleteInstructor(id, request);
            return ResponseEntity.ok("Instructor deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
