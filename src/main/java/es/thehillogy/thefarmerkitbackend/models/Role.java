package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Roles")
public class Role  implements Serializable {

	private static final long serialVersionUID = -9152352761121754432L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name="description", length = 50)
	private String description;
	
	@Column(name="rolename", nullable = false, length = 50)
	private String roleName;
	

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.role", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleAuthority> roleAuthorities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleAuthority> getRoleAuthorities() {
		return roleAuthorities;
	}

	public void setRoleAuthorities(List<RoleAuthority> roleAuthorities) {
		this.roleAuthorities = roleAuthorities;
	}
}
