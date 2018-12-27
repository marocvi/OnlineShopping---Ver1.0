package com.hai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cart_ID")
	private int id  ;
	@Column(name = "Money_Total")
	private double moneyTotal;
	@Column(name = "Amount_Total")
	private int amountTotal;
	

	
	//Map to  user
	@OneToOne
	@JoinColumn(name = "User_ID", unique = true)
	private Users user;
	
	//Map to Cart_Detail
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	private List<CartDetail > cartDetails ;


	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

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

	public int getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(int amountTotal) {
		this.amountTotal = amountTotal;
	}

	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}




	
}
