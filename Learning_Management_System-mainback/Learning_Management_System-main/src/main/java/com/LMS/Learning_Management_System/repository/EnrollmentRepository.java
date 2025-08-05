package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Enrollment;
import com.LMS.Learning_Management_System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    boolean existsByStudentAndCourse(Student student, Course course);

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    List<Enrollment> findByCourse(Course course);

    List<Enrollment> findByStudent(Student student);
}
