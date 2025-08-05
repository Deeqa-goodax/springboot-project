package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findByCourseCourseId(int courseId);
}
