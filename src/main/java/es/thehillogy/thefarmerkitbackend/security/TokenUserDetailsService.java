package es.thehillogy.thefarmerkitbackend.security;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import es.thehillogy.thefarmerkitbackend.dtos.UserJWT;
import es.thehillogy.thefarmerkitbackend.mappers.IRoleMapper;
import es.thehillogy.thefarmerkitbackend.models.User;
import es.thehillogy.thefarmerkitbackend.repositories.IUsersRepository;


/**
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
public class TokenUserDetailsService implements UserDetailsService
{

	@Inject
	private IUsersRepository userRepository;
	
	@Autowired
	private IRoleMapper rolesMapper;
	
	public TokenUserDetailsService () {}
	
	public TokenUserDetailsService (
			IUsersRepository userRepository,
			IRoleMapper rolesMapper) {
		this.userRepository = userRepository;
		this.rolesMapper = rolesMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String userToken)
	{
		UserJWT userJwt = SecurityHelper.validateToken(userToken);
		
		Optional<User> userEntity = userRepository.findByUserName(userJwt.getUsername());
		
		if (!userEntity.isPresent())
		{
			throw new BadCredentialsException("User not found in database: " + userJwt.getUsername());
		} else if (userEntity.get().getStatus().getName().equalsIgnoreCase("inactivo"))
		{
			throw new BadCredentialsException("User is suspended: " + userJwt.getUsername());
		}
		
		userJwt.setRoles(rolesMapper.mapToDTOList(userEntity.get().getRoles()));
					
		return userJwt;
	}
}
