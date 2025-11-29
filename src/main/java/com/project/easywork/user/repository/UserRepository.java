package com.project.easywork.user.repository;

import com.project.easywork.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
  Optional<User> findByUsername(String username);
  
  boolean existsByEmail(String email);
  boolean existsByUsername(String username);
  boolean existsByPhoneNumber(String phoneNumber);
}
