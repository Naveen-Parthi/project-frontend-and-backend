package com.example.demo.servimpl;

import com.example.demo.entities.FeedbackForm;
import com.example.demo.repositories.Feedbackrepo;
import com.example.demo.service.FeedbackServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this class as a Spring service component
public class FeedbackImpl implements FeedbackServ {

    @Autowired // Automatically injects the Feedbackrepo dependency
    private Feedbackrepo feedbackRepository;

    @Override
    // Method to retrieve all feedback forms from the repository
    public List<FeedbackForm> getAll() {
        return feedbackRepository.findAll();
    }

    @Override
    // Method to add a new feedback form to the repository
    public FeedbackForm addFb(FeedbackForm feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    // Method to delete a feedback form from the repository by its ID
    public void delFb(Long id) {
        feedbackRepository.deleteById(id);
    }
}