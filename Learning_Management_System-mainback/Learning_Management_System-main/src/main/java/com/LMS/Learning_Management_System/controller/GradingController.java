package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.GradingDto;
import com.LMS.Learning_Management_System.service.GradingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradingController {

    private final GradingService gradingService;

    // Correct constructor-based injection
    public GradingController(GradingService gradingService) {
        this.gradingService = gradingService;
    }

    @PostMapping("/submit/quiz/{quizId}/student/{studentId}")
    public ResponseEntity<String> submitQuizGrade(@PathVariable int quizId,
                                                  @PathVariable int studentId,
                                                  @RequestParam double score,
                                                  HttpServletRequest request) {
        try {
            gradingService.submitQuizGrade(quizId, studentId, score, request);
            return ResponseEntity.ok("Quiz grade submitted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentGrades(@PathVariable int studentId, HttpServletRequest request) {
        try {
            List<GradingDto> grades = gradingService.getGradesByStudentId(studentId, request);
            return ResponseEntity.ok(grades);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getGradesByCourse(@PathVariable int courseId, HttpServletRequest request) {
        try {
            List<GradingDto> grades = gradingService.getGradesByCourseId(courseId, request);
            return ResponseEntity.ok(grades);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/grade/{gradeId}")
    public ResponseEntity<String> updateGrade(@PathVariable int gradeId,
                                              @RequestParam double newScore,
                                              HttpServletRequest request) {
        try {
            gradingService.updateGrade(gradeId, newScore, request);
            return ResponseEntity.ok("Grade updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
