package com.example.demo.repositories;

import com.example.demo.entities.UserDetailsWithHRManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsWithHRManagerRepo extends JpaRepository<UserDetailsWithHRManager, Long> {
    // Custom query methods if needed
}
