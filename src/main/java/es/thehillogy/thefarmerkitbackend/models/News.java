package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "News")
public class News implements Serializable {

	private static final long serialVersionUID = -7186072327193856078L;
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(nullable = false,length = 255)
	private String title;
	
	@Column(nullable = false)
	@Type(type="text")
	private String description;
	
	@Column(name="imageurl",length = 255)
	private String imageUrl;
	
	@Column(name="modifieddate")
	private Date modifiedDate;
	
	@Column(name="createddate")
	private Date createddate;
	
	@Column(name="publicationdate", nullable = false)
	private Date publicationDate;
	
	@ManyToOne
	@JoinColumn(name = "createdbyid", nullable = true)
	private User createdBy;
	
	@ManyToOne
	@JoinColumn(name = "modifiedbyid", nullable = true)
	private User modifiedBy;
			
	@ManyToOne
	@JoinColumn(name = "newsstatusid", nullable = false)
	private NewsStatus newsstatusid;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createddate != null ? (Date) createddate.clone() : null;
	}

	public void setCreatedDate(Date createdDate) {
		this.createddate = createddate;
	}

	public Date getModifiedDate() {
		return modifiedDate != null ? (Date) modifiedDate.clone() : null;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public NewsStatus getStatus() {
		return newsstatusid;
	}

	public void setStatus(NewsStatus status) {
		this.newsstatusid = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getPublicationDate() {
		return publicationDate != null ? (Date) publicationDate.clone() : null;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}
