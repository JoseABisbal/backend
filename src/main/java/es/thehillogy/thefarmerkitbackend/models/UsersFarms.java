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

import es.thehillogy.thefarmerkitbackend.util.Password;

@Entity
@Table(name = "UsersFarms")
public class UsersFarms  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3842240582662847265L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name",length = 250)
	private String name;
	
	@Column(name="latitude")
	private float  latitude;
	
	@Column(name="longitude")
	private Date longitude;
	
	@Column(name="farmTypeId")
	private FarmTypeId farmTypeId;
	
	@Column(name= "address", length = 2000)
	private String address;
	
	@ManyToOne
	@Column(name = "town", length=255)
	private UserStatus town ;
	
	@ManyToOne
	@Column(name = "city", length=255)
	private UserStatus city ;
	
	@Column(name="country", length=255)
	private User country;
	
	@Column(name="cp")
	private Long cp;

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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Date getLongitude() {
		return longitude;
	}

	public void setLongitude(Date longitude) {
		this.longitude = longitude;
	}

	public FarmTypeId getFarmTypeId() {
		return farmTypeId;
	}

	public void setFarmTypeId(FarmTypeId farmTypeId) {
		this.farmTypeId = farmTypeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserStatus getTown() {
		return town;
	}

	public void setTown(UserStatus town) {
		this.town = town;
	}

	public UserStatus getCity() {
		return city;
	}

	public void setCity(UserStatus city) {
		this.city = city;
	}

	public User getCountry() {
		return country;
	}

	public void setCountry(User country) {
		this.country = country;
	}

	public Long getCp() {
		return cp;
	}

	public void setCp(Long cp) {
		this.cp = cp;
	}
	
	

	
}
