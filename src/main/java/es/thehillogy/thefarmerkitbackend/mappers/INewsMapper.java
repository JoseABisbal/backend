package es.thehillogy.thefarmerkitbackend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import es.thehillogy.thefarmerkitbackend.dtos.NewsDTO;
import es.thehillogy.thefarmerkitbackend.models.News;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { INewsStatusMapper.class, IUsersMapper.class })
public interface INewsMapper
{
	@Mappings ({
		@Mapping(source = "createdDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "createdDate"),
		@Mapping(source = "modifiedDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "modifiedDate"),
		@Mapping(source = "publicationDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "publicationDate")
	})
	News mapFromNewsDTO(NewsDTO value);
	
	List<NewsDTO> mapFromNewsEntityList(List<News> values);
	
	List<News> mapFromNewsDTOList(List<NewsDTO> values);
	
	@Mappings ({
		@Mapping(source = "createdDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "createdDate"),
		@Mapping(source = "modifiedDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "modifiedDate"),
		@Mapping(source = "publicationDate", dateFormat = "yyyy-MM-dd HH:mm:ss", target = "publicationDate")
	})
	NewsDTO mapFromNewsEntity(News value);
}
