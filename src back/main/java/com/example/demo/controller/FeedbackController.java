package com.example.demo.controller;

import com.example.demo.entities.FeedbackForm;
import com.example.demo.servimpl.FeedbackImpl;
import com.example.demo.servimpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fb")
public class FeedbackController {

    @Autowired
    private FeedbackImpl feedbackService;  // Autowiring Feedback service implementation

    @Autowired
    private UserImpl userService;  // Autowiring User service implementation

    // Endpoint to get all feedback entries
    @GetMapping("/get-all")
    public ResponseEntity<List<FeedbackForm>> getAll() {
        List<FeedbackForm> feedbacks = feedbackService.getAll();  // Fetching all feedbacks
        return ResponseEntity.ok(feedbacks);  // Returning response with HTTP 200 status
    }

    // Endpoint to add new feedback
    @PostMapping("/add")
    public ResponseEntity<FeedbackForm> addFeedback(@RequestBody FeedbackForm feedback) {
        // Setting the fromName field in feedback
        FeedbackForm savedFeedback = feedbackService.addFb(feedback);  // Saving feedback
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);  // Returning response with HTTP 201 status
    }

    // Endpoint to delete a feedback by ID
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFeedback(@RequestParam Long feedbackId) {
        try {
            feedbackService.delFb(feedbackId);  // Deleting feedback by ID
            return ResponseEntity.ok("Deleted");  // Returning response with HTTP 200 status
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete feedback");  // Returning response with HTTP 400 status in case of error
        }
    }
}