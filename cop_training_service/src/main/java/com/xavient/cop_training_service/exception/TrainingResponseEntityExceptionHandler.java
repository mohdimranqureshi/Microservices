package com.xavient.cop_training_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TrainingResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	private static Logger logger = LoggerFactory.getLogger(TrainingResponseEntityExceptionHandler.class);
	
	public TrainingResponseEntityExceptionHandler(){
		super();
	}
	
	@Override
	protected final ResponseEntity<Object> handleHttpMessageNotReadable(
			final HttpMessageNotReadableException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request){
		
		logger.info("Bad Request: {}" +ex.getMessage());
		logger.debug("Bad Request: "+ex);
		
		final TrainingErrorInformationDTO clientError = new TrainingErrorInformationDTO(
				HttpStatus.BAD_REQUEST, "Message not readable", request.getDescription(false),
                "18101", 0, ex);
		
		return handleExceptionInternal(ex, clientError, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected final ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request){
		
		logger.info("Bad Request: {}" +ex.getMessage());
		logger.debug("Bad Request: "+ex);
		
		final TrainingErrorInformationDTO clientError = new TrainingErrorInformationDTO(
				HttpStatus.BAD_REQUEST, "Missing request parameter", request.getDescription(false),
                "18101", 0, ex);
		
		return handleExceptionInternal(ex, clientError, headers, HttpStatus.BAD_REQUEST, request);
		
	}
	
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request){
		
		logger.info("Bad Request: {}" +ex.getMessage());
		logger.debug("Bad Request: "+ex);
		
		final TrainingErrorInformationDTO clientError = new TrainingErrorInformationDTO(
				HttpStatus.BAD_REQUEST, "Validation errors", request.getDescription(false),
                "18101", 0, ex);
		
		return handleExceptionInternal(ex, clientError, headers, HttpStatus.BAD_REQUEST, request);
		
	}
}


