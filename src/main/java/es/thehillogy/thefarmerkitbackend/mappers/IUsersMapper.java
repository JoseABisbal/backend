package es.thehillogy.thefarmerkitbackend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import es.thehillogy.thefarmerkitbackend.dtos.UserDTO;
import es.thehillogy.thefarmerkitbackend.models.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { IUsersStatusMapper.class, IRoleMapper.class })
public interface IUsersMapper {

	@Mappings ({
		@Mapping(source = "createdDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "createdDate"),
		@Mapping(source = "modifiedDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "modifiedDate")
	})
	User mapFromDTO(UserDTO value);		
	
	@Mappings ({
		@Mapping(source = "createdDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "createdDate"),
		@Mapping(source = "modifiedDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "modifiedDate")
	})
	UserDTO mapToDTO(User value);
	
	List<UserDTO> mapToDTOList(List<User> values);
	
	List<User> mapFromDTOList(List<UserDTO> values);
}
