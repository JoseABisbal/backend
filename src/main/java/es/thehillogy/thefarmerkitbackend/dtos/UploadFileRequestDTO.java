package es.thehillogy.thefarmerkitbackend.dtos;

import java.io.InputStream;

public class UploadFileRequestDTO {

	private String fileName;	
	
	private InputStream inputStream;	
	
	private long size;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
