package com.LMS.Learning_Management_System.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private int answerId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    private Users student;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @Column(name = "selected_option")
    private String selectedOption;

    public Answer() {
    }

    public Answer(Users student, Quiz quiz, Question question, String selectedOption) {
        this.student = student;
        this.quiz = quiz;
        this.question = question;
        this.selectedOption = selectedOption;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Users getStudent() {
        return student;
    }

    public void setStudent(Users student) {
        this.student = student;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", student=" + student +
                ", quiz=" + quiz +
                ", question=" + question +
                ", selectedOption='" + selectedOption + '\'' +
                '}';
    }
}
