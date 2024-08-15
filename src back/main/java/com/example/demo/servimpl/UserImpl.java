package com.example.demo.servimpl;

import com.example.demo.entities.Userdetails;
import com.example.demo.repositories.UserRepo;
import com.example.demo.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserImpl implements UserServ {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Userdetails getUser(Long Id) {
        return userRepo.findById(Id).orElse(null);
    }

    @Override
    public Userdetails addUser(Userdetails user) {
        return userRepo.save(user);
    }

    @Override
    public List<Userdetails> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Userdetails updateUser(Long id, Userdetails updatedUser) {
        Userdetails existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());

            if (updatedUser.getHr() != null) {
                existingUser.setHr(updatedUser.getHr());
            }
            if (updatedUser.getManager() != null) {
                existingUser.setManager(updatedUser.getManager());
            }

            return userRepo.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public Userdetails findByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Userdetails> getEmployeesByManager(Long managerId) {
        return userRepo.findByManagerIdAndRole(managerId, "employee"); // Adjusted to filter by role
    }
}
