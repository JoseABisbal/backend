package es.thehillogy.thefarmerkitbackend.dtos;

import java.io.Serializable;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 2086419562977433820L;

	private Long id;
	
	private String roleName;

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
}
