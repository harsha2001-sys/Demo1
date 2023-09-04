package com.service;

import java.sql.Connection;

import com.dao.LoginDao;
import com.dao.LoginDaoImpl;
import com.exception.LoginException;
import com.model.User;

public class LoginServiceImpl implements LoginService {

	@Override
	public String createUser(User user) throws LoginException {
		String str = " ";
		Connection connection = LoginDaoImpl.getConnection();
		if (user.getPassword().length() >= 5) {
			LoginDao loginDao = new LoginDaoImpl();
			str = loginDao.createUser(user);
		} else {
			throw new LoginException("Password length is too short");
		}
		return str;
	}

	@Override
	public User searchById(int id) throws LoginException {
		int idlen = String.valueOf(id).length();
		User user = null;
		Connection connection = LoginDaoImpl.getConnection();
		if (idlen < 5) {
			LoginDao loginDao = new LoginDaoImpl();
			user = loginDao.searchById(id);
		} else {
			throw new LoginException("Less than 3 digits");
		}
		return user;
	}

	@Override
	public User updateUser(User user, String password, int id) throws LoginException {
		int idlen = String.valueOf(id).length();
		User update = null;
		if (password.length() > 5 && idlen < 5) {
			LoginDao loginDao = new LoginDaoImpl();
			update = loginDao.updateUser(user, password, id);
		} else {
			throw new LoginException("Validation error");
		}
		return update;
	}

	@Override
	public String deleteUserById(int userId) throws LoginException {
		int idlen = String.valueOf(userId).length();
		String dummy = null;
//		User user = new User();
		Connection connection = LoginDaoImpl.getConnection();
		if (idlen == 3) {
			LoginDao loginDao = new LoginDaoImpl();
			dummy = loginDao.deleteUserById(userId);
		} else {
			throw new LoginException("Invalid id");
		}
		return dummy;
	}

}
