package com.hai.model;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Order_Table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "Order_ID")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Order_Date")
	private  Date date;
	@Column(name = "Money_Total")
	private double moneyTotal;
	@Column( name = "Amount_Total")
	private int amountTotal;
	
	//Map to order user
	@ManyToOne(cascade = CascadeType.PERSIST, optional = true ,targetEntity = Users.class )
	@JoinColumn( name = "Order_User_ID",nullable = false ,
	referencedColumnName = "User_ID")
	private Users orderUser;
	
	//Map to process user
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn( name = "Process_User_ID" , nullable = false)
	private Users processUser;

	//Map to Order_Detail
	@OneToMany(cascade = CascadeType.ALL, targetEntity = OrderDetail.class, orphanRemoval = true, mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	//Map to ExportDetail
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
	private List<ExportDetail> exportDetails;
	
	//Map to Payment
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	
	//Getter and setter
	


	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public List<ExportDetail> getExportDetails() {
		return exportDetails;
	}

	public void setExportDetails(List<ExportDetail> exportDetails) {
		this.exportDetails = exportDetails;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMoneyTotal() {
		return moneyTotal;
	}

	public void setMoneyTotal(double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	public Integer getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Integer amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Users getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(Users orderUser) {
		this.orderUser = orderUser;
	}

	public Users getProcessUser() {
		return processUser;
	}

	public void setProcessUser(Users processUser) {
		this.processUser = processUser;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	
}
