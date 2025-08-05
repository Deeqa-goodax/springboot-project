package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.QuizDto;
import com.LMS.Learning_Management_System.entity.Quiz;
import com.LMS.Learning_Management_System.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuiz(@RequestBody Quiz quiz, HttpServletRequest request) {
        try {
            quizService.addQuiz(quiz, request);
            return ResponseEntity.ok("Quiz added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/course/{courseId}")
    public ResponseEntity<?> getQuizzesByCourse(@PathVariable int courseId, HttpServletRequest request) {
        try {
            List<QuizDto> quizzes = quizService.getQuizzesByCourseId(courseId, request);
            return ResponseEntity.ok(quizzes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getQuizById(@PathVariable int quizId, HttpServletRequest request) {
        try {
            QuizDto quiz = quizService.getQuizById(quizId, request);
            return ResponseEntity.ok(quiz);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{quizId}")
    public ResponseEntity<String> updateQuiz(@PathVariable int quizId, @RequestBody Quiz updatedQuiz, HttpServletRequest request) {
        try {
            quizService.updateQuiz(quizId, updatedQuiz, request);
            return ResponseEntity.ok("Quiz updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int quizId, HttpServletRequest request) {
        try {
            quizService.deleteQuiz(quizId, request);
            return ResponseEntity.ok("Quiz deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
