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
@Table(name = "Export_Detail")
public class ExportDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Export_Detail_ID")
	private int id;
	@Column(name = "Recieved_Address")
	private String recievedAddress;
	private String reason;
	
	//Map to Export
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Export_ID", nullable = false)
	private Export export;
	
	//Map to order
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Order_ID")
	private Order order;
	
	//Map to product
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Product_ID",nullable = false)
	private Product product;

	
	//Getter and setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecievedAddress() {
		return recievedAddress;
	}

	public void setRecievedAddress(String recievedAddress) {
		this.recievedAddress = recievedAddress;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Export getExport() {
		return export;
	}

	public void setExport(Export export) {
		this.export = export;
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
