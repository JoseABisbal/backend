package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "Crops")
public class Crops implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -386727179847129891L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name",length = 250)
	private String name;
	
	@Column(name="kcal")
	private int kcal;
	
	@Column(name="Crop_TypeId")
	private Long cropTypeId;
	
	@Column(name="Crop_Status_Id")
	private CropStatusId cropStatusId;

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

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public Long getCropTypeId() {
		return cropTypeId;
	}

	public void setCropTypeId(Long cropTypeId) {
		this.cropTypeId = cropTypeId;
	}

	public CropStatusId getCropStatusId() {
		return cropStatusId;
	}

	public void setCropStatusId(CropStatusId cropStatusId) {
		this.cropStatusId = cropStatusId;
	}
	
	
}





	