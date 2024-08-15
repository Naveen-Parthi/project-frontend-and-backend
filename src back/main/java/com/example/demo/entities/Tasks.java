package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Tasks")
public class Tasks {
	 @Id // Marks this field as the primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key value
	    private Long id;

	    @Column(nullable = false) // Maps this field to a database column, and makes it non-nullable
	    private String taskname;
	    

	    @Column(nullable = false) // Maps this field to a database column, and makes it non-nullable
	    private String deadline;

		@ManyToOne
		private Userdetails user;
	    
	    @Column(nullable = false)
	    private boolean completionStatus;

	public Tasks(Long id, String taskname, String deadline, Userdetails user, boolean completionStatus) {
		this.id = id;
		this.taskname = taskname;
		this.deadline = deadline;
		this.user = user;
		this.completionStatus = completionStatus;
	}

	public Tasks() {
	}

	public Long getId() {
		return id;
	}

	public String getTaskname() {
		return taskname;
	}

	public String getDeadline() {
		return deadline;
	}

	public Userdetails getUser() {
		return user;
	}

	public boolean isCompletionStatus() {
		return completionStatus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public void setUser(Userdetails user) {
		this.user = user;
	}

	public void setCompletionStatus(boolean completionStatus) {
		this.completionStatus = completionStatus;
	}
}
