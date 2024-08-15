package com.example.demo.servimpl;

import com.example.demo.entities.Managerdetails;
import com.example.demo.repositories.ManagerRepo;
import com.example.demo.service.ManagerServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerImpl implements ManagerServ {

    @Autowired
    private ManagerRepo managerRepo;

    @Override
    public Managerdetails getManager(Long Id) {
        return managerRepo.findById(Id).orElse(null);
    }

    @Override
    public Managerdetails addManager(Managerdetails manager) {
        return managerRepo.save(manager);
    }

    @Override
    public List<Managerdetails> getAllManagers() {
        return managerRepo.findAll();
    }

    @Override
    public Managerdetails updateManager(Long id, Managerdetails updatedManager) {
        Managerdetails existingManager = managerRepo.findById(id).orElse(null);
        if (existingManager != null) {
            existingManager.setName(updatedManager.getName());
            existingManager.setPhone(updatedManager.getPhone());
            existingManager.setEmail(updatedManager.getEmail());
            existingManager.setUsername(updatedManager.getUsername());
            existingManager.setPassword(updatedManager.getPassword());

            // Remove this line if 'role' is not applicable for Managerdetails
            // existingManager.setRole(updatedManager.getRole());

            return managerRepo.save(existingManager);
        }
        return null;
    }


    @Override
    public void deleteManager(Long id) {
        if (managerRepo.existsById(id)) {
            managerRepo.deleteById(id);
        }
    }

    @Override
    public Managerdetails findByUsername(String username) {
        return managerRepo.findByUsername(username);
    }
}
