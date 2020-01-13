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
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = -3308048301073734273L;
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="username",length = 150)
	private String userName;
	
	@Column(name="displayname",length = 250)
	private String displayName;
	
	@Column(name="createddate")
	private Date createdDate;
	
	@Column(name="modifieddate")
	private Date modifiedDate;
	
	@Column(name= "email", length = 250)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "statusid", nullable = false)
	private UserStatus status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "usersroles", 
	  joinColumns = @JoinColumn(name = "userid"), 
	  inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<Role> roles;
	
	@Column(name="usertypeid")
	private User usertypeid;
	
	//Password (Base64) - (varchar: 250)
	
	@Column(name="password")
	private Password password;
	
	@Column(name="birthbate")
	private Date birthbate ;

	@Column(name="farms")
	private UsersFarms farms;
	
	@Column(name="alerts")
	private UsersAlerts alerts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUsertypeid() {
		return usertypeid;
	}

	public void setUsertypeid(User usertypeid) {
		this.usertypeid = usertypeid;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public Date getBirthbate() {
		return birthbate;
	}

	public void setBirthbate(Date birthbate) {
		this.birthbate = birthbate;
	}

	public UsersFarms getFarms() {
		return farms;
	}

	public void setFarms(UsersFarms farms) {
		this.farms = farms;
	}

	public UsersAlerts getAlerts() {
		return alerts;
	}

	public void setAlerts(UsersAlerts alerts) {
		this.alerts = alerts;
	}

	
	
}
