package com.LMS.Learning_Management_System.dto;

public class CourseDto {
    private int courseId;
    private String courseName;
    private String description;
    private int instructorId;

    public CourseDto() {
    }

    public CourseDto(int courseId, String courseName, String description, int instructorId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.instructorId = instructorId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", instructorId=" + instructorId +
                '}';
    }
}
