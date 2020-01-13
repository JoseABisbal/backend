package es.thehillogy.thefarmerkitbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thehillogy.thefarmerkitbackend.dtos.GenericResponse;
import es.thehillogy.thefarmerkitbackend.dtos.GenericResponseBuilder;
import es.thehillogy.thefarmerkitbackend.dtos.UserDTO;
import es.thehillogy.thefarmerkitbackend.exceptions.UserException;
import es.thehillogy.thefarmerkitbackend.services.UsersService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UsersEndpoint {

	private UsersService usersService;

    @Autowired
    public UsersEndpoint(UsersService usersService) {
        this.usersService = usersService;
    }

    @ApiOperation(
            value = "Get All Users",
            produces = "json/application")
    @GetMapping
    public GenericResponse<Page<UserDTO>> getAllUsers(Pageable pageable) {
    	return new GenericResponseBuilder<Page<UserDTO>>(usersService.getAllUsers(pageable)).build();
    }
    
    @ApiOperation(value = "Get User By Id")
	@GetMapping("/{id}")
	public GenericResponse<UserDTO> getUserById(@PathVariable("id") Long id) throws UserException
	{		
		return new GenericResponseBuilder<UserDTO>(usersService.getUserById(id)).build();
	}
    
    @ApiOperation(value = "Create User")
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse<UserDTO> createUser(@RequestBody UserDTO userDTO) throws UserException
	{
    	return new GenericResponseBuilder<UserDTO>(usersService.create(userDTO)).build();
	}
    
    @ApiOperation(value = "Update User")
	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws UserException
	{
    	return new GenericResponseBuilder<UserDTO>(usersService.update(userDTO)).build();
	}
    
    @ApiOperation(value = "Delete User")
    @DeleteMapping("/{id}")
	public GenericResponse<Boolean> deleteUser(@PathVariable("id") Long id)
	{
    	return new GenericResponseBuilder<Boolean>(usersService.delete(id)).build();
	}
}
