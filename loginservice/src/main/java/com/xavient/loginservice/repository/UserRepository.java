package com.xavient.loginservice.repository;

public interface UserRepository {

	public String validateUser(String userName, String password);
}
