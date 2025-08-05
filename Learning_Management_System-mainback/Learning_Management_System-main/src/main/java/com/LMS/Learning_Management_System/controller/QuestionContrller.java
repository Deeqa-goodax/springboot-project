package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.QuestionDto;
import com.LMS.Learning_Management_System.entity.Question;
import com.LMS.Learning_Management_System.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionContrller {

    private final QuestionService questionService;

    public QuestionContrller(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question, HttpServletRequest request) {
        try {
            questionService.addQuestion(question, request);
            return ResponseEntity.ok("Question added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestions(HttpServletRequest request) {
        try {
            List<QuestionDto> questions = questionService.getAllQuestions(request);
            return ResponseEntity.ok(questions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable int id, HttpServletRequest request) {
        try {
            QuestionDto question = questionService.getQuestionById(id, request);
            return ResponseEntity.ok(question);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id, @RequestBody Question updatedQuestion, HttpServletRequest request) {
        try {
            questionService.updateQuestion(id, updatedQuestion, request);
            return ResponseEntity.ok("Question updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id, HttpServletRequest request) {
        try {
            questionService.deleteQuestion(id, request);
            return ResponseEntity.ok("Question deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
