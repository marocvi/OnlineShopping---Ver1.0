package com.hai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Product_ID")
	private int id;
	@Column(name = "Product_Name")
	private String name;
	private int stock;
	private String size;
	private String color;
	@Column(columnDefinition="TEXT")
	private String description;
	@Column(name="image_name")
	private String listOfImages;
	private String brand;
	@Column(name="profile_image")
	private String profileImage;

	// Map to Subcategory
	@ManyToOne (cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "SubCategory_ID")
	private SubCategory subcategory;

	// Map to Supplier
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(name = "Product_Supplier",
		joinColumns = @JoinColumn(name = "Product_ID",nullable = false),
		inverseJoinColumns = @JoinColumn(name = "Supplier_ID", nullable = false) )
	private List<Supplier> suppliers;

	// Map to List
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch= FetchType.EAGER)
	private List<Price> prices;

	// Map to wishlist
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Wishlist> wishlists;

	// Map to Cart_Detail
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<CartDetail> cartDetails;
	
	//map to Order Detail
	@OneToMany(mappedBy ="product" , cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	//Map to Export Detail
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ExportDetail> exportDetails;
	
	
	//Map to Import Detail
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ImportDetail> importDetails ;
	
	
	//Map to Comment
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval= true, mappedBy="product")
	private List<Comments> comments;
	
	// Getter and setter
	public List<ExportDetail> getExportDetails() {
		return exportDetails;
	}

	public List<ImportDetail> getImportDetails() {
		return importDetails;
	}

	public void setImportDetails(List<ImportDetail> importDetails) {
		this.importDetails = importDetails;
	}

	public void setExportDetails(List<ExportDetail> exportDetails) {
		this.exportDetails = exportDetails;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
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

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}



	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}
	public String getListOfImages() {
		return listOfImages;
	}

	public void setListOfImages(String listOfImages) {
		this.listOfImages = listOfImages;
	}

	

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
