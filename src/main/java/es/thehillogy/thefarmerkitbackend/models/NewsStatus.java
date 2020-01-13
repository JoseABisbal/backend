package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "News_Status")
public class NewsStatus implements Serializable {

	private static final long serialVersionUID = 6734434230319688989L;
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name",length = 50)
	private String name;
	
	public NewsStatus () {}
	
	public NewsStatus (String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusName() {
		return name;
	}

	public void setStatusName(String name) {
		this.name = name;
	}

}
