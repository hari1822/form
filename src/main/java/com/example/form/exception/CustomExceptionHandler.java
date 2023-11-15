package com.example.form.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	 @ExceptionHandler(CustomException.class)
	 @ResponseStatus(value =  HttpStatus.INTERNAL_SERVER_ERROR)
	 public String customException(CustomException ex) {
		return ex.getMessage();
	 }
	
	 @ExceptionHandler(IOException.class)
	 @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) 
	 public String customException(IOException ex) {
		 List<String> details= new ArrayList<String>();
		 details.add(ex.getMessage());	
		 return details.toString();
	 }
	 
	 @ExceptionHandler(FileSizeLimitExceededException.class)
	 @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) 
	 public String customException(FileSizeLimitExceededException ex) {
		 List<String> details= new ArrayList<String>();
		 details.add(ex.getFileName());
		 details.add(ex.getMessage());
		 return details.toString();
	 }
	 
	 @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	 @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) 
	 public String customException( HttpMediaTypeNotSupportedException ex) {
		 List<String> details= new ArrayList<String>();
		 details.add(ex.getSupportedMediaTypes().toString());
		 details.add(ex.getMessage());	
		 return details.toString();
	 }
	 
}
