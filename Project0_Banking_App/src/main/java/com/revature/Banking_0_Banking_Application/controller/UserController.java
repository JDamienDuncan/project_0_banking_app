package com.revature.Banking_0_Banking_Application.controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.Banking_0_Banking_Application.models.Admin;
import com.revature.Banking_0_Banking_Application.models.Customer;
import com.revature.Banking_0_Banking_Application.models.Employee;
import com.revature.Banking_0_Banking_Application.models.User;
import com.revature.Banking_0_Banking_Application.services.DatabaseConnection;
import com.revature.Banking_0_Banking_Application.services.LoginService;

public class UserController implements UserInputInterface, LoginInterface{
	
	private Scanner sc;
	private DatabaseConnection connection;
	

	public UserController(Scanner sc, LoginService loginService) {
		super();
		this.sc = new Scanner(System.in);
		connection = new DatabaseConnection();
	}

	
	
	
	
	
	@Override
	public User validateLogin(String username, String password) {
		User user = null;
		final String sql = "Select * FROM users WHERE username = '"+username+"' AND passw = '" +password+"';";
		String lname, fname, usern, passw, email;
		int age,id;
		
		try(Statement statement = connection.connection.createStatement();){
			ResultSet set = statement.executeQuery(sql);
			
			if(set.next()) {
				lname = set.getString(1);
				fname = set.getString(2);
				usern = set.getString(3);
				passw = set.getString(4);
				age = set.getInt(5);
				id = set.getInt(6);
				email = set.getString(7);
				if(id == 1) {
					user = new Admin(usern, passw, email, fname, lname, age);
					return user;
				}else if(id == 2) {
					user = new Employee(usern, passw, email, fname, lname, age);
					return user;
					
				}else {
					user = new Customer(usern, passw, email, fname, lname, age, 0);
					user.setUser_id(id);
					return user;
				}
				
			}else {
				return null;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserInput() {
		return sc.nextLine();
	}

	@Override
	public void login() {
		System.out.println("Please login by typing your username and password \n");
		System.out.print("Enter Username: ");
		String username = sc.nextLine();
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		User user = validateLogin(username, password);
		
		if(user != null) {
			System.out.println("\nWelcome back, "+user.getFname()+" "+user.getLname()+"!");
			System.out.println("----------------------------------------\n");
			
			user.mainDriver();
		}else {
			System.out.println("\nFailed login attempt: invalid password or username.\n");
			
		}
	}
}
