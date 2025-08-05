package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.entity.QuestionType;
import com.LMS.Learning_Management_System.service.QuestionTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questiontype")
public class QuestionTypeContrller {

    private final QuestionTypeService questionTypeService;

    public QuestionTypeContrller(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestionType(@RequestBody QuestionType questionType, HttpServletRequest request) {
        try {
            questionTypeService.addQuestionType(questionType, request);
            return ResponseEntity.ok("Question type added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestionTypes(HttpServletRequest request) {
        try {
            List<QuestionType> questionTypes = questionTypeService.getAllQuestionTypes(request);
            return ResponseEntity.ok(questionTypes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionTypeById(@PathVariable int id, HttpServletRequest request) {
        try {
            QuestionType questionType = questionTypeService.getQuestionTypeById(id, request);
            return ResponseEntity.ok(questionType);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestionType(@PathVariable int id, @RequestBody QuestionType updatedQuestionType, HttpServletRequest request) {
        try {
            questionTypeService.updateQuestionType(id, updatedQuestionType, request);
            return ResponseEntity.ok("Question type updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestionType(@PathVariable int id, HttpServletRequest request) {
        try {
            questionTypeService.deleteQuestionType(id, request);
            return ResponseEntity.ok("Question type deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
