package es.thehillogy.thefarmerkitbackend.security;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.thehillogy.thefarmerkitbackend.dtos.UserJWT;
import es.thehillogy.thefarmerkitbackend.exceptions.AuthorizationException;
import es.thehillogy.thefarmerkitbackend.models.Role;
import es.thehillogy.thefarmerkitbackend.models.RoleAuthority;
import es.thehillogy.thefarmerkitbackend.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class SecurityHelper implements ApplicationContextAware
{
	private static Logger logger = LoggerFactory.getLogger(SecurityHelper.class);
	
	public static final String SYSTEM_USER = "SYSTEM";
	
	public static final String JWT_SECRET = "gafXDoGsbMvtON4vXMkFSGSDJKDp0tty";
	
	private static ApplicationContext context;	
	

	private SecurityHelper()
	{
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		setContext(applicationContext); // NOSONAR
	}

	@SuppressWarnings("unchecked")
	public static String getUserFromSsoToken(String ssoToken)
	{
		byte[] playloadDecode = Base64.getDecoder().decode(ssoToken.split("\\.")[1]);
		
		String decodedString = new String(playloadDecode, Charset.forName("UTF-8"));

		HashMap<String, Object> result = null;
		try
		{
			result = new ObjectMapper().readValue(decodedString, HashMap.class);

		} catch (Exception e)
		{
			logger.warn("Exception at getUserFromSsoToken. {}", e.getMessage());
		}

		return result != null ? (String) result.get("preferred_username") : null;
	}

	public static String createToken(User user)
	{
		return Jwts.builder()
				.claim("userId", user.getId())
				.claim("createdDate", user.getCreatedDate())
				.claim("userName", user.getUserName())
				.claim("displayName", user.getDisplayName())
				.claim("email", user.getEmail())
				.claim("status", user.getStatus())
				.claim("roles", user.getRoles())
				.claim("grantedAuthorities", getUserGrantedAuthoritiesString(user)).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
	}

	private static List<String> getUserGrantedAuthoritiesString(User user)
	{
		List<GrantedAuthority> authorities = getUserGrantedAuthorities(user);

		List<String> grantedAuthorities = new ArrayList<>();
		for (GrantedAuthority auth : authorities)
		{
			grantedAuthorities.add(auth.getAuthority());
		}

		return grantedAuthorities;
	}

	public static List<GrantedAuthority> getUserGrantedAuthorities(User user)
	{
		List<String> grantedAuthorities = new ArrayList<>();

		for (Role profile : user.getRoles())
		{
			grantedAuthorities.addAll(getGrantedAuthorities(profile));
		}
		return deleteDuplicates(grantedAuthorities);
	}

	private static List<String> getGrantedAuthorities(Role role)
	{
		List<String> grantedAuthorities = new ArrayList<>();
		
		for (RoleAuthority ra : role.getRoleAuthorities())
		{
			grantedAuthorities.add(ra.getAuthority().getCode());
		}

		return grantedAuthorities;
	}

	private static List<GrantedAuthority> deleteDuplicates(List<String> repeatedGrantedAuthorities)
	{
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		Set<String> unrepeatedRepeatedAuthorities = new HashSet<>();

		for (String grantedAuthority : repeatedGrantedAuthorities)
		{
			if (unrepeatedRepeatedAuthorities.add(grantedAuthority))
			{
				grantedAuthorities.add(new SimpleGrantedAuthority(grantedAuthority));
			}
		}

		return grantedAuthorities;
	}

	public static List<GrantedAuthority> getUserGrantedAuthorities(List<String> list)
	{
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (String auth : list)
		{
			grantedAuthorities.add(new SimpleGrantedAuthority(auth));
		}

		return grantedAuthorities;
	}

	public static boolean validateSSOToken(String token, Optional<SecurityOptions> securityOptions) throws AuthorizationException
	{	
		try
		{
			SecurityOptions currentSecurityOptions = securityOptions.isPresent() ? securityOptions.get() : (SecurityOptions) context.getBean("securityOptions");
			
			// En localhost SSOURL es nulo. No se valida
			if (currentSecurityOptions.getSsoURL().isEmpty()) {
				return true;
			}				

			TrustStrategy acceptingTrustStrategy = (java.security.cert.X509Certificate[] chain,
					String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);

			RestTemplate restTemplate = new RestTemplate(requestFactory);

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", token);

			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

			restTemplate.exchange(currentSecurityOptions.getSsoURL(), HttpMethod.GET, entity, Object.class);

		} catch (Exception e)
		{
			logger.error("Error connecting to 'SSO REDHAT URL'. Error: {} {}", e.getMessage(), e);			
			logger.error("Invalid SSO token: {} ", token);
			
			throw new AuthorizationException("Error validating SSO Token against RedHat Server. " + e.getMessage(), e);
		}

		return true;
	}

	@SuppressWarnings({ "unchecked" })
	public static UserJWT validateToken(String token)
	{
		UserJWT userJwtResult = null;

		try
		{
			Claims claim = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

			List<String> grantedAuthorities = claim.get("grantedAuthorities", List.class);

			userJwtResult = new UserJWT(
					claim.get("userId", Long.class), 
					(String) claim.get("userName"),
					getUserGrantedAuthorities(grantedAuthorities));
			
			userJwtResult.setDisplayName((String) claim.get("displayName"));

		} catch (Exception e)
		{
			logger.error("Invalid User token: {} {} {}", token, e.getMessage(), e);
		}

		return userJwtResult;
	}

	/**
	 * Gets current authenticated user
	 * 
	 * @return the user principal
	 */
	public static String getPrincipalUsername()
	{
		String principal;
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated())
		{
			principal = SYSTEM_USER;
		} else
		{
			principal = ((UserJWT) authentication.getPrincipal()).getUsername();
		}

		return principal;

	}

	public static UserJWT getPrincipal()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((UserJWT) authentication.getPrincipal());
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		SecurityHelper.context = context;
	}

}
