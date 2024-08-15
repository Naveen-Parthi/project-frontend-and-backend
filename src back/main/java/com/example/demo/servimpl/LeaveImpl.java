package com.example.demo.servimpl;

    import com.example.demo.entities.LeaveForm;
    import com.example.demo.repositories.LeaveAppRepo;
    import com.example.demo.service.LeaveServ;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service // Marks this class as a Spring service component
    public class LeaveImpl implements LeaveServ {

        @Autowired // Automatically injects the LeaveAppRepo dependency
        private LeaveAppRepo leaveRepo;

        @Override
        // Method to apply for leave
        public LeaveForm applyLeave(LeaveForm leaveForm, Boolean manager) {
            leaveForm.setStatus("Applied");
             // Sets the initial status to "Applied"
             if(manager){
                leaveForm.setStatus("ManagerApproved");
             }
            return leaveRepo.save(leaveForm); // Saves the leave form to the repository
        }

        @Override
        // Method to retrieve all leaves for a specific user by their ID
        public List<LeaveForm> getUserLeaves(Long userId) {
            return leaveRepo.findByUserId(userId); // Finds all leave forms by user ID
        }

        @Override
        // Method to approve leave by manager
        public void managerApproveLeave(Long leaveId) {
            LeaveForm leave = leaveRepo.findById(leaveId).orElseThrow(); // Finds leave form by ID or throws an exception if not found
            leave.setStatus("ManagerApproved"); // Sets status to "ManagerApproved"
            leaveRepo.save(leave); // Saves the updated leave form to the repository
        }

        @Override
        // Method to deny leave by manager
        public void managerDenyLeave(Long leaveId) {
            LeaveForm leave = leaveRepo.findById(leaveId).orElseThrow(); // Finds leave form by ID or throws an exception if not found
            leave.setStatus("ManagerDenied"); // Sets status to "ManagerDenied"
            leaveRepo.save(leave); // Saves the updated leave form to the repository
        }

        @Override
        // Method to approve leave by HR
        public void hrApproveLeave(Long leaveId) {
            LeaveForm leave = leaveRepo.findById(leaveId).orElseThrow(); // Finds leave form by ID or throws an exception if not found
            leave.setStatus("HRApproved"); // Sets status to "HRApproved"
            leaveRepo.save(leave); // Saves the updated leave form to the repository
        }

        @Override
        // Method to deny leave by HR
        public void hrDenyLeave(Long leaveId) {
            LeaveForm leave = leaveRepo.findById(leaveId).orElseThrow(); // Finds leave form by ID or throws an exception if not found
            leave.setStatus("HRDenied"); // Sets status to "HRDenied"
            leaveRepo.save(leave); // Saves the updated leave form to the repository
        }

		public List<LeaveForm> getAllUserApply() {
			
			return leaveRepo.getAllUserApply("Applied");
		}
public List<LeaveForm> getAllUserMangerapprove() {
			
			return leaveRepo.getAllUserApply("ManagerApproved");
		}

		
    }