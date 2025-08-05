package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.EnrollmentDto;
import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Enrollment;
import com.LMS.Learning_Management_System.entity.Student;
import com.LMS.Learning_Management_System.repository.CourseRepository;
import com.LMS.Learning_Management_System.repository.EnrollmentRepository;
import com.LMS.Learning_Management_System.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Replace with actual authentication logic
    private Student getAuthenticatedStudent(HttpServletRequest request) {
        // Dummy implementation for testing; replace with actual logic
        int dummyStudentId = 1;
        return studentRepository.findById(dummyStudentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
    }

    // ============================
    // ENROLL STUDENT
    // ============================
    public void enrollStudent(Integer courseId, HttpServletRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        Student student = getAuthenticatedStudent(request);

        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndCourse(student, course);
        if (alreadyEnrolled) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        enrollmentRepository.save(enrollment);
    }

    // ============================
    // UNENROLL STUDENT
    // ============================
    public void unenrollStudent(Integer courseId, HttpServletRequest request) {
        Student student = getAuthenticatedStudent(request);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(student, course)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found."));

        enrollmentRepository.delete(enrollment);
    }

    // ============================
    // GET STUDENTS IN COURSE
    // ============================
    public List<EnrollmentDto> getEnrolledStudents(Integer courseId, HttpServletRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        return enrollmentRepository.findByCourse(course)
                .stream()
                .map(e -> new EnrollmentDto(
                        e.getEnrollmentId(),
                        e.getStudent().getUserAccountId(),
                        courseId,
                        course.getCourseName(),
                        e.getStudent().getFullName()
                ))
                .collect(Collectors.toList());
    }

    // ============================
    // GET COURSES FOR STUDENT
    // ============================
    public List<EnrollmentDto> getCoursesForStudent(HttpServletRequest request) {
        Student student = getAuthenticatedStudent(request);

        return enrollmentRepository.findByStudent(student)
                .stream()
                .map(e -> new EnrollmentDto(
                        e.getEnrollmentId(),
                        student.getUserAccountId(),
                        e.getCourse().getCourseId(),
                        e.getCourse().getCourseName(),
                        student.getFullName()
                ))
                .collect(Collectors.toList());
    }
}
