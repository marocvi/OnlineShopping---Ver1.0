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
@Table(name="WishList")
//TODO add check here, check for guest_id and user_id are null at the same time
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Wishlist_ID")
	private int id;
	
	//Mapp to Product
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Product_ID", nullable = false )
	private Product product;
	
	
	//Mapp to User
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="User_ID")
	private Users user;

	
	//Getter and setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
}
