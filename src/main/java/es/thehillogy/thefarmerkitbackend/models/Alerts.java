package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Alerts")
public class Alerts implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3700354068249981543L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name",length = 255)
	private String name;
	
	@Column(name="Description", length = 1000)
	private String description;
	
	@Column(name="Enabled")
	private boolean enabled;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
