package es.thehillogy.thefarmerkitbackend.dtos;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserJWT implements UserDetails
{	
	private static final long serialVersionUID = 1124446164631622539L;

	private Long id;
	
	private String userName;
	
	private String displayName;
	
	private List<RoleDTO> roles;
	
	private List<GrantedAuthority> authorities;

	public UserJWT()
	{
		super();
	}

	public UserJWT(Long id, String userName, List<GrantedAuthority> grantedAuthorities)
	{
		super();
		this.id = id;
		this.userName = userName;
		this.authorities = grantedAuthorities;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}	

	@Override
	public List<GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	@Override
	public String getPassword()
	{
		return null;
	}

	@Override
	public String getUsername()
	{
		return userName;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
}
