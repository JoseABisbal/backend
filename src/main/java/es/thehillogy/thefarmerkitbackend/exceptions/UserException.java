package es.thehillogy.thefarmerkitbackend.exceptions;

public class UserException extends Exception { 

	private static final long serialVersionUID = 177293575899665256L;

	public UserException(String errorMessage) {
       super(errorMessage);
   }
	
	public UserException(Throwable ex) {
	       super(ex);
	   }
}