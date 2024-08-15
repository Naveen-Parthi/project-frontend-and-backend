package com.example.demo.entities;

import jakarta.persistence.*;

// Marks this class as a JPA entity
@Entity
// Specifies the table name in the database
@Table(name = "Feedback")
public class FeedbackForm {

	// Unique ID for feedback, marked as the primary key
	@Id
	// Specifies the generation strategy for the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Message content of the feedback, cannot be null
	@Column(nullable = false)
	private String message;

	// Name of the person giving the feedback, cannot be null
	@Column(nullable = false)
	private String fromName;

	// Default constructor
	public FeedbackForm() {
	}

	// Parameterized constructor
	public FeedbackForm(Long id, String message, String fromName) {
		this.id = id;
		this.message = message;
		this.fromName = fromName;
	}

	// Getter for id
	public Long getId() {
		return id;
	}

	// Setter for id
	public void setId(Long id) {
		this.id = id;
	}

	// Getter for message
	public String getMessage() {
		return message;
	}

	// Setter for message
	public void setMessage(String message) {
		this.message = message;
	}

	// Getter for fromName
	public String getFromName() {
		return fromName;
	}

	// Setter for fromName
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
}