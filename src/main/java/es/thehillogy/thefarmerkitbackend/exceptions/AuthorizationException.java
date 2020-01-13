package es.thehillogy.thefarmerkitbackend.exceptions;

public class AuthorizationException extends Exception { 

	private static final long serialVersionUID = 177293575899665256L;

	public AuthorizationException(String errorMessage) {
       super(errorMessage);
    }
	
	public AuthorizationException(String errorMessage, Throwable e) {
	   super(errorMessage, e);
    }
	
	public AuthorizationException(Throwable ex) {
	   super(ex);
	}
}