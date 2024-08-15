package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Userdetails;

public interface UserRepo extends JpaRepository<Userdetails, Long> {
    Userdetails findByUsernameAndPassword(String username, String password);
    List<Userdetails> findByManagerId(Long managerId);
    List<Userdetails> findByManagerIdAndRole(Long managerId, String role); // Add this line
    
}
