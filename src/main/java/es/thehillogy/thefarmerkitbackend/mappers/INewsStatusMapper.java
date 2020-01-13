package es.thehillogy.thefarmerkitbackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import es.thehillogy.thefarmerkitbackend.dtos.NewsStatusDTO;
import es.thehillogy.thefarmerkitbackend.models.NewsStatus;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface INewsStatusMapper {

	NewsStatus mapFromNewsStatusDTO(NewsStatusDTO value);		
	
	NewsStatusDTO mapFromNewsStatusEntity(NewsStatus value);
}
