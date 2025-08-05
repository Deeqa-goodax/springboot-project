package com.LMS.Learning_Management_System.service;

import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

import java.util.Collections;

@Service
@Primary  // Marking this as the default service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    public CustomUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        Users user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Ensure roles are properly converted into GrantedAuthority
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())  // The hashed password from DB
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getUserType().
                        toString()))) // Set roles as GrantedAuthority
                .build();
    }
}
