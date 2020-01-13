package es.thehillogy.thefarmerkitbackend.exceptions;

public class NewsException extends Exception { 

	private static final long serialVersionUID = 177293575899665256L;

	public NewsException(String errorMessage) {
       super(errorMessage);
   }
	
	public NewsException(Throwable ex) {
	       super(ex);
	   }
}