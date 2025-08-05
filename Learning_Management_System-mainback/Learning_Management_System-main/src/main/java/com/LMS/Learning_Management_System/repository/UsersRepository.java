package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @EntityGraph(attributePaths = {"userType"})
    Optional<Users> findByUsername(String username);

    @EntityGraph(attributePaths = {"userType"})
    Optional<Users> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
