package es.thehillogy.thefarmerkitbackend.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "comite-empresa-backend.security")
public class SecurityOptions {
	
	private String ssoURL;

	public String getSsoURL() {
		return ssoURL;
	}

	public void setSsoURL(String ssoURL) {
		this.ssoURL = ssoURL;
	}
}
