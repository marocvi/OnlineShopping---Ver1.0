package com.hai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Role_Id")
	private int id;
	private String role;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<UserRole> users;
	
	//Getter and setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<UserRole> getUsers() {
		return users;
	}
	public void setUsers(List<UserRole> users) {
		this.users = users;
	}
	
	
}
