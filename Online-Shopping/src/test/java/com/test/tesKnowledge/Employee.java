package com.test.tesKnowledge;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "employee_ID")
	
	private int id;
	private String name;
	@OneToMany( cascade = CascadeType.ALL, mappedBy= "employee")
	private List<Car> cars ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
}
