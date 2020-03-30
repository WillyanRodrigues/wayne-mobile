package br.com.vivo.waynemobile.exception;

import org.springframework.http.HttpStatus;

public class PartnerHTTPException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PartnerHTTPException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	private HttpStatus status;
	private String message;
	
	
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	

}
