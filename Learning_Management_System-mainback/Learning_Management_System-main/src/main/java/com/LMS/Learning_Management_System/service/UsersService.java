package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.dto.UsersDto;
import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.util.SignupRequest;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Dummy auth check; replace with real admin check
    private void checkAdmin(HttpServletRequest request) {
        // throw new IllegalArgumentException("Unauthorized");
    }

    public void registerUser(Users user, HttpServletRequest request) {
        checkAdmin(request);
        if (user.getEmail() == null || user.getPassword() == null || user.getUserType() == null) {
            throw new IllegalArgumentException("Email, password, and userType are required.");
        }
        user.setRegistrationDate(new Date());
        usersRepository.save(user);
    }

    public List<UsersDto> getAllUsers(HttpServletRequest request) {
        checkAdmin(request);
        return usersRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UsersDto getUserById(int userId, HttpServletRequest request) {
        checkAdmin(request);
        Users user = usersRepository.findById((long) userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return toDto(user);
    }

    public void updateUser(int userId, Users updatedUser, HttpServletRequest request) {
        checkAdmin(request);
        Users existing = usersRepository.findById((long) userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        if (updatedUser.getEmail() != null) {
            existing.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            existing.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getUserType() != null) {
            existing.setUserType(updatedUser.getUserType());
        }
        usersRepository.save(existing);
    }

    public void deleteUser(int userId, HttpServletRequest request) {
        checkAdmin(request);
        if (!usersRepository.existsById((long) userId)) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        usersRepository.deleteById((long) userId);
    }

    private UsersDto toDto(Users user) {
        return new UsersDto(
                Math.toIntExact(user.getUserId()),
                user.getEmail(),
                user.getUserType(),
                user.getRegistrationDate()
        );
    }

    public void save(SignupRequest signUpRequest, HttpServletRequest request) {
    }

    public Users findByEmail(String email) {
        return null;
    }

    public boolean validatePassword(String password, String password1) {
        return false;
    }
}
