package com.hai.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Import_Detail")
public class ImportDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Import_Detail_ID")
	private int id;
	private int amount;
	@Column(name = "Unit_Price" )
	private double unitPrice;
	
	
	//Map to Import
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name ="Import_ID")
	private Import productImport;
	
	//Map to Product
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Product_ID")
	private  Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Import getProductImport() {
		return productImport;
	}

	public void setProductImport(Import productImport) {
		this.productImport = productImport;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

	

}
