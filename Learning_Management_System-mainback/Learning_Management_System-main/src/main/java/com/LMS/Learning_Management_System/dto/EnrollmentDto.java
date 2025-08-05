package com.LMS.Learning_Management_System.dto;

public class EnrollmentDto {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private String courseName; // optional
    private String studentName; // optional

    public EnrollmentDto() {
    }

    public EnrollmentDto(int enrollmentId, int studentId, int courseId, String courseName, String studentName) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentName = studentName;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
