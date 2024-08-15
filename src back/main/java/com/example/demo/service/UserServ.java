package com.example.demo.service;

import com.example.demo.entities.Userdetails;
import java.util.List;

import com.example.demo.entities.Userdetails;
import java.util.List;

public interface UserServ {
    Userdetails getUser(Long Id);
    Userdetails addUser(Userdetails user);
    List<Userdetails> getAllUsers();
    List<Userdetails> getEmployeesByManager(Long managerId); // Add this line
    Userdetails updateUser(Long id, Userdetails user);
    void deleteUser(Long id);
    Userdetails findByUsernameAndPassword(String username, String password);
}
