package com.example.demo.controller;

import com.example.demo.entities.LeaveForm;
import com.example.demo.servimpl.LeaveImpl;
import com.example.demo.servimpl.ManagerImpl;
import com.example.demo.servimpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveImpl leaveService;

    @Autowired
    private UserImpl userService;

    @Autowired
    private ManagerImpl managerService;

    // Endpoint to apply for leave
    @PostMapping("/apply")
    public ResponseEntity<LeaveForm> applyLeave(
            @RequestBody LeaveForm leaveForm,
            @RequestParam Long userId) {  // Change here
        leaveForm.setUser(userService.getUser(userId));  // Set the user for the leave form
        System.out.println(userId);
        LeaveForm savedLeave = leaveService.applyLeave(leaveForm, false);  // Save the leave form
        return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);  // Return response with HTTP 201 status
    }

    @GetMapping("/user/{userId}")
    public List<LeaveForm> getUserLeaves(@PathVariable Long userId) {
        return leaveService.getUserLeaves(userId);
    }

    @PatchMapping("/manager/approve/{leaveId}")
    public String managerApproveLeave(@PathVariable Long leaveId) {
        leaveService.managerApproveLeave(leaveId);
        return "Manager Approved";
    }

    @PatchMapping("/manager/deny/{leaveId}")
    public String managerDenyLeave(@PathVariable Long leaveId) {
        leaveService.managerDenyLeave(leaveId);
        return "Manager Denied";
    }

    @PatchMapping("/hr/approve/{leaveId}")
    public String hrApproveLeave(@PathVariable Long leaveId) {
        leaveService.hrApproveLeave(leaveId);
        return "HR Approved";
    }

    @PatchMapping("/hr/deny/{leaveId}")
    public String hrDenyLeave(@PathVariable Long leaveId) {
        leaveService.hrDenyLeave(leaveId);
        return "HR Denied";
    }
    @GetMapping("/apply/manager/getAll")
    public List<LeaveForm> getAllApply() {
    	return leaveService.getAllUserApply();
    }
    @GetMapping("/apply/hr/getAll")
    public List<LeaveForm> getAllUserMangerapprove() {
    	return leaveService.getAllUserMangerapprove();
    }
    @PostMapping("/manager/apply")
public ResponseEntity<LeaveForm> managerApplyLeave(
        @RequestBody LeaveForm leaveForm,
        @RequestParam Long managerId) {
    leaveForm.setManager(managerService.getManager(managerId));
    LeaveForm savedLeave = leaveService.applyLeave(leaveForm, true);
    return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);
}

}