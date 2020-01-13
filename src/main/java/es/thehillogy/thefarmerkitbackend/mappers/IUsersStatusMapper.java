package es.thehillogy.thefarmerkitbackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import es.thehillogy.thefarmerkitbackend.dtos.UserStatusDTO;
import es.thehillogy.thefarmerkitbackend.models.UserStatus;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUsersStatusMapper {

	UserStatus mapFromDTO(UserStatusDTO value);		
	
	UserStatusDTO mapToDTO(UserStatus value);
}
