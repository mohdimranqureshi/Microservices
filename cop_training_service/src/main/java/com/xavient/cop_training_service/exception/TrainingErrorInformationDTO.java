package com.xavient.cop_training_service.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

/*
 * In case of error, this class will be used to send response.
 */
public class TrainingErrorInformationDTO {

	String appStatusCode;

	int httpStatusCode;

	private String displayMessage;

	private String uri;

	private String detailMessage;
	
	TrainingErrorInformationDTO(String appStatusCode, int httpStatusCode, String displayMessage, String uri,
			String detailMessage){
		
		super();
		this.appStatusCode = appStatusCode;
		this.httpStatusCode = httpStatusCode;
		this.displayMessage = displayMessage;
		this.uri = uri;
		this.detailMessage = detailMessage;
	}
	
	public TrainingErrorInformationDTO(HttpStatus httpStatus, String message, String uri, String loginErrorCode,
			int success, final Exception ex) {
		super();
		final String detail = ExceptionUtils.getStackTrace(ex);

		this.appStatusCode = loginErrorCode;
		this.httpStatusCode = httpStatus.value();
		this.displayMessage = message;
		this.uri = uri;
		this.detailMessage = detail;
	}

	public TrainingErrorInformationDTO() {
	}
	
	public String getAppStatusCode() {
		return appStatusCode;
	}

	public void setAppStatusCode(String appStatusCode) {
		this.appStatusCode = appStatusCode;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appStatusCode == null) ? 0 : appStatusCode.hashCode());
		result = prime * result + ((displayMessage == null) ? 0 : displayMessage.hashCode());
		result = prime * result + httpStatusCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainingErrorInformationDTO other = (TrainingErrorInformationDTO) obj;
		if (appStatusCode == null) {
			if (other.appStatusCode != null)
				return false;
		} else if (!appStatusCode.equals(other.appStatusCode))
			return false;
		if (displayMessage == null) {
			if (other.displayMessage != null)
				return false;
		} else if (!displayMessage.equals(other.displayMessage))
			return false;
		if (httpStatusCode != other.httpStatusCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientErrorInformation [appStatusCode=" + appStatusCode + ", httpStatusCode=" + httpStatusCode
				+ ", displayMessage=" + displayMessage + ", uri=" + uri + ", detailMessage=" + detailMessage + "]";
	}
}
