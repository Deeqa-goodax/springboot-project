package com.LMS.Learning_Management_System.dto;

public class LessonAttendenceDto {
    private int attendanceId;
    private int lessonId;
    private int studentId;
    private String studentName;

    public LessonAttendenceDto() {
    }

    public LessonAttendenceDto(int attendanceId, int lessonId, int studentId, String studentName) {
        this.attendanceId = attendanceId;
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
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
}
