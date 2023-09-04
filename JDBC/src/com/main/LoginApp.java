package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.LoginDao;
import com.dao.LoginDaoImpl;
import com.exception.LoginException;
import com.model.User;
import com.service.LoginService;
import com.service.LoginServiceImpl;

public class LoginApp {

	public static void main(String[] args) {
		LoginDaoImpl.getConnection();

		Scanner scanner = new Scanner(System.in);
		User user = new User();

		System.out.println("1. Create ");
		System.out.println("2. Read ");
		System.out.println("3. Update ");
		System.out.println("4. Delete ");
		System.out.println("Enter your choice ");
		int choice = scanner.nextInt();

		LoginDao dao = new LoginDaoImpl();
		LoginService loginService = new LoginServiceImpl();

		while (choice < 5) {

			switch (choice) {
			case 1: 
				
				try {
					System.out.println("Enter the user name : ");
					String name = scanner.next();
					System.out.println("Enter the password : ");
					String password = scanner.next();
					user.setUserName(name);
					user.setPassword(password);
					String output = loginService.createUser(user);
					if (output != null) {
						System.out.println(output + " User Succesfully Created");
					}
				} catch (LoginException e) {
					System.err.println(e.getMessage());
				}
				break;
			

			case 2: 
				
				try {
					System.out.println("Enter the id ");
					int id = scanner.nextInt();
					user = loginService.searchById(id);
					if (user != null) {
						System.out.println(user.toString());
					}
				} catch (LoginException e) {
					System.err.println(e.getMessage());
				}
				break;
		

			case 3: 

				try {
					System.out.println("Update the password ");
					String password = scanner.next();
					System.out.println("Enter the id ");
					int id1 = scanner.nextInt();
					User update = loginService.updateUser(user, password, id1);
					if (update != null) {
						System.out.println("Updated Successfully ");
					}
				} catch (LoginException e) {
					System.err.println(e.getMessage());
				}
			
				break;
			case 4: 

				try {
					System.out.println("Enter the id ");
					int id2 = scanner.nextInt();
					String delete = loginService.deleteUserById(id2);
					if (delete != null) {
						System.out.println(delete);
					}
				} catch (LoginException e) {
					System.err.println(e.getMessage());
				}	
	}
			System.err.println();
            System.out.println("Enter Y to continue or N to terminate");
            String con = scanner.next();
            if (con.equals("Y")) {
            	System.out.println("1. Create ");
    			System.out.println("2. Read ");
    			System.out.println("3. Update ");
    			System.out.println("4. Delete ");
    			System.out.println("Enter your choice ");
    			choice = scanner.nextInt();
			}
            else if (con.equals("N")){
            	System.out.println("****Thank you******");
            	break;
            }
		}
		
//		List< User> output = new ArrayList<User>();
//		output = dao.allUser();
//		System.out.println(output.toString());	
	}
}
