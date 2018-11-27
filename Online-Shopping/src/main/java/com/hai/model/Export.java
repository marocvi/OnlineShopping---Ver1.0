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
@Table(name ="Export")
public class Export {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Export_ID")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Export_Date")
	private Date exportDate;
	@Column(name = "Amount_Total")
	private int amountTota;
	
	//Map to User
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User_ID")
	private Users user;
	
	//Map to exportDetail
	@OneToMany(mappedBy = "export", orphanRemoval = true)
	private List<ExportDetail> exportDetails;

	
	//Getter and setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

	public int getAmountTota() {
		return amountTota;
	}

	public void setAmountTota(int amountTota) {
		this.amountTota = amountTota;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<ExportDetail> getExportDetails() {
		return exportDetails;
	}

	public void setExportDetails(List<ExportDetail> exportDetails) {
		this.exportDetails = exportDetails;
	}
	
	
	
	
	
	
	
}
