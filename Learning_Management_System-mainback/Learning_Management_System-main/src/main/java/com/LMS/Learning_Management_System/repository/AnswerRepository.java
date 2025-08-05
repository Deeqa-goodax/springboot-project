package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuizQuizId(int quizId);
    List<Answer> findByStudentUserIdAndQuizQuizId(int studentId, int quizId);
}
