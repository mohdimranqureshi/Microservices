package com.xavient.cop_training_service.exception;

public class TrainingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrainingException(String message) {
		super(message);
	}

	public TrainingException(String message, Throwable cause) {
		super(message, cause);
	}
}
