package es.thehillogy.thefarmerkitbackend.dtos;

public class AuthRequestDTO {

	private String ssoToken;

	public String getSsoToken() {
		return ssoToken;
	}

	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}
}
