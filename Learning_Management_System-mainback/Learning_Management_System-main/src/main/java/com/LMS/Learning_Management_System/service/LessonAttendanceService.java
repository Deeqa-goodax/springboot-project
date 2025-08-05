package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.LessonAttendenceDto;
import com.LMS.Learning_Management_System.dto.LessonAttendenceDto;
import com.LMS.Learning_Management_System.entity.Lesson;
import com.LMS.Learning_Management_System.entity.LessonAttendance;
import com.LMS.Learning_Management_System.entity.Student;
import com.LMS.Learning_Management_System.repository.LessonAttendanceRepository;
import com.LMS.Learning_Management_System.repository.LessonRepository;
import com.LMS.Learning_Management_System.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonAttendanceService {

    @Autowired
    private LessonAttendanceRepository attendanceRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Dummy auth for testing — replace with actual security later
    private Student getAuthenticatedStudent(HttpServletRequest request) {
        int dummyId = 1; // or extract from JWT/session
        return studentRepository.findById(dummyId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
    }

    public void markAttendance(int lessonId, HttpServletRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found."));

        Student student = getAuthenticatedStudent(request);

        if (attendanceRepository.existsByStudentIdAndLessonId(student, lesson)) {
            throw new IllegalArgumentException("Attendance already marked.");
        }

        LessonAttendance attendance = new LessonAttendance();
        attendance.setLessonId(lesson);
        attendance.setStudentId(student);

        attendanceRepository.save(attendance);
    }

    public List<LessonAttendenceDto> getAttendanceForLesson(int lessonId, HttpServletRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found."));

        return attendanceRepository.findByLessonId(lesson)
                .stream()
                .map(a -> new LessonAttendenceDto(
                        a.getAttendanceId(),
                        a.getLessonId().getLessonId(),
                        Math.toIntExact(a.getStudentId().getUserId().getUserId()),
                        a.getStudentId().getFullName()
                ))
                .collect(Collectors.toList());
    }
}
