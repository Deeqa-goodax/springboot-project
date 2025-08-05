package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.entity.Assignment;
import com.LMS.Learning_Management_System.service.AssignmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssigmentController {

    @Autowired
    private final AssignmentService assignmentService;

    public AssigmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAssignment(@RequestBody Assignment assignment, HttpServletRequest request) {
        try {
            assignmentService.addAssignment(assignment, request);
            return ResponseEntity.ok("Assignment added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/course/{courseId}")
    public ResponseEntity<?> getAssignmentsByCourse(@PathVariable int courseId, HttpServletRequest request) {
        try {
            List<Assignment> assignments = assignmentService.getAssignmentsByCourse(courseId, request);
            return ResponseEntity.ok(assignments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/id/{assignmentId}")
    public ResponseEntity<?> getAssignmentById(@PathVariable int assignmentId, HttpServletRequest request) {
        try {
            Assignment assignment = assignmentService.getAssignmentById(assignmentId, request);
            return ResponseEntity.ok(assignment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{assignmentId}")
    public ResponseEntity<String> updateAssignment(@PathVariable int assignmentId, @RequestBody Assignment updatedAssignment, HttpServletRequest request) {
        try {
            assignmentService.updateAssignment(assignmentId, updatedAssignment, request);
            return ResponseEntity.ok("Assignment updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{assignmentId}")
    public ResponseEntity<String> deleteAssignment(@PathVariable int assignmentId, HttpServletRequest request) {
        try {
            assignmentService.deleteAssignment(assignmentId, request);
            return ResponseEntity.ok("Assignment deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
