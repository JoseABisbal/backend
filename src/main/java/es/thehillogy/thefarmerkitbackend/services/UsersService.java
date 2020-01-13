package es.thehillogy.thefarmerkitbackend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.thehillogy.thefarmerkitbackend.dtos.UserDTO;
import es.thehillogy.thefarmerkitbackend.dtos.UserJWT;
import es.thehillogy.thefarmerkitbackend.exceptions.AuthorizationException;
import es.thehillogy.thefarmerkitbackend.exceptions.UserException;
import es.thehillogy.thefarmerkitbackend.mappers.IUsersMapper;
import es.thehillogy.thefarmerkitbackend.models.User;
import es.thehillogy.thefarmerkitbackend.repositories.IUsersRepository;

@Service
public class UsersService {

	private final IUsersRepository usersRepository;
	
	private IUsersMapper usersMapper;
	
	@Autowired
    public UsersService(
    		IUsersRepository usersRepository,
    		IUsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }
	
	public Page<UserDTO> getAllUsers(Pageable pageable) {
    	
    	Page<User> usersEntities = usersRepository.findAllByOrderByCreatedDateDesc(pageable);
    	
    	List<UserDTO> mappedEntities = 
    			usersMapper.mapToDTOList(usersEntities.getContent())
				.stream()
				.collect(Collectors.toList());
		
		return new PageImpl<>(
				mappedEntities,
				pageable, 
				usersEntities.getTotalElements());
    }
	
	public UserDTO getUserById(Long userId) throws UserException
	{
		User entity = getUserByIdOperation(userId);
		
		return usersMapper.mapToDTO(entity);
	}	
    
    public UserDTO create(UserDTO userDTO) throws UserException {
		
    	checkForExistingUser(userDTO.getUserName());
    	
    	User mappedEntity = usersMapper.mapFromDTO(userDTO);
		
		mappedEntity.setCreatedDate(new Date());
		mappedEntity.setModifiedDate(new Date());
		
		User savedEntity = usersRepository.save(mappedEntity);			
		
		return usersMapper.mapToDTO(savedEntity);
	}
    
    public UserDTO update(UserDTO userDTO) throws UserException {
		
    	User entity = this.getUserByIdOperation(userDTO.getId());
    	User mappedEntity = usersMapper.mapFromDTO(userDTO);
		
    	entity.setModifiedDate(new Date());		
    	entity.setDisplayName(mappedEntity.getDisplayName());
    	entity.setEmail(mappedEntity.getEmail());
    	entity.setStatus(mappedEntity.getStatus());
    	entity.setRoles(mappedEntity.getRoles());    	
		
		return usersMapper.mapToDTO(usersRepository.save(entity));
	}
    
    public boolean delete(Long id) {
		
    	usersRepository.deleteById(id);
		
		return true;
	}
    
    public User getCurrentLoggedUser () throws AuthorizationException {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (authentication == null || authentication.getPrincipal() == null || authentication.getPrincipal().equals("anonymousUser")) {
    		return null;
    	}
    	
    	UserJWT userJwt = (UserJWT) authentication.getPrincipal();
    	
    	Optional<User> entity = usersRepository.findById(userJwt.getId());
    	
    	if(!entity.isPresent()) {
    		throw new AuthorizationException("There's no information for current logged user. Id: " + userJwt.getId());
    	}
    	
    	return entity.get();
    }
    
    private User getUserByIdOperation(Long userId) throws UserException {
		
		Optional<User> entity = usersRepository.findById(userId);
		
		if (!entity.isPresent()) {
			throw new UserException("User not found in database. Id: " + userId);
		}
		
		return entity.get();
	}
    
    private void checkForExistingUser(String userName) throws UserException {
		
		Optional<User> entity = usersRepository.findByUserName(userName);
		
		if (entity.isPresent()) {
			throw new UserException("User already found in database. Id: " + userName);
		}
	}
}
