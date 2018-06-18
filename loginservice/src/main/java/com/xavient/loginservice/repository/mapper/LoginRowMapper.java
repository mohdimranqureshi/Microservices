package com.xavient.loginservice.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xavient.loginservice.model.UserDetails;

public class LoginRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName(rs.getString("userName"));
		userDetails.setPassword(rs.getString("password"));
		userDetails.setUserType(rs.getInt("role_Value"));
		userDetails.setCopName(rs.getString("cop_name"));
		userDetails.setCopId(rs.getInt("cop_Id"));
		return userDetails;
	}

}
