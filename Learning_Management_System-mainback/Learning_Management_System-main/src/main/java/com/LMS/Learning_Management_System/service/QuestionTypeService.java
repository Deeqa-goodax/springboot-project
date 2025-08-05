package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.entity.QuestionType;
import com.LMS.Learning_Management_System.repository.QuestionTypeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    public void addQuestionType(QuestionType questionType, HttpServletRequest request) {
        if (questionType.getTypeName() == null) {
            throw new IllegalArgumentException("Type name must not be null.");
        }
        questionTypeRepository.save(questionType);
    }

    public List<QuestionType> getAllQuestionTypes(HttpServletRequest request) {
        return questionTypeRepository.findAll();
    }

    public QuestionType getQuestionTypeById(int id, HttpServletRequest request) {
        return questionTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question type not found with ID: " + id));
    }

    public void updateQuestionType(int id, QuestionType updatedQuestionType, HttpServletRequest request) {
        QuestionType existing = questionTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question type not found with ID: " + id));

        if (updatedQuestionType.getTypeName() != null) {
            existing.setTypeName(updatedQuestionType.getTypeName());
        }

        questionTypeRepository.save(existing);
    }

    public void deleteQuestionType(int id, HttpServletRequest request) {
        if (!questionTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Question type not found with ID: " + id);
        }
        questionTypeRepository.deleteById(id);
    }
}
