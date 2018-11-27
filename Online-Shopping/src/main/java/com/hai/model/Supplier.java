package com.hai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Supplier")
public class Supplier {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Supplier_ID")
	private int id;
	@Column(name="Supplier_Name")
	private String name;
	private String address;
	@Column(name="Phone_Number")
	private String phoneNumber;
	
	//Map to Product
	@ManyToMany(mappedBy="suppliers",cascade = CascadeType.ALL)
	private List<Product> products;
	
	//Map to Import
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	
	
	//Getter and setter
	private List<Import> imports;

	public List<Import> getImports() {
		return imports;
	}

	public void setImports(List<Import> imports) {
		this.imports = imports;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


	
	
	
}
