package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.UsersTypeDto;
import com.LMS.Learning_Management_System.entity.UsersType;
import com.LMS.Learning_Management_System.repository.UsersTypeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersTypeService {

    @Autowired
    private UsersTypeRepository usersTypeRepository;

    // Dummy admin check - replace with your real authentication logic
    private void checkAdmin(HttpServletRequest request) {
        // Implement actual authentication
    }

    public void addUserType(UsersType userType, HttpServletRequest request) {
        checkAdmin(request);
        if (userType.getUserTypeName() == null || userType.getUserTypeName().isEmpty()) {
            throw new IllegalArgumentException("User type name is required.");
        }

        if (usersTypeRepository.existsByUserTypeNameIgnoreCase(userType.getUserTypeName())) {
            throw new IllegalArgumentException("User type already exists.");
        }

        usersTypeRepository.save(userType);
    }

    public List<UsersTypeDto> getAllUserTypes(HttpServletRequest request) {
        checkAdmin(request);
        return usersTypeRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UsersTypeDto getUserTypeById(int typeId, HttpServletRequest request) {
        checkAdmin(request);
        UsersType userType = usersTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("User type not found."));
        return toDto(userType);
    }

    public void updateUserType(int typeId, UsersType updatedUserType, HttpServletRequest request) {
        checkAdmin(request);
        UsersType existing = usersTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("User type not found."));

        if (updatedUserType.getUserTypeName() != null && !updatedUserType.getUserTypeName().isEmpty()) {
            existing.setUserTypeName(updatedUserType.getUserTypeName());
        }

        usersTypeRepository.save(existing);
    }

    public void deleteUserType(int typeId, HttpServletRequest request) {
        checkAdmin(request);
        if (!usersTypeRepository.existsById(typeId)) {
            throw new IllegalArgumentException("User type not found.");
        }
        usersTypeRepository.deleteById(typeId);
    }

    private UsersTypeDto toDto(UsersType userType) {
        return new UsersTypeDto(userType.getUserTypeId(), userType.getUserTypeName());
    }
}
