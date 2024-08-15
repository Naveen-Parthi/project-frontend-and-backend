package com.example.demo.entities;

import org.apache.catalina.Manager;

import jakarta.persistence.*;

// Marks this class as a JPA entity
@Entity
// Specifies the table name in the database
@Table(name = "LeaveForm")
public class LeaveForm {

	// Marks this field as the primary key
	@Id
	// Auto-generates the primary key value
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Maps this field to a database column, and makes it non-nullable
	@Column(nullable = false)
	private String reason;

	// Maps this field to a database column, and makes it non-nullable
	@Column(nullable = false)
	private String startDate;

	// Maps this field to a database column, and makes it non-nullable
	@Column(nullable = false)
	private String endDate;

	// Many-to-one relationship with the Userdetails entity
	@ManyToOne
	// Specifies the foreign key column in the LeaveForm table and the referenced column in the Userdetails table
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
	private Userdetails user;

	@ManyToOne
	@JoinColumn(name="manager_id", referencedColumnName = "id", nullable = true)
	private Managerdetails manager;

	// Maps this field to a database column, and makes it non-nullable
	@Column(nullable = false)
	private String status; // Applied, ManagerApproved, ManagerDenied, HRApproved, HRDenied

	// Default constructor
	public LeaveForm() {}

	// Getter for id
	public Long getId() {
		return id;
	}

	// Setter for id
	public void setId(Long id) {
		this.id = id;
	}

	// Getter for reason
	public String getReason() {
		return reason;
	}

	// Setter for reason
	public void setReason(String reason) {
		this.reason = reason;
	}

	// Getter for startDate
	public String getStartDate() {
		return startDate;
	}

	// Setter for startDate
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	// Getter for endDate
	public String getEndDate() {
		return endDate;
	}

	// Setter for endDate
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	// Getter for user
	public Userdetails getUser() {
		return user;
	}

	// Setter for user
	public void setUser(Userdetails user) {
		this.user = user;
	}

	// Getter for status
	public String getStatus() {
		return status;
	}

	// Setter for status
	public void setStatus(String status) {
		this.status = status;
	}

	public Managerdetails getManager(){
		return this.manager;
	}

	public void setManager(Managerdetails manager ){
		this.manager = manager;
	}
}