package com.dao;

import com.model.*;

import java.util.List;

import com.exception.*;

public interface LoginDao {

	public abstract String createUser(User user) throws LoginException;

	public abstract User searchById(int id) throws LoginException;

	public abstract List allUser();

	public abstract User updateUser(User user, String password, int id) throws LoginException;

	public abstract String deleteUserById(int userId) throws LoginException;

}
