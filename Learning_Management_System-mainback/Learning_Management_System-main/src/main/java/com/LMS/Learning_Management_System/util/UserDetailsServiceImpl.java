package com.LMS.Learning_Management_System.util;

import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UsersRepository userRepository;

    public UserDetailsServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("User not found with username: {}", username);
                    return new UsernameNotFoundException("User Not Found with username: " + username);
                });

        if (logger.isDebugEnabled()) {
            String role = user.getUserType() != null ? user.getUserType().getUserTypeName() : "NO_ROLE";
            logger.debug("Successfully loaded user: {} with role: {}", user.getUsername(), role);
        }

        return UserDetailsImpl.build(user);
    }
}
