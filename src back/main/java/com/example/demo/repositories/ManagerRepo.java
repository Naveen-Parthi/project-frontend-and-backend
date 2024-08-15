package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Managerdetails;

public interface ManagerRepo extends JpaRepository<Managerdetails, Long> {
    Managerdetails findByUsername(String username);
}
