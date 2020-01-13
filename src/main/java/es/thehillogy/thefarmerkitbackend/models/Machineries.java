package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Machineries")
public class Machineries implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8384202676778792168L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(name = "Name",  length = 255)
	private String name;

	@Column(name = "Model ", length = 255)
	private String model;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "Manufacturer", length = 255)
	private String manufacturer;
	
	@Column(name = "CreatedDate")
	private Date createddate;
	
	@Column(name = "Type ", length = 150)
	private String type;
	
	@Column(name = "YearOfRegistration")
	private int yearOfRegistration;
	
	@Column(name = "PurchasedDate")
	private Date purchaseddate;
	
	@Column(name = "Price")
	private float price;
	
	@Column(name = "OriginalUnitsPerHour")
	private float originalUnitsPerHour;
	
	@Column(name = "CurrentUnitsPerHour")
	private float currentUnitsPerHour;
	
	@Column(name = "ImageUrl", length = 1000)
	private String imageUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public Date getPurchaseddate() {
		return purchaseddate;
	}

	public void setPurchaseddate(Date purchaseddate) {
		this.purchaseddate = purchaseddate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getOriginalUnitsPerHour() {
		return originalUnitsPerHour;
	}

	public void setOriginalUnitsPerHour(float originalUnitsPerHour) {
		this.originalUnitsPerHour = originalUnitsPerHour;
	}

	public float getCurrentUnitsPerHour() {
		return currentUnitsPerHour;
	}

	public void setCurrentUnitsPerHour(float currentUnitsPerHour) {
		this.currentUnitsPerHour = currentUnitsPerHour;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	


}
