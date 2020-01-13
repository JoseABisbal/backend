package es.thehillogy.thefarmerkitbackend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import es.thehillogy.thefarmerkitbackend.dtos.RoleDTO;
import es.thehillogy.thefarmerkitbackend.models.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleMapper {

	Role mapFromDTO(RoleDTO value);		
	
	RoleDTO mapToDTO(Role value);
	
	List<Role> mapFromDTOList(List<RoleDTO> values);		
	
	List<RoleDTO> mapToDTOList(List<Role> values);
}
