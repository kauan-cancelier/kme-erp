package br.com.kme.exceptions;

import lombok.Getter;

@Getter
public class ApiError extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	private final int status;
    private final String message;

    public ApiError(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
