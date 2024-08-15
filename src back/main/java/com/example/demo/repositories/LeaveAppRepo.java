package com.example.demo.repositories;

import com.example.demo.entities.LeaveForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveAppRepo extends JpaRepository<LeaveForm, Long> {
    List<LeaveForm> findByUserId(Long userId);

    @Query("select e from LeaveForm e where e.status= ?1")
	List<LeaveForm> getAllUserApply(String s);
}