package com.LMS.Learning_Management_System.controller;

import com.LMS.Learning_Management_System.dto.AnswerDto;
import com.LMS.Learning_Management_System.entity.Answer;
import com.LMS.Learning_Management_System.service.AnswerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody Answer answer, HttpServletRequest request) {
        try {
            answerService.submitAnswer(answer, request);
            return ResponseEntity.ok("Answer submitted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getAnswersByQuiz(@PathVariable int quizId, HttpServletRequest request) {
        try {
            List<AnswerDto> answers = answerService.getAnswersByQuizId(quizId, request);
            return ResponseEntity.ok(answers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}/quiz/{quizId}")
    public ResponseEntity<?> getStudentAnswersForQuiz(
            @PathVariable int studentId,
            @PathVariable int quizId,
            HttpServletRequest request
    ) {
        try {
            List<AnswerDto> answers = answerService.getStudentAnswersForQuiz(studentId, quizId, request);
            return ResponseEntity.ok(answers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<String> deleteAnswer(@PathVariable int answerId, HttpServletRequest request) {
        try {
            answerService.deleteAnswer(answerId, request);
            return ResponseEntity.ok("Answer deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
