package com.LMS.Learning_Management_System.dto;

import java.util.Date;

public class SubmissionDto {
    private int submissionId;
    private int assignmentId;
    private int studentId;
    private String studentName;
    private String filePath;
    private Float grade;
    private String feedback;
    private Date submittedAt;

    // ✅ Constructor
    public SubmissionDto(int submissionId, int assignmentId, int studentId, String studentName,
                         String filePath, Float grade, String feedback, Date submittedAt) {
        this.submissionId = submissionId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.filePath = filePath;
        this.grade = grade;
        this.feedback = feedback;
        this.submittedAt = submittedAt;
    }

    // ✅ Getters and Setters
    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public String toString() {
        return "SubmissionDto{" +
                "submissionId=" + submissionId +
                ", assignmentId=" + assignmentId +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", grade=" + grade +
                ", feedback='" + feedback + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }
}
