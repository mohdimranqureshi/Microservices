package com.xavient.loginservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.xavient.loginservice.repository.UserRepository;
import com.xavient.loginservice.util.LoginUtil;
import com.xavient.loginservice.validation.LoginValidator;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;
	
	Gson gson = new Gson();

	@RequestMapping(value = "/validateUser", method = RequestMethod.GET)
	public ResponseEntity<String> validateUser(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {

		logger.info("\n entering UserController.validateUser() " + userName, password);
		LoginValidator validator = new LoginValidator();
		validator.validate(userName, null);

		/*
		 * if(result.hasErrors()){ logger.error("Request is not well formed");
		 * throw new LoginException("The Request is not well formed"); }
		 */

		String response = userRepository.validateUser(userName, password);
		if(response.equals(gson.toJson(LoginUtil.invalid_user))){
			logger.info("\n leaving UserController.validateUser() ");
			return new ResponseEntity<String>(response, HttpStatus.UNAUTHORIZED);
		}else{
		logger.info("\n leaving UserController.validateUser() ");
		return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}
}
