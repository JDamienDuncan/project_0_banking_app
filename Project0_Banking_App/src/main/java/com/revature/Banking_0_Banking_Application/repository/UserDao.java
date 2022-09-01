package com.revature.Banking_0_Banking_Application.repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.revature.Banking_0_Banking_Application.controller.UserController;
import com.revature.Banking_0_Banking_Application.models.Customer;
import com.revature.Banking_0_Banking_Application.models.User;
import com.revature.Banking_0_Banking_Application.services.DatabaseConnection;

public class UserDao implements UserDaoInterface{
	private Scanner sc = new Scanner(System.in);
	private DatabaseConnection connection= new DatabaseConnection();
	private Random r = new Random();

	@Override
	public User createdUser(User newUser) {
		// Create needed variables
		String lname, fname, usern, passw, email;
		String type = "Customer";
		int id = r.nextInt(5000,5) + 5;
	    float balance = (float) 0.00;
		int age;
		
		// Get user input to create new user
		System.out.println("Please Enter the needed information for your new account.\n");
		
		System.out.print("Enter your First Name: ");
		fname = sc.nextLine();
		
		System.out.print("Enter your Last Name: ");
		lname = sc.nextLine();
		
		System.out.print("Enter your email: ");
		email = sc.nextLine();
		
		System.out.print("Enter a Username: ");
		usern = sc.nextLine();
		
		System.out.print("Enter a Password: ");
		passw = sc.nextLine();
		
		System.out.print("Enter your Age: ");
		age = sc.nextInt();
		
		newUser = new Customer(usern, passw, email, fname, lname, age, 0);
		
		final String sql = "INSERT INTO user_types VALUES ('"+newUser.getUser_id()+"', '"+type+"');";
		final String sql1 = "INSERT INTO customers VALUES ('"+balance+"', '"+newUser.getUser_id()+"');";
		final String sql2 = "INSERT INTO users "
				+ "	VALUES('"+lname+"', '"+fname+"',"
						+ " '"+usern+"', '"+passw+"', '"+age+"', '"+id+"', '"+email+"');";
		final String sql3 = sql + sql1 + sql2;
		
		// Return newUser
		
		try(Statement statement = connection.connection.createStatement();){
			statement.executeUpdate(sql3);
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			
		}
		
		System.out.println("Your account has been created!");
		System.out.println("----------------------------------------");
		return newUser;
	}

	@Override
	public User getUser(String username, String password) {
		User user = null;
		
		final String sql = "Select * FROM users WHERE username = '"+username+"';";
		
		DatabaseConnection connection = new DatabaseConnection();
		
		try(Statement statement = connection.connection.createStatement();){
			ResultSet set = statement.executeQuery(sql);
			if(set.next()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public User updatedUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
