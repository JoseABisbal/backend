package es.thehillogy.thefarmerkitbackend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This is invoked when user tries to access a secured REST resource without
 * supplying any credentials. We should just send a 401 Unauthorized response
 * because there is no 'login page' to redirect to
 * 
 */
@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, // NOSONAR
				"RestAuthenticationEntryPoint / Unauthorized Exception thrown. " + authenticationException.getMessage());
	}
}
