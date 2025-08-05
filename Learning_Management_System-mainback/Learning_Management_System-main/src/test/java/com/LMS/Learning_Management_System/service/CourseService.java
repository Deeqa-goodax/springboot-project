package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.CourseDto;
import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.repository.CourseRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // ======================
    // ADD COURSE
    // ======================
    public void addCourse(Course course, HttpServletRequest request, int instructorId) {
        if (course == null || course.getInstructor() == null) {
            throw new IllegalArgumentException("Invalid course or instructor.");
        }

        // Optionally validate instructorId matches course.getInstructor().getUserId()

        courseRepository.save(course);
    }

    // ======================
    // GET COURSE BY ID
    // ======================
    public CourseDto getCourseById(int courseId, HttpServletRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        return convertToDto(course);
    }

    // ======================
    // GET ALL COURSES
    // ======================
    public List<CourseDto> getAllCourses(HttpServletRequest request) {
        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ======================
    // UPDATE COURSE
    // ======================
    public void updateCourse(int courseId, Course updatedCourse, HttpServletRequest request) {
        Course existing = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        existing.setCourseName(updatedCourse.getCourseName());
        existing.setDescription(updatedCourse.getDescription());
        existing.setInstructor(updatedCourse.getInstructor());

        courseRepository.save(existing);
    }

    // ======================
    // DELETE COURSE
    // ======================
    public void deleteCourse(int courseId, HttpServletRequest request) {
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("Course not found with ID: " + courseId);
        }

        courseRepository.deleteById(courseId);
    }

    // ======================
    // SEND NOTIFICATIONS
    // ======================
    public void sendNotificationsToEnrolledStudents(int courseId, HttpServletRequest request) {
        // Placeholder for notification logic
        System.out.println("Notification sent to students of course ID: " + courseId);
    }

    // ======================
    // UPLOAD MEDIA FILE
    // ======================
    public void uploadMediaFile(int courseId, MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }

        // Simulated file upload
        System.out.println("File uploaded: " + file.getOriginalFilename() + " for course ID: " + courseId);
    }

    // ======================
    // DTO CONVERTER
    // ======================
    private CourseDto convertToDto(Course course) {
        return new CourseDto(
                course.getCourseId(),
                course.getCourseName(),
                course.getDescription(),
                course.getInstructor().getUserAccountId()
        );
    }
}
