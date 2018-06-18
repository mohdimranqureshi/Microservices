package com.xavient.loginservice.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xavient.loginservice.exception.LoginException;
import com.xavient.loginservice.model.UserDetails;

@Component
public class LoginValidator implements Validator{

	private static final Logger logger = LoggerFactory.getLogger(LoginValidator.class);
	
	@Override
	public boolean supports(Class<?> arg0) {
		
		return UserDetails.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors error) {

		String userName = (String) object;
		logger.info("Inside Validator.validate validating User details Request");
		if(userName == null || userName.trim().length() <= 0){
			throw new LoginException("User Name can not be null.");
		}
		
		/*if(userDetail.getPassword() == null || userDetail.getPassword().trim().length() <= 0){
			throw new LoginException("Password can not be blank");
		}*/
		
	}

}
