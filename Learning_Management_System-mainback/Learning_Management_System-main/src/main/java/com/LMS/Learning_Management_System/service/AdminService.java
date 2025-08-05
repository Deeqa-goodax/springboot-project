package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.entity.UsersType;
import com.LMS.Learning_Management_System.repository.UsersTypeRepository;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UsersTypeRepository usersTypeRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int userId) {
        if (!userRepository.existsById((long) userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
        userRepository.deleteById((long) userId);
    }

    public void makeAdmin(int userId) {
        Users user = userRepository.findById((long) userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

        UsersType adminType = usersTypeRepository.findByUserTypeName("ADMIN")
                .orElseThrow(() -> new IllegalArgumentException("ADMIN user type not found."));

        if ("ADMIN".equalsIgnoreCase(user.getUserType().getUserTypeName())) {
            throw new IllegalArgumentException("User is already an admin.");
        }

        // ✅ Correct: assign the adminType entity
        user.setUserType(adminType);

        // ✅ Save updated user
        userRepository.save(user);
    }
}
