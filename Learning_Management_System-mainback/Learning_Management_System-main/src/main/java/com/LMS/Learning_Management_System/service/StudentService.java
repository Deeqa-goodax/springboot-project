package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.StudentDto;
import com.LMS.Learning_Management_System.entity.Student;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.repository.StudentRepository;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UsersRepository usersRepository;

    // Dummy method simulating authentication
    private Users getAuthenticatedUser(HttpServletRequest request) {
        return usersRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void addStudent(Student student, HttpServletRequest request) {
        Users user = student.getUserId();
        if (user == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }

        Users savedUser = usersRepository.save(user);
        student.setUserAccountId(savedUser.getUserType().getUserTypeId());
        student.setUserId(savedUser);
        studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents(HttpServletRequest request) {
        getAuthenticatedUser(request);
        return studentRepository.findAll().stream()
                .map(s -> new StudentDto(
                        s.getUserAccountId(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getUserId().getEmail()
                ))
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(int id, HttpServletRequest request) {
        getAuthenticatedUser(request);
        Student s = studentRepository.findById(Math.toIntExact(Long.valueOf(id)))
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return new StudentDto(
                s.getUserAccountId(),
                s.getFirstName(),
                s.getLastName(),
                s.getUserId().getEmail()
        );
    }

    public void updateStudent(int id, Student updated, HttpServletRequest request) {
        getAuthenticatedUser(request);
        Student existing = studentRepository.findById(Math.toIntExact(Long.valueOf(id)))
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());

        if (updated.getUserId() != null && updated.getUserId().getEmail() != null) {
            existing.getUserId().setEmail(updated.getUserId().getEmail());
            usersRepository.save(existing.getUserId());
        }

        studentRepository.save(existing);
    }

    public void deleteStudent(int id, HttpServletRequest request) {
        Users user = getAuthenticatedUser(request);
        if (!studentRepository.existsById(Math.toIntExact(Long.valueOf(id)))) {
            throw new IllegalArgumentException("Student not found");
        }
        studentRepository.deleteById(Math.toIntExact(Long.valueOf(id)));
        usersRepository.deleteById(user.getUserId());
    }
}
