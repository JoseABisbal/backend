package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TaskTypeId")
public class TaskTypeId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1190089705378360105L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name = "Name",nullable = false,length = 100)
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
