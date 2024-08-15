package com.example.demo.service;

import com.example.demo.entities.LeaveForm;
import java.util.List;

// Interface for leave service
public interface LeaveServ {

    // Method to apply for leave
    LeaveForm applyLeave(LeaveForm leaveForm, Boolean manager);

    // Method to retrieve all leave applications for a specific user
    List<LeaveForm> getUserLeaves(Long userId);

    // Method for managers to approve a leave application
    void managerApproveLeave(Long leaveId);

    // Method for managers to deny a leave application
    void managerDenyLeave(Long leaveId);

    // Method for HR to approve a leave application
    void hrApproveLeave(Long leaveId);

    // Method for HR to deny a leave application
    void hrDenyLeave(Long leaveId);
    public List<LeaveForm> getAllUserApply() ;
    public List<LeaveForm> getAllUserMangerapprove();
}