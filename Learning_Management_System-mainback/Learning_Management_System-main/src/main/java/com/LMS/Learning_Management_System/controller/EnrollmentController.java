package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.EnrollmentDto;
import com.LMS.Learning_Management_System.service.EnrollmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // ==============================
    // ENROLL STUDENT TO A COURSE
    // ==============================
    @PostMapping("/enroll/course/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable Integer courseId, HttpServletRequest request) {
        try {
            enrollmentService.enrollStudent(courseId, request);
            return ResponseEntity.ok("Student enrolled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ==============================
    // GET STUDENTS IN A COURSE
    // ==============================
    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<?> getEnrolledStudents(@PathVariable Integer courseId, HttpServletRequest request) {
        try {
            List<EnrollmentDto> enrolledStudents = enrollmentService.getEnrolledStudents(courseId, request);
            return ResponseEntity.ok(enrolledStudents);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ==============================
    // GET COURSES FOR A STUDENT
    // ==============================
    @GetMapping("/student/courses")
    public ResponseEntity<?> getCoursesForStudent(HttpServletRequest request) {
        try {
            List<EnrollmentDto> courses = enrollmentService.getCoursesForStudent(request);
            return ResponseEntity.ok(courses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ==============================
    // UNENROLL STUDENT FROM A COURSE
    // ==============================
    @DeleteMapping("/unenroll/course/{courseId}")
    public ResponseEntity<String> unenrollStudent(@PathVariable Integer courseId, HttpServletRequest request) {
        try {
            enrollmentService.unenrollStudent(courseId, request);
            return ResponseEntity.ok("Student unenrolled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
