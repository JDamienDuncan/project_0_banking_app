package com.revature.Banking_0_Banking_Application.repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.Banking_0_Banking_Application.models.Customer;
import com.revature.Banking_0_Banking_Application.models.User;
import com.revature.Banking_0_Banking_Application.services.DatabaseConnection;

public class UserDao implements UserDaoInterface{
	
	private Scanner sc;
	private DatabaseConnection connection;
	private Random r;
	public Logger consoleLogger;
	public Logger fileLogger;
	
	public UserDao() {
		consoleLogger = LoggerFactory.getLogger("consoleLogger");
		fileLogger = LoggerFactory.getLogger("fileLogger");
		connection= new DatabaseConnection();
		sc = new Scanner(System.in);
		r = new Random();
	}

	@Override
	public User createdUser(User newUser) {
		// Create needed variables
		String lname, fname, usern, passw, email;
		String type = "Customer";
		int id = r.nextInt(10000-5) + 5;
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
		newUser.setUser_id(id);
		
		final String sql = "INSERT INTO user_types VALUES ('"+newUser.getUser_id()+"', '"+type+"');";
		final String sql1 = "INSERT INTO customers VALUES ('"+newUser.getUser_id()+"', '"+balance+"', '"+newUser.getLname()+"');";
		final String sql4 = " INSERT INTO customer_status VALUES('"+newUser.getUsername()+"', '"+"Pending"+"', '"+newUser.getUsername()+"');";
		final String sql2 = "INSERT INTO users "
				+ "	VALUES('"+lname+"', '"+fname+"',"
						+ " '"+usern+"', '"+passw+"', '"+age+"', '"+id+"', '"+email+"');";
		
		final String sql3 = sql + sql2 + sql1 + sql4;
		
		
		// Return newUser
		//System.out.println(newUser.getUser_id());
		try(Statement statement = connection.connection.createStatement();){
			
			statement.executeUpdate(sql3);
			
		} catch (SQLException e) {
			consoleLogger.debug(e.getMessage());
			fileLogger.debug(e.getMessage());
			
		}
		
		System.out.println("\nYour account has been created!\n");
		System.out.println("----------------------------------------\n");
		return newUser;
	}

	@Override
	public User getUser(String username, String password) {
		
		final String sql = "Select * FROM users WHERE username = '"+username+"';";
		
		try(Statement statement = this.connection.connection.createStatement();){
			ResultSet set = statement.executeQuery(sql);
			if(set.next()) {
			}
		} catch (SQLException e) {
			consoleLogger.debug(e.getMessage());
			fileLogger.debug(e.getMessage());
			
		}
		
		
		return null;
	}

	@Override
	public User updatedUser(User newUser) {
		
		return null;
	}

	@Override
	public User deleteUser(User newUser) {
		System.out.println("----------------------------------------\n");
		
		final String sql0 = "DELETE from customer_status where username = '"+newUser.getUsername()+"';";
		final String sql1 = "DELETE from customers where user_id = '"+newUser.getUser_id()+"';";
		final String sql2 = "DELETE from users where user_id = '"+newUser.getUser_id()+"';";
		final String sql3 = "DELETE from user_types where user_id = '"+newUser.getUser_id()+"';";
		final String finalSql = sql0 + sql1 + sql2 + sql3;
		
		try(Statement statement = connection.connection.createStatement();) {
			statement.executeQuery(finalSql);
			System.out.println("The account has been deleted!");
			
		}catch(SQLException e) {
			consoleLogger.debug(e.getMessage());
			fileLogger.debug(e.getMessage());
		}
		
		System.out.println("----------------------------------------\n");
		return null;
	}

}
