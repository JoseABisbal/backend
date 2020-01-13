package es.thehillogy.thefarmerkitbackend.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.thehillogy.thefarmerkitbackend.exceptions.AuthorizationException;
import es.thehillogy.thefarmerkitbackend.models.User;
import es.thehillogy.thefarmerkitbackend.repositories.IUsersRepository;
import es.thehillogy.thefarmerkitbackend.security.SecurityHelper;
import es.thehillogy.thefarmerkitbackend.security.SecurityOptions;


@Service
public class AuthenticationService {

	Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
		
	private IUsersRepository usersRepository;
	
	private SecurityOptions securityOptions;
	
	@Autowired
	public AuthenticationService (
			IUsersRepository usersRepository,
			SecurityOptions securityOptions) {
		this.usersRepository = usersRepository;
		this.securityOptions = securityOptions;
	}
	
	@Transactional(readOnly = true)
	public String createToken(String ssoToken) {
		
		try {
			SecurityHelper.validateSSOToken(ssoToken, Optional.of(securityOptions));
		} catch (AuthorizationException e) {
			throw new AccessDeniedException("403 returned");
		}

		String userName = SecurityHelper.getUserFromSsoToken(ssoToken);
		
		if(userName == null || userName.isEmpty()) {
			String message = "UserName can't be blank";
			logger.warn(message);
			throw new BadCredentialsException(message);
		}

		Optional<User> user = usersRepository.findByUserName(userName.toLowerCase());
		
		if (!user.isPresent()) {
			String message = "User not found in database: " + userName;
			logger.warn(message);
			throw new BadCredentialsException(message);
		} else if ("inactivo".equalsIgnoreCase(user.get().getStatus().getName())) {
			String message = "User is suspended: " + userName;
			logger.warn(message);
			throw new BadCredentialsException(message);
		} 

		return SecurityHelper.createToken(user.get());
	}	
}
