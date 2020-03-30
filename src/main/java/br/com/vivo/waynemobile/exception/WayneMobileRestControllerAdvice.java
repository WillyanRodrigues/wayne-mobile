package br.com.vivo.waynemobile.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WayneMobileRestControllerAdvice {

	@ExceptionHandler(PartnerHTTPException.class)
	public ResponseEntity<String> handleHTTPPartnerException(PartnerHTTPException ex){
		return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}
}
