package com.example.employee_management.exception;

	import java.util.HashMap;
	import java.util.Map;

	import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestControllerAdvice;

	@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public Map<String, String> handleRuntimeException(RuntimeException ex) {

	        Map<String, String> response = new HashMap<>();

	        response.put("status", "404");
	        response.put("error", ex.getMessage());

	        return response;
	    }
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleValidationException(
	            MethodArgumentNotValidException ex) {

	        System.out.println("VALIDATION HANDLER CALLED");

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	        );

	        return errors;
	  
	    }
	}
