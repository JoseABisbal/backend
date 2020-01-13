package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CropStatusId")
public class CropStatusId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4807396544637818161L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name",length = 250)
	private String name;

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

}
