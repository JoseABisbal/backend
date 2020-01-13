package es.thehillogy.thefarmerkitbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import es.thehillogy.thefarmerkitbackend.dtos.NewsDTO;
import es.thehillogy.thefarmerkitbackend.exceptions.AuthorizationException;
import es.thehillogy.thefarmerkitbackend.exceptions.NewsException;
import es.thehillogy.thefarmerkitbackend.services.NewsService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/news")
public class NewsEndpoint {

    private NewsService newsService;

    @Autowired
    public NewsEndpoint(NewsService newsService) {
        this.newsService = newsService;
    }

    @ApiOperation(
            value = "Get All News",
            produces = "json/application")
    @GetMapping
    public GenericResponse<Page<NewsDTO>> getAllNews(Pageable pageable) {
    	return new GenericResponseBuilder<Page<NewsDTO>>(newsService.getAllNews(pageable)).build();
    }
    
    @ApiOperation(
            value = "Get News By Status",
            produces = "json/application")
    @GetMapping("/status/{name}")
    public GenericResponse<Page<NewsDTO>> getNewsByStatus(
    		@PathVariable("name") String statusName,
    		@PageableDefault(page = 0, size = 10) Pageable pageable) {
    	return new GenericResponseBuilder<Page<NewsDTO>>(newsService.getNewsByStatus(pageable, statusName)).build();
    }
    
    @ApiOperation(value = "Get News By Id")
	@GetMapping("/{id}")
	public GenericResponse<NewsDTO> getNewsById(@PathVariable("id") Long id) throws NewsException
	{		
		return new GenericResponseBuilder<NewsDTO>(newsService.getNewsById(id)).build();
	}
    
    @ApiOperation(value = "Create News")
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) throws AuthorizationException
	{
    	return new GenericResponseBuilder<NewsDTO>(newsService.create(newsDTO)).build();
	}
    
    @ApiOperation(value = "Update News")
	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse<NewsDTO> updateNews(@RequestBody NewsDTO newsDTO) throws NewsException
	{
    	return new GenericResponseBuilder<NewsDTO>(newsService.update(newsDTO)).build();
	}
    
    @ApiOperation(value = "Delete News")
	@DeleteMapping("/{id}")
	public GenericResponse<Boolean> deleteNews(@PathVariable("id") Long id)
	{
    	return new GenericResponseBuilder<Boolean>(newsService.delete(id)).build();
	}
}
