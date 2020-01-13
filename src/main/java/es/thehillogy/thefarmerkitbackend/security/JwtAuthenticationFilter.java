package es.thehillogy.thefarmerkitbackend.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import es.thehillogy.thefarmerkitbackend.constants.HeaderConstants;
import es.thehillogy.thefarmerkitbackend.dtos.UserJWT;

@Component("jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter
{	
	private static final String CREDENTIAL_NO_APPLICABLE = "N/A";
		
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request)
	{
		String userToken = null;
		
		try {
			userToken = request.getHeader(HeaderConstants.USER_TOKEN);
			
			if(userToken == null || userToken.isEmpty()) {
				return null;
			}
			
			UserJWT userJWT = SecurityHelper.validateToken(userToken);
			
			if(userJWT == null) {
				return null;
			}
			
		} catch(Exception e)
		{	
			userToken = null;
			logger.error("Error at getPreAuthenticatedPrincipal. " + e.getMessage(), e);
		}
		
		return userToken;			
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request)
	{
		logger.info("Retrieving the Pre-Authenticated Credentials. " + request.getRequestURI());
		return CREDENTIAL_NO_APPLICABLE;
	}
}
