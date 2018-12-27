package com.hai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cart_Detail")
public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cart_Detail_ID")
	private int id;
	private double money;
	private int amount;
	private String color;
	private String size;
	
	//Map to product
	@ManyToOne
	@JoinColumn(nullable=false, name = "Product_ID")
	private Product product;
	
	//Map to cart
	@ManyToOne
	@JoinColumn(name = "Cart_ID", nullable = false)
	private Cart cart;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	 
	
}
