package com.LMS.Learning_Management_System.dto;

import java.util.Date;

public class QuizDto {

    private int quizId;
    private String title;
    private int courseId;
    private String courseName;
    private Integer questionCount;
    private Boolean randomized;
    private Date creationDate;

    public QuizDto() {}

    public QuizDto(int quizId, String title, int courseId, String courseName,
                   Integer questionCount, Boolean randomized, Date creationDate) {
        this.quizId = quizId;
        this.title = title;
        this.courseId = courseId;
        this.courseName = courseName;
        this.questionCount = questionCount;
        this.randomized = randomized;
        this.creationDate = creationDate;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Boolean getRandomized() {
        return randomized;
    }

    public void setRandomized(Boolean randomized) {
        this.randomized = randomized;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
