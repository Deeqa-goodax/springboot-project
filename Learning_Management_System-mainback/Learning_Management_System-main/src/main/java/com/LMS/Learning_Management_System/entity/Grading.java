package com.LMS.Learning_Management_System.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "grading")
public class Grading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private int gradingId;

    @Column(name = "grade")
    private int grade;

    @Column(name = "score")
    private double score;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_account_id")
    private Student student;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "graded_at")
    private Date gradedAt;

    public Grading() {}

    public Grading(int grade, double score, Quiz quiz, Student student) {
        this.grade = grade;
        this.score = score;
        this.quiz = quiz;
        this.student = student;
        this.gradedAt = new Date();
    }

    public int getGradingId() {
        return gradingId;
    }

    public void setGradingId(int gradingId) {
        this.gradingId = gradingId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getGradedAt() {
        return gradedAt;
    }

    public void setGradedAt(Date gradedAt) {
        this.gradedAt = gradedAt;
    }
}
