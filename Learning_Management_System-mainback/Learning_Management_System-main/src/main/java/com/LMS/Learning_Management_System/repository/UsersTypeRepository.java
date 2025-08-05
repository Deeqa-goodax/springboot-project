package com.LMS.Learning_Management_System.repository;

import com.LMS.Learning_Management_System.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
    Optional<UsersType> findByUserTypeName(String userTypeName);

    boolean existsByUserTypeNameIgnoreCase(String userTypeName);
}
