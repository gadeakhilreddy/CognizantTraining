package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		return new ResponseEntity<Object>("wrong method",HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	       List<FieldError> list=ex.getFieldErrors();
	       
	       List<String> errorList=new ArrayList<String>();
	       
	       for(FieldError e:list) {
	    	   errorList.add(e.getDefaultMessage());
	       }
	     
		
		return new ResponseEntity<Object>(errorList.toString(),HttpStatus.BAD_REQUEST);
	}

	
	  @Override 
	  protected ResponseEntity<Object>
	  handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders
	  headers, HttpStatus status, WebRequest request) {
	  
		   Map<String, Object> body = new LinkedHashMap<>(); 

		  body.put("timestamp", new Date()); 

		   body.put("status", status.value()); 

		   body.put("error", "Bad Request"); 
		  
		  if (ex.getCause() instanceof InvalidFormatException) { 

			  final Throwable cause = ex.getCause() == null ? ex : ex.getCause(); 

			   for (InvalidFormatException.Reference reference : ((InvalidFormatException) cause).getPath()) { 

			  body.put("message",reference.getFieldName()); 

			  } 

			  } 
		 
			
		  
	  return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST); }
	 
	
	

}