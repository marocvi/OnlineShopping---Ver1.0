package com.test.tesKnowledge;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "employee_ID")
	
	private int id;
	private String name;
	
	@OneToMany(mappedBy="employee")
	@MapKeyColumn(name="Car_Id")
	private Map<Integer,Car> cars = new HashMap<>();
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
	public Map<Integer, Car> getCars() {
		return cars;
	}
	public void setCars(Map<Integer, Car> cars) {
		this.cars = cars;
	}

	
	
	
}
