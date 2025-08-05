package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.SubmissionDto;
import com.LMS.Learning_Management_System.entity.Submission;
import com.LMS.Learning_Management_System.repository.SubmissionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public void submitAssignment(Submission submission, HttpServletRequest request) {
        if (submission.getAssignmentId() == null || submission.getStudentId() == null) {
            throw new IllegalArgumentException("Assignment and student must be provided.");
        }
        submission.setSubmittedAt(new Date());
        submissionRepository.save(submission);
    }

    public List<SubmissionDto> getSubmissionsByAssignment(int assignmentId, HttpServletRequest request) {
        return submissionRepository.findByAssignmentId_AssignmentId(assignmentId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<SubmissionDto> getSubmissionsByStudent(int studentId, HttpServletRequest request) {
        return submissionRepository.findByStudentId_UserAccountId(studentId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public SubmissionDto getSubmissionById(int submissionId, HttpServletRequest request) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found."));
        return mapToDto(submission);
    }

    public void deleteSubmission(int submissionId, HttpServletRequest request) {
        if (!submissionRepository.existsById(submissionId)) {
            throw new IllegalArgumentException("Submission not found.");
        }
        submissionRepository.deleteById(submissionId);
    }

    private SubmissionDto mapToDto(Submission s) {
        return new SubmissionDto(
                s.getSubmissionId(),
                s.getAssignmentId().getAssignmentId(),
                s.getStudentId().getUserAccountId(),
                s.getStudentId().getFullName(),
                s.getFilePath(),
                s.getGrade(),
                s.getFeedback(),
                s.getSubmittedAt()
        );
    }
}
