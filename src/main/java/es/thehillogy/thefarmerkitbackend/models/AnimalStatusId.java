package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AnimalStatusId")
public class AnimalStatusId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 430963440053767612L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="Name",length = 255)
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


	
	