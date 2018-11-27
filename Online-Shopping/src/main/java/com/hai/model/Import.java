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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Import")
public class Import {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "Import_ID")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Import_Date")
	private Date date;
	@Column(name = "Amount_Total")
	private int amountTotal;
	@Column(name = "Money_Total")
	private double moenyTotal;
	
	//Map to supplier
	@ManyToOne (cascade =CascadeType.PERSIST)
	@JoinColumn(name = "Supplier_ID")
	private Supplier supplier;
	
	//Map to User
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "User_ID")
	private Users user;
	
	//Map to Import Detail
	@OneToMany (mappedBy = "productImport")
	private List<ImportDetail> importDetails;

	public int getId() {
		return id;
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

	public int getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(int amountTotal) {
		this.amountTotal = amountTotal;
	}

	public double getMoenyTotal() {
		return moenyTotal;
	}

	public void setMoenyTotal(double moenyTotal) {
		this.moenyTotal = moenyTotal;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<ImportDetail> getImportDetails() {
		return importDetails;
	}

	public void setImportDetails(List<ImportDetail> importDetails) {
		this.importDetails = importDetails;
	}
	
	
	
}
