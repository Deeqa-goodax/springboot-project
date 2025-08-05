package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.QuizDto;
import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Quiz;
import com.LMS.Learning_Management_System.repository.CourseRepository;
import com.LMS.Learning_Management_System.repository.QuizRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CourseRepository courseRepository) {
        this.quizRepository = quizRepository;
        this.courseRepository = courseRepository;
    }

    public void addQuiz(Quiz quiz, HttpServletRequest request) {
        // Basic validation
        if (quiz.getCourse() == null || quiz.getTitle() == null || quiz.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Course and title are required.");
        }

        // Optionally, validate that the course exists
        Course course = courseRepository.findById(quiz.getCourse().getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID."));

        quiz.setCourse(course);
        quiz.setCreationDate(new Date());
        quizRepository.save(quiz);
    }

    public List<QuizDto> getQuizzesByCourseId(int courseId, HttpServletRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        List<Quiz> quizzes = quizRepository.findByCourse(course);

        return quizzes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public QuizDto getQuizById(int quizId, HttpServletRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));
        return convertToDto(quiz);
    }

    public void updateQuiz(int quizId, Quiz updatedQuiz, HttpServletRequest request) {
        Quiz existingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        if (updatedQuiz.getTitle() != null) existingQuiz.setTitle(updatedQuiz.getTitle());
        if (updatedQuiz.getQuestionCount() != null) existingQuiz.setQuestionCount(updatedQuiz.getQuestionCount());
        if (updatedQuiz.getRandomized() != null) existingQuiz.setRandomized(updatedQuiz.getRandomized());

        quizRepository.save(existingQuiz);
    }

    public void deleteQuiz(int quizId, HttpServletRequest request) {
        if (!quizRepository.existsById(quizId)) {
            throw new IllegalArgumentException("Quiz not found with ID: " + quizId);
        }
        quizRepository.deleteById(quizId);
    }

    private QuizDto convertToDto(Quiz quiz) {
        return new QuizDto(
                quiz.getQuizId(),
                quiz.getTitle(),
                quiz.getCourse().getCourseId(),
                quiz.getCourse().getCourseName(),
                quiz.getQuestionCount(),
                quiz.getRandomized(),
                quiz.getCreationDate()
        );
    }
}
