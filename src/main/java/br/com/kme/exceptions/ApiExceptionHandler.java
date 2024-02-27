package br.com.kme.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ApiError.class)
	public ResponseEntity<Object> handleApiException(ApiError apiError) {
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", apiError.getStatus());
		body.put("error", apiError.getMessage());
		return new ResponseEntity<>(body, HttpStatus.valueOf(apiError.getStatus()));
	}
}
