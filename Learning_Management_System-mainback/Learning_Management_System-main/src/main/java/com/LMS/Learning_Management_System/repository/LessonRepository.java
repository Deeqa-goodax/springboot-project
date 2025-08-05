package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByCourse(Course course);


}
