package com.example.demo.controller;

import com.example.demo.entities.UserDetailsWithHRManager;
import com.example.demo.servimpl.UserDetailsWithHRManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsWithHRManagerController {

    @Autowired
    private UserDetailsWithHRManagerImpl userDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsWithHRManager> getUserDetails(@PathVariable Long id) {
        UserDetailsWithHRManager userDetails = userDetailsService.getUserDetails(id);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<UserDetailsWithHRManager> addUserDetails(@RequestBody UserDetailsWithHRManager userDetails) {
        try {
            UserDetailsWithHRManager createdUserDetails = userDetailsService.addUserDetails(userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDetailsWithHRManager>> getAllUserDetails() {
        List<UserDetailsWithHRManager> userDetailsList = userDetailsService.getAllUserDetails();
        return ResponseEntity.ok(userDetailsList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable Long id, @RequestBody UserDetailsWithHRManager updatedUserDetails) {
        try {
            UserDetailsWithHRManager userDetails = userDetailsService.updateUserDetails(id, updatedUserDetails);
            if (userDetails != null) {
                return ResponseEntity.ok(userDetails);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserDetails(@PathVariable Long id) {
        try {
            userDetailsService.deleteUserDetails(id);
            return ResponseEntity.ok("User details deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user details: " + e.getMessage());
        }
    }
}
