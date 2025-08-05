package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Course;
import com.LMS.Learning_Management_System.entity.Grading;
import com.LMS.Learning_Management_System.entity.Quiz;
import com.LMS.Learning_Management_System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface GradingRepository extends JpaRepository<Grading, Integer> {

    // ✅ Check if a specific grade exists for a student and quiz
    @Query("SELECT COALESCE(g.grade, -1) FROM Grading g WHERE g.quiz.quizId = :quizId AND g.student.userId = :studentId")
    int findGradeByQuizAndStudentID(@Param("quizId") int quizId, @Param("studentId") int studentId);

    @Query("SELECT COUNT(g) > 0 FROM Grading g WHERE g.quiz.quizId = :quizId AND g.student.userId = :studentId")
    Optional<Boolean> boolFindGradeByQuizAndStudentID(@Param("quizId") int quizId, @Param("studentId") int studentId);

    // ✅ Return Map-like results of studentId -> grade (use DTO or projection in real systems)
    @Query("SELECT g.student.userId FROM Grading g WHERE g.quiz.quizId = :quizId")
    List<Integer> findStudentByQuiz(@Param("quizId") int quizId);

    @Query("SELECT g.grade FROM Grading g WHERE g.quiz.quizId = :quizId")
    List<Integer> findGradeByQuizId(@Param("quizId") int quizId);

    // ✅ Fetch all grading records for a quiz
    List<Grading> findAllByQuiz(Quiz quiz);

    // ✅ Fetch all grading records for a student

    // ✅ Fetch all grades for quizzes under a specific course
    List<Grading> findByQuiz_Course(Course course);
    List<Grading> findByQuiz(Quiz quiz);
    List<Grading> findByStudent(Student student);
    Optional<Grading> findByQuizAndStudent(Quiz quiz, Student student);

}
