package es.thehillogy.thefarmerkitbackend.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.thehillogy.thefarmerkitbackend.dtos.NewsDTO;
import es.thehillogy.thefarmerkitbackend.exceptions.AuthorizationException;
import es.thehillogy.thefarmerkitbackend.exceptions.NewsException;
import es.thehillogy.thefarmerkitbackend.mappers.INewsMapper;
import es.thehillogy.thefarmerkitbackend.models.News;
import es.thehillogy.thefarmerkitbackend.models.User;
import es.thehillogy.thefarmerkitbackend.repositories.INewsRepository;

@Service
public class NewsService {

	private static Logger log = LoggerFactory.getLogger(NewsService.class);
	
	private final INewsRepository newsRepository;
	
	private final UsersService usersService;
	
	private INewsMapper newsMapper;

	@Autowired
    public NewsService(
    		INewsRepository newsRepository,
    		INewsMapper newsMapper,
    		UsersService usersService) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.usersService = usersService;
    }    
    
    public Page<NewsDTO> getAllNews(Pageable pageable) {
    	    	
    	Page<News> newsEntities = newsRepository.findAllPublished(pageable);
    	
    	List<NewsDTO> registrationsResult = 
    			newsMapper.mapFromNewsEntityList(newsEntities.getContent())
				.stream()
				.sorted(Comparator.comparing(NewsDTO::getCreatedDate).reversed())
				.collect(Collectors.toList());
		
		return new PageImpl<>(
				registrationsResult,
				pageable, 
				newsEntities.getTotalElements());
    }
    
    public Page<NewsDTO> getNewsByStatus(Pageable pageable, String statusName) {
    			
    	Page<News> newsEntities = newsRepository.findAllByStatus(pageable, statusName);
    	
    	List<NewsDTO> registrationsResult = 
    			newsMapper.mapFromNewsEntityList(newsEntities.getContent())
				.stream()
				.collect(Collectors.toList());
		
		return new PageImpl<>(
				registrationsResult,
				pageable, 
				newsEntities.getTotalElements());
    }
    
    public NewsDTO getNewsById(Long newsId) throws NewsException
	{
		News entity = getNewsByIdOperation(newsId);
		
		return newsMapper.mapFromNewsEntity(entity);
	}	

    public NewsDTO create(NewsDTO newsDTO) throws AuthorizationException {
		
		News mappedEntity = newsMapper.mapFromNewsDTO(newsDTO);
		
		User currentLoggedUser = usersService.getCurrentLoggedUser();
		
		mappedEntity.setCreatedDate(new Date());
		mappedEntity.setModifiedDate(new Date());		
		
		mappedEntity.setCreatedBy(currentLoggedUser);
		mappedEntity.setModifiedBy(currentLoggedUser);
				
		News newsEntity = newsRepository.save(mappedEntity);			
		
		return newsMapper.mapFromNewsEntity(newsEntity);
	}
    
    public NewsDTO update(NewsDTO newsDTO) throws NewsException {
		
    	News entity = this.getNewsByIdOperation(newsDTO.getId());
    	News mappedEntity = newsMapper.mapFromNewsDTO(newsDTO);
    	
    	User currentLoggedUser = null;
		try {
			currentLoggedUser = usersService.getCurrentLoggedUser();
		} catch (AuthorizationException e) {
			log.warn("Error trying to get current logged user. {} {}", e.getMessage(), e);
		}
		
    	entity.setModifiedDate(new Date());		
    	entity.setModifiedBy(currentLoggedUser);
    	entity.setTitle(mappedEntity.getTitle());
    	entity.setDescription(mappedEntity.getDescription());
    	entity.setImageUrl(mappedEntity.getImageUrl());
    	entity.setPublicationDate(mappedEntity.getPublicationDate());
    	entity.setStatus(mappedEntity.getStatus());
    			
		return newsMapper.mapFromNewsEntity(newsRepository.save(entity));
	}
    
    public boolean delete(Long id) {
		
    	newsRepository.deleteById(id);
		
		return true;
	}
    
    private News getNewsByIdOperation(Long newsId) throws NewsException {
		
		Optional<News> entity = newsRepository.findById(newsId);
		
		if (!entity.isPresent()) {
			throw new NewsException("News not found in database. Id: " + newsId);
		}
		
		return entity.get();
	}
}
