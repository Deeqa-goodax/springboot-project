package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.QuestionDto;
import com.LMS.Learning_Management_System.entity.Question;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.repository.QuestionRepository;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UsersRepository usersRepository;

    // Dummy auth method - replace with real implementation
    private Users getAuthenticatedUser(HttpServletRequest request) {
        return usersRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void addQuestion(Question question, HttpServletRequest request) {
        getAuthenticatedUser(request); // Authorization check if needed
        questionRepository.save(question);
    }

    public List<QuestionDto> getAllQuestions(HttpServletRequest request) {
        getAuthenticatedUser(request);
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public QuestionDto getQuestionById(int id, HttpServletRequest request) {
        getAuthenticatedUser(request);
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) throw new IllegalArgumentException("Question not found");
        return convertToDto(question.get());
    }

    public void updateQuestion(int id, Question updatedQuestion, HttpServletRequest request) {
        getAuthenticatedUser(request);
        Optional<Question> existingQuestionOpt = questionRepository.findById(id);
        if (existingQuestionOpt.isEmpty()) throw new IllegalArgumentException("Question not found");

        Question existingQuestion = existingQuestionOpt.get();

        existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
        existingQuestion.setOptions(updatedQuestion.getOptions());
        existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
        existingQuestion.setQuiz(updatedQuestion.getQuiz());
        existingQuestion.setQuestionType(updatedQuestion.getQuestionType());
        existingQuestion.setCourseId(updatedQuestion.getCourseId());

        questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(int id, HttpServletRequest request) {
        getAuthenticatedUser(request);
        if (!questionRepository.existsById(id)) {
            throw new IllegalArgumentException("Question not found");
        }
        questionRepository.deleteById(id);
    }

    private QuestionDto convertToDto(Question question) {
        return new QuestionDto(
                question.getQuestionId(),
                question.getQuiz().getQuizId(),
                question.getCourseId().getCourseId(),
                question.getQuestionText(),
                question.getQuestionType().getTypeName(),
                question.getOptions(),
                question.getCorrectAnswer()
        );
    }
}
