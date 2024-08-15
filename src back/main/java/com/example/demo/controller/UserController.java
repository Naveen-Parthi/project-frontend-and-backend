package com.example.demo.controller;

import com.example.demo.entities.Userdetails;
import com.example.demo.entities.HRdetails;
import com.example.demo.entities.Managerdetails;
import com.example.demo.servimpl.HRImpl;
import com.example.demo.servimpl.ManagerImpl;
import com.example.demo.servimpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserImpl userService;

    @Autowired
    private HRImpl hrService;

    @Autowired
    private ManagerImpl managerService;

    @PostMapping("/signIn")
    public ResponseEntity<Userdetails> signIn(@RequestParam String username, @RequestParam String password) {
        Userdetails user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Userdetails> getUser(@PathVariable Long Id) {
        return ResponseEntity.ok(userService.getUser(Id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody Userdetails user, @RequestParam(required = false) Long hr, @RequestParam(required = false) Long manager) {
        try {
            if (user.getRole() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role is required.");
            }

            // Handle specific roles
            if (user.getRole().equalsIgnoreCase("employee")) {
                if (hr == null || manager == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HR ID and Manager ID are required for employees.");
                }

                HRdetails hrDetails = hrService.getHR(hr);
                Managerdetails managerDetails = managerService.getManager(manager);

                if (hrDetails == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HR entity not found for ID: " + hr);
                }
                if (managerDetails == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Manager entity not found for ID: " + manager);
                }

                user.setHr(hrDetails);
                user.setManager(managerDetails);

            } else if (user.getRole().equalsIgnoreCase("manager")) {
                if (hr == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HR ID is required for managers.");
                }

                HRdetails hrDetails = hrService.getHR(hr);

                if (hrDetails == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HR entity not found for ID: " + hr);
                }

                user.setHr(hrDetails);
                user.setManager(null); // Managers do not have a manager
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role. Role should be either 'employee' or 'manager'.");
            }

            // Save the user
            Userdetails ret = userService.addUser(user);
            return new ResponseEntity<>(ret, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Userdetails>> getAllEmployees() {
        List<Userdetails> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<?> editUser(@PathVariable Long Id, @RequestBody Userdetails updatedUser) {
        try {
            Userdetails user = userService.updateUser(Id, updatedUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long Id) {
        try {
            userService.deleteUser(Id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }

    @GetMapping("/byManager/{managerId}")
    public ResponseEntity<List<Userdetails>> getEmployeesByManager(@PathVariable Long managerId) {
        List<Userdetails> employees = userService.getEmployeesByManager(managerId);
        return ResponseEntity.ok(employees);
    }
}
