package com.LMS.Learning_Management_System.dto;

public class AnswerDto {
    private int answerId;
    private int studentId;
    private int quizId;
    private int questionId;
    private String selectedOption;

    public AnswerDto() {
    }

    public AnswerDto(int answerId, int studentId, int quizId, int questionId, String selectedOption) {
        this.answerId = answerId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "answerId=" + answerId +
                ", studentId=" + studentId +
                ", quizId=" + quizId +
                ", questionId=" + questionId +
                ", selectedOption='" + selectedOption + '\'' +
                '}';
    }
}
