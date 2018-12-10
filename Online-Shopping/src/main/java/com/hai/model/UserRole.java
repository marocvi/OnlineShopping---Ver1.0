package com.hai.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="User_Role")
public class UserRole {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="User_Role_ID")
	@Id
	private int id;
	
	//Map to Role
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="Role_ID", nullable = false)
	private Role role;
	
	//Map to User
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="User_ID",nullable=false)
	private Users user;
	
	@Column(name="Start_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="End_Date")
	private Date endDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
