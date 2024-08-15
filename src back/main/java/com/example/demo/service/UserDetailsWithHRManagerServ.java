package com.example.demo.service;

import com.example.demo.entities.UserDetailsWithHRManager;
import java.util.List;

public interface UserDetailsWithHRManagerServ {
    UserDetailsWithHRManager getUserDetails(Long id);
    UserDetailsWithHRManager addUserDetails(UserDetailsWithHRManager userDetails);
    List<UserDetailsWithHRManager> getAllUserDetails();
    UserDetailsWithHRManager updateUserDetails(Long id, UserDetailsWithHRManager userDetails);
    void deleteUserDetails(Long id);
}
	