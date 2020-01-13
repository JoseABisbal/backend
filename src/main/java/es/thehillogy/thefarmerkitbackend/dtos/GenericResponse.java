package es.thehillogy.thefarmerkitbackend.dtos;

import java.io.Serializable;

public class GenericResponse<T> implements Serializable {	
    
	private static final long serialVersionUID = -8027287489262198763L;
	
	private String status;
	
    private String code;
    
    private String message;
    
    private T data; //NOSONAR

    public GenericResponse() {
    	super();
    }
    
    public GenericResponse(String status, String code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
    }
    
    public GenericResponse(String status, String code, String message, T data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
    }
   
    public String getStatus() {
    	return status;
    }

    public void setStatus(String status) {
    	this.status = status;
    }

    public String getCode() {
    	return code;
    }
   
    public void setCode(String code) {
    	this.code = code;
    }

    public String getMessage() {
    	return message;
    }
   
    public void setMessage(String message) {
    	this.message = message;
    }    

    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
    	return "GenericResponse [status=" + status + ", code=" + code + ", message=" + message + ", data=" + data + "]";
    }

}