package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.InstructorDto;
import com.LMS.Learning_Management_System.entity.Instructor;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.repository.InstructorRepository;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void addInstructor(Instructor instructor, HttpServletRequest request) {
        if (instructor.getUserId() == null || instructor.getUserId().getEmail() == null) {
            throw new IllegalArgumentException("User and email are required.");
        }

        Users savedUser = usersRepository.save(instructor.getUserId());
        instructor.setUserAccountId(Math.toIntExact(savedUser.getUserId()));
        instructor.setUserId(savedUser);

        instructorRepository.save(instructor);
    }

    public List<InstructorDto> getAllInstructors(HttpServletRequest request) {
        return instructorRepository.findAll()
                .stream()
                .map(i -> new InstructorDto(
                        i.getUserAccountId(),
                        i.getFirstName(),
                        i.getLastName(),
                        i.getUserId().getEmail()))
                .collect(Collectors.toList());
    }

    public Instructor getInstructorById(int id, HttpServletRequest request) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found with ID: " + id));
    }

    public void updateInstructor(int id, Instructor updated, HttpServletRequest request) {
        Instructor existing = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found."));

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());

        // Update user email if needed
        if (updated.getUserId() != null && updated.getUserId().getEmail() != null) {
            existing.getUserId().setEmail(updated.getUserId().getEmail());
            usersRepository.save(existing.getUserId());
        }

        instructorRepository.save(existing);
    }

    public void deleteInstructor(int id, HttpServletRequest request) {
        if (!instructorRepository.existsById(id)) {
            throw new IllegalArgumentException("Instructor not found.");
        }
        instructorRepository.deleteById(id);
    }
}
