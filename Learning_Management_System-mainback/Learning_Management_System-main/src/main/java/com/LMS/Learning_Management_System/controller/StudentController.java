package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.StudentDto;
import com.LMS.Learning_Management_System.entity.Student;
import com.LMS.Learning_Management_System.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student, HttpServletRequest request) {
        try {
            studentService.addStudent(student, request);
            return ResponseEntity.ok("Student added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents(HttpServletRequest request) {
        try {
            List<StudentDto> students = studentService.getAllStudents(request);
            return ResponseEntity.ok(students);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id, HttpServletRequest request) {
        try {
            StudentDto student = studentService.getStudentById(id, request);
            return ResponseEntity.ok(student);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent, HttpServletRequest request) {
        try {
            studentService.updateStudent(id, updatedStudent, request);
            return ResponseEntity.ok("Student updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id, HttpServletRequest request) {
        try {
            studentService.deleteStudent(id, request);
            return ResponseEntity.ok("Student deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
