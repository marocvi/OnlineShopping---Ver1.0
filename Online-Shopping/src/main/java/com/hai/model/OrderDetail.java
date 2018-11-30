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
@Table(name = "Order_Detail")
public class OrderDetail {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "Order_Detail_ID")
	private int id;
	private int amount;
	@Column(name = "Unit_Price")
	private double unitPrice;

	//Map to Order_Table
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn (name = "Order_ID" ,nullable = false)
	private Order order;
	
	//Map to Product
	@ManyToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "Product_ID" , nullable = false)
	private Product product;
	
	//Getter and setter
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
