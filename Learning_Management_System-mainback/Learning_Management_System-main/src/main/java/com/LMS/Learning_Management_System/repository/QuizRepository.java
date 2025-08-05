package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    // ✅ Spring Data JPA will automatically implement this
    List<Quiz> findByCourse(Course course);

    // Optional: If you want by course ID instead
    // @Query("SELECT q FROM Quiz q WHERE q.course.courseId = :courseId")
    // List<Quiz> getQuizzesByCourseId(@Param("courseId") int courseId);
}
