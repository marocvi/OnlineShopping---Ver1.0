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
public class Guest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Guest_ID")
	private int id;
	@Column(name="Guest_Session")
	private String session;
	
	//Map to wishlist
	@OneToMany(mappedBy="guest",cascade=CascadeType.ALL)
	private List<Wishlist> whishlists;
	
	
	//Map to Cart
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
	private List<Cart> carts;
	
	
	//Getter and setter

	public int getId() {
		return id;
	}

	public List<Wishlist> getWhishlists() {
		return whishlists;
	}

	public void setWhishlists(List<Wishlist> whishlists) {
		this.whishlists = whishlists;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}


	
}
