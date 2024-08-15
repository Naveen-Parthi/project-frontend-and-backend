package com.example.demo.service;

import com.example.demo.entities.FeedbackForm;
import java.util.List;

// Interface for feedback service
public interface FeedbackServ {

    // Method to retrieve all feedback forms
    List<FeedbackForm> getAll();

    // Method to add a new feedback form
    FeedbackForm addFb(FeedbackForm fb);

    // Method to delete a feedback form by its ID
    void delFb(Long id);
}