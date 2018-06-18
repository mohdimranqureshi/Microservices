package com.xavient.loginservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.xavient.loginservice.model.UserDetails;
import com.xavient.loginservice.repository.mapper.LoginRowMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	public static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@SuppressWarnings("unchecked")
	public String validateUser(String userName, String password) {

		logger.info("Starting UserRepositoryImpl.validateUser()" +userName);
		List<UserDetails> userDetails = new ArrayList<>();
		Gson gson = new Gson();
		String response = null;
		if (userName != null && password != null) {

			String sql = "SELECT user_details.userName, user_details.password, cop.cop_name, cop.cop_Id, user_type.role_Value FROM user_details,cop,user_type where user_details.userId = cop.userId AND user_details.role_Id = user_type.role_Id AND user_details.userName = ? AND user_details.password = ?"; 

			userDetails = (List<UserDetails>) jdbcTemplate.query(sql, new Object[] { userName, password },
					new LoginRowMapper());

			if (userDetails.size() <= 0) {

				return gson.toJson("Invalid UserName and Password");
			} else {

				response = gson.toJson(userDetails);
			}

		}
		logger.info("Leaving UserRepositoryImpl.validateUser()" +response);
		return response;
	}

}
