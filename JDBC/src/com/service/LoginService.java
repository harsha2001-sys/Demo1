package com.service;

import com.exception.LoginException;
import com.model.User;

public interface LoginService {

	public abstract String createUser(User user) throws LoginException;

	public abstract User searchById(int id) throws LoginException;

	public abstract User updateUser(User user, String password, int id) throws LoginException;

	public abstract String deleteUserById(int userId) throws LoginException;

}
