package com.hai.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Offer")
public class SpecialOffer {

	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Offer_ID")
	private int id;
	@Column(name="Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="End_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private String content; 
	private String link;
	private String image;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Product_ID")
	private Product product;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
