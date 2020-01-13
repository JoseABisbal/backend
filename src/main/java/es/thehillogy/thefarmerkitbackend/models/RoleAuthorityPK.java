package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Embeddable
public class RoleAuthorityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Authority authority;

	@ManyToOne(fetch=FetchType.EAGER)
	private Role role;

	public RoleAuthorityPK()
	{
		super();
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
