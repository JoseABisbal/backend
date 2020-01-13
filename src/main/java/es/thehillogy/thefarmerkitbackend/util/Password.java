package es.thehillogy.thefarmerkitbackend.util;

import java.util.Base64;

public class Password {
	
	private String password;
	
	
	
    public String EncriptarPassword() {
		return new String(Base64.getDecoder().decode(password));
	}

	public void DesencritarPassword(String password) {
		this.password = Base64.getEncoder().encodeToString(password.getBytes());
	}

	
	

}
