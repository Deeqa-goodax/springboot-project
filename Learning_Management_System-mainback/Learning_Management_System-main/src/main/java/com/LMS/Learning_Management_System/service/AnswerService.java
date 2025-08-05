package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.AnswerDto;
import com.LMS.Learning_Management_System.entity.Answer;
import com.LMS.Learning_Management_System.repository.AnswerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    // Optionally use this to identify the logged-in user from the request
    // @Autowired private UserService userService;

    public void submitAnswer(Answer answer, HttpServletRequest request) {
        if (answer == null || answer.getStudent() == null || answer.getQuiz() == null || answer.getQuestion() == null) {
            throw new IllegalArgumentException("Missing required answer details.");
        }

        // Optional: Validate the logged-in user has permission to submit
        answerRepository.save(answer);
    }

    public List<AnswerDto> getAnswersByQuizId(int quizId, HttpServletRequest request) {
        List<Answer> answers = answerRepository.findByQuizQuizId(quizId);
        return answers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<AnswerDto> getStudentAnswersForQuiz(int studentId, int quizId, HttpServletRequest request) {
        List<Answer> answers = answerRepository.findByStudentUserIdAndQuizQuizId(studentId, quizId);
        return answers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void deleteAnswer(int answerId, HttpServletRequest request) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isEmpty()) {
            throw new IllegalArgumentException("Answer not found with ID: " + answerId);
        }

        // Optional: Check permissions
        answerRepository.deleteById(answerId);
    }

    private AnswerDto toDto(Answer answer) {
        return new AnswerDto(
                answer.getAnswerId(),
                Math.toIntExact(answer.getStudent().getUserId()),
                answer.getQuiz().getQuizId(),
                answer.getQuestion().getQuestionId(),
                answer.getSelectedOption()
        );
    }
}
