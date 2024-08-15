package com.example.demo.service;

import com.example.demo.entities.Managerdetails;
import java.util.List;

public interface ManagerServ {
    Managerdetails getManager(Long Id);
    Managerdetails addManager(Managerdetails manager);
    List<Managerdetails> getAllManagers();
    Managerdetails updateManager(Long id, Managerdetails manager);
    void deleteManager(Long id);
    Managerdetails findByUsername(String username);
}
