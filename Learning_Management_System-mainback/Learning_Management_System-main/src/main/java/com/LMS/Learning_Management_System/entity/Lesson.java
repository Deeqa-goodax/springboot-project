package com.LMS.Learning_Management_System.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private int lessonId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course; // Changed from courseId to course

    private String lessonName;
    private String lessonDescription;

    private int lessonOrder;

    private String OTP;

    private String content;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    public Lesson() {}

    public Lesson(int lessonId, Course course, String lessonName, String lessonDescription, int lessonOrder, String OTP, String content, Date creationTime) {
        this.lessonId = lessonId;
        this.course = course; // Changed from courseId to course
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.lessonOrder = lessonOrder;
        this.OTP = OTP;
        this.content = content;
        this.creationTime = creationTime;
    }

    // Getters and Setters
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Course getCourse() {
        return course; // Changed from courseId to course
    }

    public void setCourse(Course course) {
        this.course = course; // Changed from courseId to course
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", course=" + course +  // Changed courseId to course
                ", lessonName='" + lessonName + '\'' +
                ", lessonDescription='" + lessonDescription + '\'' +
                ", lessonOrder=" + lessonOrder +
                ", OTP='" + OTP + '\'' +
                ", content='" + content + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }

    public Course getCourseId() {
        return null;
    }

    public void setCourseId(Course course) {
    }
}
