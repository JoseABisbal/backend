package es.thehillogy.thefarmerkitbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.thehillogy.thefarmerkitbackend.dtos.AuthRequestDTO;
import es.thehillogy.thefarmerkitbackend.dtos.GenericResponse;
import es.thehillogy.thefarmerkitbackend.dtos.GenericResponseBuilder;
import es.thehillogy.thefarmerkitbackend.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationEndpoint {
	
	private AuthenticationService authenticationService;
	
	@Autowired
	public AuthenticationEndpoint (AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "createAppToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<String> createToken(@RequestBody AuthRequestDTO authRequest)
	{		
		return new GenericResponseBuilder<String>(authenticationService.createToken(authRequest.getSsoToken())).build();
	}
}
