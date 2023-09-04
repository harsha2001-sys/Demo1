package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.exception.LoginException;
import com.model.User;
import com.mysql.cj.xdevapi.PreparableStatement;

public class LoginDaoImpl implements LoginDao {

	public static Connection getConnection() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String driverName = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/joins";
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, "root", "Reset123");
			System.out.println(connection != null ? "connection established" : "connection failed");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("There is no respective jars : " + cnfe.getMessage());
		} catch (SQLException se) {// Catching SQL Exception
			System.out.println("SQL Exception :" + se.getMessage());
		} catch (Exception e) {
			System.out.println(e);
		}

//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
		return connection;
	}

	@Override
	public String createUser(User user) throws LoginException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO  user(user_name, password) values (?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return user.getUserName();
	}

	@Override
	public User searchById(int id) throws LoginException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User dummy = new User();
		String search = "select user_id,user_name,password from user where user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(search);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dummy.setUserId(resultSet.getInt("user_id"));
				dummy.setUserName(resultSet.getString("user_name"));
				dummy.setPassword(resultSet.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dummy;
	}

	@Override
	public List allUser() {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		List<User> list = new ArrayList<User>();
		String query = "select * from user";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt("user_id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPassword(resultSet.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User updateUser(User user, String password, int id) throws LoginException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		int update = 0;
		User user2 = new User();
		String update1 = "update user set password = ? where user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(update1);
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, id);
			update = preparedStatement.executeUpdate();
			if (update == 0) {
				user2 = user;
			}
		} catch (SQLException e) {
			throw new LoginException("Not updated");
		}
		return user2;
	}

	@Override
	public String deleteUserById(int userId) throws LoginException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		String delete = "delete from user where user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(delete);
			preparedStatement.setInt(1, userId);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new LoginException("Invalid userid");
		}
		return "User deleted successfully";
	}
}
