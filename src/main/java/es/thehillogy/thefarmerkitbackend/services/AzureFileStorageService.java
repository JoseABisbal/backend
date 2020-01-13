package es.thehillogy.thefarmerkitbackend.services;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import es.thehillogy.thefarmerkitbackend.dtos.UploadFileRequestDTO;
import es.thehillogy.thefarmerkitbackend.exceptions.FileUploadException;

@Service
public class AzureFileStorageService {

	private static Logger log = LoggerFactory.getLogger(AzureFileStorageService.class);
	
	 @Value("${thefarmerkit-backend.azure.storage.connectionString}")
	 private String azureStorageConnectionString;
	 
	 @Value("${thefarmerkit-backend.azure.storage.containerReference}")
	 private String azureContainerReference;
	 
	 @Value("${thefarmerkit-backend.environment}")
	 private String environment;
	 
	 public String uploadFile(UploadFileRequestDTO uploadRequest) throws FileUploadException 
	 {
		if (uploadRequest == null) {
			return null;
		}
		
		try
		{
			CloudBlobContainer container = getCloudStorageAccount();

			CloudBlockBlob blob = container.getBlockBlobReference("THEFARMERKIT/" + environment + "/FILES/" + uploadRequest.getFileName());
			
			InputStream fileInputStream = uploadRequest.getInputStream();
			
			blob.upload(fileInputStream, uploadRequest.getSize());
			
			String fileUrl = blob.getSnapshotQualifiedUri().toString();
			
			log.info("Image uploaded to Azure Blob Storage. FileUrl: {}", fileUrl);
			
			return fileUrl;
		}
		catch (Exception e)
		{
			String errorMessage = "Error trying to upload Image File to Azure Blob Storage Service. {} {}";
		    log.error(errorMessage, e.getMessage(), e);
		    throw new FileUploadException(errorMessage, e);
		}
	 }	
	 
	 private CloudBlobContainer getCloudStorageAccount() throws URISyntaxException, InvalidKeyException, StorageException {
			
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(azureStorageConnectionString);			
			
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			return blobClient.getContainerReference(azureContainerReference);
		}
}
