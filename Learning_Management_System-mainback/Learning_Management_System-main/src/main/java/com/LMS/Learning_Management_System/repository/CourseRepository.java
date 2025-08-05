package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseName(String courseName);

    boolean existsByInstructorUserAccountIdAndCourseId(int userAccountId, int courseId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Course c " +
            "WHERE c.instructor.userAccountId = :instructorId " +
            "AND c.courseId = :courseId")
    boolean existsByInstructorAndCourseId(@Param("instructorId") int instructorId,
                                          @Param("courseId") int courseId);
}
