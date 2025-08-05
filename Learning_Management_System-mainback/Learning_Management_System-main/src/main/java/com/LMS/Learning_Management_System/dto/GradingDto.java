package com.LMS.Learning_Management_System.dto;

import java.util.Date;

public class GradingDto {
    private int gradeId;
    private int studentId;
    private int quizId;
    private double score;
    private Date gradedAt;

    public GradingDto(int gradeId, int studentId, int quizId, double score, Date gradedAt) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
        this.gradedAt = gradedAt;
    }

    // Getters and setters

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getGradedAt() {
        return gradedAt;
    }

    public void setGradedAt(Date gradedAt) {
        this.gradedAt = gradedAt;
    }
}
