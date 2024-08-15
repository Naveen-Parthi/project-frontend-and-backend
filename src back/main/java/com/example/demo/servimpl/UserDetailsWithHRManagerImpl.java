package com.example.demo.servimpl;

import com.example.demo.entities.UserDetailsWithHRManager;
import com.example.demo.repositories.UserDetailsWithHRManagerRepo;
import com.example.demo.service.UserDetailsWithHRManagerServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsWithHRManagerImpl implements UserDetailsWithHRManagerServ {

    @Autowired
    private UserDetailsWithHRManagerRepo userDetailsRepo;

    @Override
    public UserDetailsWithHRManager getUserDetails(Long id) {
        return userDetailsRepo.findById(id).orElse(null);
    }

    @Override
    public UserDetailsWithHRManager addUserDetails(UserDetailsWithHRManager userDetails) {
        return userDetailsRepo.save(userDetails);
    }

    @Override
    public List<UserDetailsWithHRManager> getAllUserDetails() {
        return userDetailsRepo.findAll();
    }

    @Override
    public UserDetailsWithHRManager updateUserDetails(Long id, UserDetailsWithHRManager updatedUserDetails) {
        UserDetailsWithHRManager existingUserDetails = userDetailsRepo.findById(id).orElse(null);
        if (existingUserDetails != null) {
            existingUserDetails.setName(updatedUserDetails.getName());
            existingUserDetails.setPhone(updatedUserDetails.getPhone());
            existingUserDetails.setEmail(updatedUserDetails.getEmail());
            existingUserDetails.setRole(updatedUserDetails.getRole());
            existingUserDetails.setUsername(updatedUserDetails.getUsername());
            existingUserDetails.setPassword(updatedUserDetails.getPassword());
            existingUserDetails.setManager(updatedUserDetails.getManager());
            existingUserDetails.setHr(updatedUserDetails.getHr());

            return userDetailsRepo.save(existingUserDetails);
        }
        return null;
    }

    @Override
    public void deleteUserDetails(Long id) {
        if (userDetailsRepo.existsById(id)) {
            userDetailsRepo.deleteById(id);
        }
    }
}
