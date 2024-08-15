package com.example.demo.controller;

import com.example.demo.entities.Managerdetails;
import com.example.demo.service.ManagerServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerServ managerServ;

    @PostMapping("/login")
    public ResponseEntity<?> loginManager(@RequestParam String username, @RequestParam String password) {
        Managerdetails manager = managerServ.findByUsername(username);
        if (manager != null && manager.getPassword().equals(password)) {	
            return ResponseEntity.ok(manager);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<?> getManager(@PathVariable Long Id) {
        Managerdetails manager = managerServ.getManager(Id);
        if (manager != null) {
            return ResponseEntity.ok(manager);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manager not found");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addManager(@RequestBody Managerdetails manager) {
        try {
            Managerdetails ret = managerServ.addManager(manager);
            return new ResponseEntity<>(ret, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating manager: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Managerdetails>> getAllManagers() {
        List<Managerdetails> managers = managerServ.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<?> editManager(@PathVariable Long Id, @RequestBody Managerdetails updatedManager) {
        try {
            if (managerServ.getManager(Id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manager not found");
            }

            updatedManager.setId(Id);
            Managerdetails updated = managerServ.updateManager(Id, updatedManager);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating manager: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long Id) {
        try {
            if (managerServ.getManager(Id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manager not found");
            }

            managerServ.deleteManager(Id);
            return ResponseEntity.ok("Manager deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting manager: " + e.getMessage());
        }
    }
}
