package com.LMS.Learning_Management_System.dto;

import com.LMS.Learning_Management_System.entity.QuestionType;

public class QuestionDto {

    private int questionId;
    private int quizId;
    private int courseId;
    private String questionText;
    private String questionTypeName;
    private String options;
    private String correctAnswer;

    public QuestionDto() {}

    public QuestionDto(int questionId, int quizId, int courseId, String questionText,
                       String questionTypeName, String options, String correctAnswer) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.courseId = courseId;
        this.questionText = questionText;
        this.questionTypeName = questionTypeName;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public QuestionDto(int questionId, int quizId, int courseId, String questionText, QuestionType.QuestionTypeEnum typeName, String options, String correctAnswer) {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
