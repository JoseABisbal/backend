package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Animals")
public class Animals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6133490829448794515L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "Identifier ", length = 300)
	private String identifier;

	@Column(name = "BirthDate")
	private Date birthDate;

	@Column(name = "Gender ", length = 1)
	private String gender;

	@Column(name = "Breed ", length = 300)
	private String breed;

	@Column(name = "Notes ")
	private String notes;

	@Column(name = "ImageUrl", length = 1000)
	private String ImageUrl;

	@Column(name = "AnimalStatusId ")
	private AnimalStatusId animalStatusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public AnimalStatusId getAnimalStatusId() {
		return animalStatusId;
	}

	public void setAnimalStatusId(AnimalStatusId animalStatusId) {
		this.animalStatusId = animalStatusId;
	}
	
	

}
