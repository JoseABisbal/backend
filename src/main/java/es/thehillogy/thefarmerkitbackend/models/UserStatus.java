package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users_Status")
public class UserStatus implements Serializable {

	private static final long serialVersionUID = 3375600974039837173L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name")
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

	public void setStatusName(String name) {
		this.name = name;
	}
}
