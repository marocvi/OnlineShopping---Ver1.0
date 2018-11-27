package com.hai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cart_ID")
	private int id;
	@Column(name = "Money_Total")
	private double moneyTotal;
	@Column(name = "Amount_Total")
	private double amountTotal;
	
	//Map to Guest
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "Guest_ID", unique = true)
	private Guest guest;
	
	//Map to  user
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User_ID", unique = true)
	private Users user;
	
	//Map to Cart_Detail
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartDetail> cartDetails = new ArrayList<CartDetail>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMoneyTotal() {
		return moneyTotal;
	}

	public void setMoneyTotal(double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	public double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}
	
	
}
