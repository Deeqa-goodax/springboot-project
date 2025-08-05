package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.LessonDto;
import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Lesson;
import com.LMS.Learning_Management_System.entity.Student;
import com.LMS.Learning_Management_System.repository.CourseRepository;
import com.LMS.Learning_Management_System.repository.LessonRepository;
import com.LMS.Learning_Management_System.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Dummy method for current authenticated student
    private Student getAuthenticatedStudent(HttpServletRequest request) {
        int dummyId = 1;
        return studentRepository.findById(dummyId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public void addLesson(Lesson lesson, HttpServletRequest request) {
        Course course = courseRepository.findById(lesson.getCourseId().getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        lesson.setCourseId(course);
        lesson.setCreationTime(new Date());
        lessonRepository.save(lesson);
    }

    public List<LessonDto> getLessonsByCourseId(int courseId, HttpServletRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        return lessonRepository.findByCourse(course).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LessonDto getLessonById(int lessonId, HttpServletRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        return convertToDto(lesson);
    }

    public void updateLesson(int lessonId, Lesson updatedLesson, HttpServletRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        lesson.setLessonName(updatedLesson.getLessonName());
        lesson.setLessonDescription(updatedLesson.getLessonDescription());
        lesson.setLessonOrder(updatedLesson.getLessonOrder());
        lesson.setContent(updatedLesson.getContent());
        lesson.setOTP(updatedLesson.getOTP());

        lessonRepository.save(lesson);
    }

    public void deleteLesson(int lessonId, int courseId, HttpServletRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        if (lesson.getCourseId().getCourseId() != courseId) {
            throw new IllegalArgumentException("Course ID does not match.");
        }

        lessonRepository.delete(lesson);
    }

    public void StudentEnterLesson(int courseId, int lessonId, String otp, HttpServletRequest request) {
        Student student = getAuthenticatedStudent(request);
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        if (lesson.getCourseId().getCourseId() != courseId) {
            throw new IllegalArgumentException("Course mismatch");
        }

        if (!lesson.getOTP().equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        // Additional logic for attendance can go here
    }

    public List<String> lessonAttendance(int lessonId, HttpServletRequest request) {
        // Mocked list for demonstration
        return List.of("Student A", "Student B");
    }

    private LessonDto convertToDto(Lesson lesson) {
        return new LessonDto(
                lesson.getLessonId(),
                lesson.getCourseId().getCourseId(),
                lesson.getLessonName(),
                lesson.getLessonDescription(),
                lesson.getLessonOrder(),
                lesson.getContent(),
                lesson.getCreationTime()
        );
    }
}
