package es.thehillogy.thefarmerkitbackend.exceptions;

public class FileUploadException extends Exception { 

	private static final long serialVersionUID = 177293575899665256L;

	public FileUploadException(String errorMessage) {
       super(errorMessage);
    }
	
	public FileUploadException(String errorMessage, Throwable e) {
		   super(errorMessage, e);
	}
	
	public FileUploadException(Throwable ex) {
	       super(ex);
	}
}