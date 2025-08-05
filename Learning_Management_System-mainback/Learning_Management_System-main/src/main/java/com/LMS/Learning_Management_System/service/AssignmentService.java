package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.entity.Assignment;
import com.LMS.Learning_Management_System.repository.AssignmentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public void addAssignment(Assignment assignment, HttpServletRequest request) {
        if (assignment == null || assignment.getCourse() == null) {
            throw new IllegalArgumentException("Assignment or course is missing.");
        }

        // Optionally validate user role or course ownership using request
        assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByCourse(int courseId, HttpServletRequest request) {
        List<Assignment> assignments = assignmentRepository.findByCourseCourseId(courseId);
        if (assignments.isEmpty()) {
            throw new IllegalArgumentException("No assignments found for course ID " + courseId);
        }
        return assignments;
    }

    public Assignment getAssignmentById(int assignmentId, HttpServletRequest request) {
        return assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found with ID: " + assignmentId));
    }

    public void updateAssignment(int assignmentId, Assignment updatedAssignment, HttpServletRequest request) {
        Assignment existing = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found with ID: " + assignmentId));

        existing.setTitle(updatedAssignment.getTitle());
        existing.setDescription(updatedAssignment.getDescription());
        existing.setDueDate(updatedAssignment.getDueDate());
        existing.setCourse(updatedAssignment.getCourse()); // Or skip if course shouldn't change

        assignmentRepository.save(existing);
    }

    public void deleteAssignment(int assignmentId, HttpServletRequest request) {
        if (!assignmentRepository.existsById(assignmentId)) {
            throw new IllegalArgumentException("Assignment not found with ID: " + assignmentId);
        }
        assignmentRepository.deleteById(assignmentId);
    }
}
