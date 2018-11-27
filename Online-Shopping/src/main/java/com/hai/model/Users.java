package com.hai.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="User_ID")
	private int id;
	@Column(name="User_Name")
	private String name;
	private String passwords;
	private String email;
	private String address;
	private String phone;
	@Column(name="create_Date")
	private Date createDate;
	@Column(name="Login_Status")
	private String loginStatus;
	
	//Map to User Role
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<UserRole> roles;
	
	//Map to Comment
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Comments> comments;
	
	//Map to Whishlist
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Wishlist> wishlists;
	
	//Map to Cart
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Cart> carts;
	
	//Map to Order
	@OneToMany(mappedBy = "orderUser", cascade = CascadeType.ALL)
	private List<Order> paidUserOrders;
	@OneToMany(mappedBy = "processUser", cascade = CascadeType.ALL)
	private List<Order> processUserOrders;
	
	//Map to Export
	@OneToMany(mappedBy ="user", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Export> exports = new ArrayList<Export>();
	
	
	//Map to Import
	@OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
	private List<Import> imports;
	
	
	

	

	//Getter and setter
	
	public List<Import> getImports() {
		return imports;
	}

	public void setImports(List<Import> imports) {
		this.imports = imports;
	}

	public List<Export> getExports() {
		return exports;
	}

	public void setExports(List<Export> exports) {
		this.exports = exports;
	}

	public int getId() {
		return id;
	}

	public List<Order> getPaidUserOrders() {
		return paidUserOrders;
	}

	public void setPaidUserOrders(List<Order> paidUserOrders) {
		this.paidUserOrders = paidUserOrders;
	}

	public List<Order> getProcessUserOrders() {
		return processUserOrders;
	}

	public void setProcessUserOrders(List<Order> processUserOrders) {
		this.processUserOrders = processUserOrders;
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

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	
}
