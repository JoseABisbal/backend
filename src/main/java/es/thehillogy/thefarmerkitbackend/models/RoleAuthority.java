package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "roles_authorities")
@NamedQuery(name = "RoleAuthority.findAll", query = "SELECT p FROM RoleAuthority p")
@AssociationOverride(name = "pk.authority", joinColumns = @JoinColumn(name = "authorityid"))

public class RoleAuthority implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RoleAuthorityPK pk = new RoleAuthorityPK();

	public RoleAuthorityPK getPk() {

		return pk;
	}

	public void setPk(RoleAuthorityPK pk) {
		this.pk = pk;
	}

	@Transient
	public Authority getAuthority() {
		return pk.getAuthority();
	}

	public void setAuthority(Authority authority) {
		pk.setAuthority(authority);
	}

	@Transient
	public Role getRole() {
		return pk.getRole();
	}

}
