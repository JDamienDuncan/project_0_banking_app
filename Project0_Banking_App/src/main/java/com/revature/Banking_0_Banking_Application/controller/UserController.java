package com.revature.Banking_0_Banking_Application.controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.Banking_0_Banking_Application.models.Banking;
import com.revature.Banking_0_Banking_Application.models.Customer;
import com.revature.Banking_0_Banking_Application.models.User;
import com.revature.Banking_0_Banking_Application.services.DatabaseConnection;
import com.revature.Banking_0_Banking_Application.services.LoginService;

public class UserController implements UserInputInterface, LoginInterface{
	
	private Scanner sc;
	private LoginService loginService;
	private DatabaseConnection connection;
	

	public UserController(Scanner sc, LoginService loginService) {
		super();
		this.sc = new Scanner(System.in);
		this.loginService = loginService;
		connection = new DatabaseConnection();
		
		
	}

	
	
	
	
	
	@Override
	public User validateLogin(String username, String password) {
		Customer user = null;
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
				user = new Customer(usern, passw, email, fname, lname, age, 0);
				user.setUser_id(id);
				return user;
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
		Customer user = (Customer) validateLogin(username, password);
		
		if(user != null) {
			System.out.println("\nWelcome back, "+user.getFname()+""+user.getLname()+"!");
			System.out.println("----------------------------------------\n");
			user.bankingMain();
			
		}else {
			System.out.println("Failed login attempt: invalid password or username.\n");
			
		}
		
		
	}


}
