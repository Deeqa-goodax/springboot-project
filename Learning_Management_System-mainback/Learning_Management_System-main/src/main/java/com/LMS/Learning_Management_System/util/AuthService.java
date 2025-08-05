package com.LMS.Learning_Management_System.util;

import com.LMS.Learning_Management_System.entity.Users;
import com.LMS.Learning_Management_System.entity.UsersType;
import com.LMS.Learning_Management_System.repository.UsersRepository;
import com.LMS.Learning_Management_System.repository.UsersTypeRepository;
import com.LMS.Learning_Management_System.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepo;

    @Autowired
    UsersTypeRepository usersTypeRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> authenticateUser(LoginRequest req) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);

            String jwt = jwtUtils.generateJwtToken(auth);
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(granted -> granted.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(
                    jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles
            ));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Error: Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Authentication failed: " + e.getMessage()));
        }
    }

    public ResponseEntity<?> registerUser(SignupRequest req) {
        if (usersRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (usersRepo.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Users user = new Users();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRegistrationDate(new Date());

        String roleName = (req.getRole() == null || req.getRole().isEmpty())
                ? "STUDENT"
                : req.getRole().iterator().next().toUpperCase();

        UsersType userType = usersTypeRepo.findByUserTypeName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role not found: " + roleName));
        user.setUserType(userType);

        usersRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
