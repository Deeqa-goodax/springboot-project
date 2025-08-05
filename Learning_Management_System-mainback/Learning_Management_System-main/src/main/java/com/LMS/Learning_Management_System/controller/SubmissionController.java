package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.SubmissionDto;
import com.LMS.Learning_Management_System.entity.Submission;
import com.LMS.Learning_Management_System.service.SubmissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAssignment(@RequestBody Submission submission, HttpServletRequest request) {
        try {
            submissionService.submitAssignment(submission, request);
            return ResponseEntity.ok("Assignment submitted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/assignment/{assignmentId}")
    public ResponseEntity<?> getSubmissionsByAssignment(@PathVariable int assignmentId, HttpServletRequest request) {
        try {
            List<SubmissionDto> submissions = submissionService.getSubmissionsByAssignment(assignmentId, request);
            return ResponseEntity.ok(submissions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getSubmissionsByStudent(@PathVariable int studentId, HttpServletRequest request) {
        try {
            List<SubmissionDto> submissions = submissionService.getSubmissionsByStudent(studentId, request);
            return ResponseEntity.ok(submissions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/id/{submissionId}")
    public ResponseEntity<?> getSubmissionById(@PathVariable int submissionId, HttpServletRequest request) {
        try {
            SubmissionDto submission = submissionService.getSubmissionById(submissionId, request);
            return ResponseEntity.ok(submission);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{submissionId}")
    public ResponseEntity<String> deleteSubmission(@PathVariable int submissionId, HttpServletRequest request) {
        try {
            submissionService.deleteSubmission(submissionId, request);
            return ResponseEntity.ok("Submission deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
