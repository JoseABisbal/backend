package es.thehillogy.thefarmerkitbackend.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.session.SessionManagementFilter;

import es.thehillogy.thefarmerkitbackend.configuration.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private RestAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	@Qualifier("jwtAuthenticationFilter")
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public UserDetailsService tokenUserDetailsService()
	{
		return new TokenUserDetailsService();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager()
	{
		return new ProviderManager(Arrays.asList(securityProvider()));
	}

	@Bean
	public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper()
	{
		return new UserDetailsByNameServiceWrapper<>(tokenUserDetailsService());
	}

	@Bean
	public PreAuthenticatedAuthenticationProvider securityProvider()
	{
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception // NOPMD
	{
		httpSecurity	
		.addFilterBefore(corsFilter(), SessionManagementFilter.class)
		.csrf().disable()		
        .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()        
        .antMatchers("/**/*swagger*/**").permitAll()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/**/api-docs").permitAll()
        .antMatchers("/auth/**").permitAll()		
        .antMatchers("/**").permitAll()	
		.anyRequest().authenticated().and()
		// Call our errorHandler if authentication/authorization fails
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// don't create session
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Custom JWT based security filter
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class);
		// disable page caching
		httpSecurity.headers().cacheControl();
	}	
	
	@Bean
    CorsFilter corsFilter() {
        return new CorsFilter();
    }

}
