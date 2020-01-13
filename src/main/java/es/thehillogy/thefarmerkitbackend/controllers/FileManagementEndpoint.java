package es.thehillogy.thefarmerkitbackend.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.thehillogy.thefarmerkitbackend.dtos.GenericResponse;
import es.thehillogy.thefarmerkitbackend.dtos.GenericResponseBuilder;
import es.thehillogy.thefarmerkitbackend.dtos.UploadFileRequestDTO;
import es.thehillogy.thefarmerkitbackend.exceptions.FileUploadException;
import es.thehillogy.thefarmerkitbackend.services.AzureFileStorageService;
import io.swagger.annotations.ApiOperation;
import liquibase.util.file.FilenameUtils;

@RestController
@RequestMapping("/files")
public class FileManagementEndpoint {
		
	private AzureFileStorageService azureFileStorageService;
	
	@Autowired
	public FileManagementEndpoint (AzureFileStorageService azureFileStorageService) {
		this.azureFileStorageService = azureFileStorageService;
	}

	@PostMapping("/upload") 
	@ApiOperation(value = "Upload file to Azure Storage Container.")
	public GenericResponse<String> uploadFile(@RequestParam("file") MultipartFile file) throws FileUploadException, IOException
	{
		UploadFileRequestDTO fileUploadRequest = getMultiPartFileFromRequest(file);

		return new GenericResponseBuilder<String>(azureFileStorageService.uploadFile(fileUploadRequest)).build();
	}
	
	private UploadFileRequestDTO getMultiPartFileFromRequest(MultipartFile file) throws FileUploadException, IOException {
		
		if(file.getInputStream() == null) {
			throw new FileUploadException("Provided multipart file is null");
		}
		
		UploadFileRequestDTO fileUploadRequest = new UploadFileRequestDTO();		
		 	           
        String fileName = FilenameUtils.getName(file.getOriginalFilename()).trim();
      
        fileUploadRequest.setFileName(fileName);
        fileUploadRequest.setSize(file.getSize());
        fileUploadRequest.setInputStream(file.getInputStream());	      	
		
		return fileUploadRequest;
	}
}
