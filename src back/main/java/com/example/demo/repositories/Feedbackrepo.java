package com.example.demo.repositories;

import com.example.demo.entities.FeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Feedbackrepo extends JpaRepository<FeedbackForm, Long> {
}