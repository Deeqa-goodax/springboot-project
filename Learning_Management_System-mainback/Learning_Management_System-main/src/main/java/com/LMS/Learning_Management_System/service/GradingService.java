package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.GradingDto;
import com.LMS.Learning_Management_System.entity.Grading;
import com.LMS.Learning_Management_System.entity.Quiz;
import com.LMS.Learning_Management_System.entity.Student;
import com.LMS.Learning_Management_System.repository.GradingRepository;
import com.LMS.Learning_Management_System.repository.QuizRepository;
import com.LMS.Learning_Management_System.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GradingService {

    @Autowired
    private GradingRepository gradingRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Add a new grade
    public void gradeStudent(int quizId, int studentId, int score, HttpServletRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        Grading grading = new Grading();
        grading.setQuiz(quiz);
        grading.setStudent(student);
        grading.setGrade(score);
        grading.setGradedAt(new Date());

        gradingRepository.save(grading);
    }

    // Get all grades for a quiz
    public List<Grading> getGradesByQuiz(int quizId, HttpServletRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        return gradingRepository.findByQuiz(quiz);
    }

    // Get all grades for a student
    public List<Grading> getGradesByStudent(int studentId, HttpServletRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        return gradingRepository.findByStudent(student);
    }

    // Get grade by quiz and student
    public Grading getGrade(int quizId, int studentId, HttpServletRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        return gradingRepository.findByQuizAndStudent(quiz, student)
                .orElseThrow(() -> new IllegalArgumentException("Grade not found for student and quiz."));
    }

    public void submitQuizGrade(int quizId, int studentId, double score, HttpServletRequest request) {
    }

    public List<GradingDto> getGradesByStudentId(int studentId, HttpServletRequest request) {
        return List.of();
    }

    public List<GradingDto> getGradesByCourseId(int courseId, HttpServletRequest request) {
        return List.of();
    }

    public void updateGrade(int gradeId, double newScore, HttpServletRequest request) {
    }
}
