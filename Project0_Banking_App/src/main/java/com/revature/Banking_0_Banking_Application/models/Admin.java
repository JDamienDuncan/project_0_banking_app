package com.revature.Banking_0_Banking_Application.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin extends User{
	
	private Scanner sc;

	public Admin(String username, String password, String email, String fname, String lname, int age) {
		super(username, password, email, fname, lname, age);
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
	}
	
	
	// Edit a selected account
	public void editAccount() {
		System.out.println("\n----------------------------------------\n");
		
		System.out.println("Needs to be implemented.");
		
		
		System.out.println("\n----------------------------------------\n");
		
	}
	
	public void acceptAccount() {
		String choice;
		System.out.println("\n----------------------------------------\n");
		

		System.out.println("Displaying Pending Users:");
		try {
			String sql = "SELECT * from customer_status where customer_status = '"+"Pending"+"';";
			
			try(Statement statement = connection.connection.createStatement();){
				ResultSet set = statement.executeQuery(sql);
				
				if(set.next()) {
					System.out.println("Username: "+set.getString(1));
					System.out.println("Status: "+set.getString(2));
					System.out.println("Last Name: "+set.getString(3));
					System.out.println();
					while(set.next()) {
							System.out.println("Username: "+set.getString(1));
							System.out.println("Status: "+set.getString(2));
							System.out.println("Last Name: "+set.getString(3));
							System.out.println();
					}
				}else {
					System.out.println("\nNo More Current Pending Accounts.\n");
				}
				
				System.out.print("Please select the username of accepted account or none: ");
				try {
					choice = String.valueOf(sc.nextLine());
					String sql2 = "update customer_status set customer_status = '"+"Accepted"+"' where username = '"+choice+"';";
					statement.executeUpdate(sql2);
					System.out.println("\nAccount status has been changed to accepted.\n");
					
					
				}catch(NumberFormatException e){
					consoleLogger.debug("Invalid entry entered. Enter valid entry.");
					fileLogger.debug(e.getMessage());
				
				}
			} catch (SQLException e) {
				consoleLogger.debug("Invalid entry entered. Enter valid entry.");
				fileLogger.debug(e.getMessage());
			}
		
		
		}catch(NumberFormatException e) {
			consoleLogger.debug("Invalid entry entered. Enter valid entry.");
			fileLogger.debug(e.getMessage());
			
		}
		
		
		System.out.println("\n----------------------------------------\n");
		
	}
	
	// Decline a new customer account
	public void declineAccount() {
		String choice;
		System.out.println("\n----------------------------------------\n");
		

		System.out.println("Displaying Pending Users:");
		try {
			String sql = "SELECT * from customer_status where customer_status = '"+"Pending"+"';";
			
			try(Statement statement = connection.connection.createStatement();){
				ResultSet set = statement.executeQuery(sql);
				
				if(set.next()) {
					System.out.println("Username: "+set.getString(1));
					System.out.println("Status: "+set.getString(2));
					System.out.println("Last Name: "+set.getString(3));
					System.out.println();
					while(set.next()) {
							System.out.println("Username: "+set.getString(1));
							System.out.println("Status: "+set.getString(2));
							System.out.println("Last Name: "+set.getString(3));
							System.out.println();
					}
				}else {
					System.out.println("\nNo More Current Pending Accounts.\n");
				}
				
				System.out.print("Please select the username of declined account or none: ");
				try {
					choice = String.valueOf(sc.nextLine());
					
					set = statement.executeQuery("select user_id from users where username = '"+choice+"';");
					
					set.next();
					int temp = set.getInt(1);
					
					
					final String sql0 = "DELETE from customer_status where username = '"+choice+"';";
					final String sql1 = "DELETE from customers where user_id1 = '"+temp+"';";
					final String sql2 = "DELETE from users where user_id = '"+temp+"';";
					final String sql3 = "DELETE from user_types where user_id = '"+temp+"';";
					final String finalSql = sql0 + sql1 + sql2 + sql3;
					
					statement.executeUpdate(finalSql);
					System.out.println("\nAccount has been changed to declined and deleted.\n");
					
					
				}catch(NumberFormatException e){
					consoleLogger.debug("Invalid entry entered. Enter valid entry.");
					fileLogger.debug(e.getMessage());
				
				}
			} catch (SQLException e) {
				consoleLogger.debug("Invalid entry entered. Enter valid entry.");
				fileLogger.debug(e.getMessage());
			}
		
		
		}catch(NumberFormatException e) {
			consoleLogger.debug("Invalid entry entered. Enter valid entry.");
			fileLogger.debug(e.getMessage());
			
		}
		
		
		System.out.println("\n----------------------------------------\n");
		
	}
	
	// View a specific account
	public void viewAccount() {
		int choice;
		
		System.out.println("\n----------------------------------------\n");
		
		System.out.print("Enter Target Account ID: ");
		try {
			choice = Integer.valueOf(sc.nextLine());
			String sql = "SELECT * from users where user_id = '"+choice+"';";
			
			try(Statement statement = connection.connection.createStatement();){
				ResultSet set = statement.executeQuery(sql); 
				if(set.next()) {
					System.out.println("Here is the users account information:");
					System.out.println("Last Name : "+set.getString(1));
					System.out.println("First Name: "+set.getString(2));
					System.out.println("Username = "+set.getString(3));
					System.out.println("Account Type: Customer");
					System.out.println("Email: "+set.getString(7));
				}else {
					System.out.println("Entered ID is not valid.\n");
				}
				
			} catch (SQLException e) {
				consoleLogger.debug("Entered ID is not valid.\n");
				fileLogger.debug(e.getMessage());
			}
		
		
		}catch(NumberFormatException e) {
			consoleLogger.debug("Entry is invalid. Enter valid entry. ");
			fileLogger.debug(e.getMessage());
			
		}
		
		
		System.out.println("\n----------------------------------------\n");
		
	
		
	}
	
	
	// Delete a selected account
	public void deleteAccount() {
		int choice;
		String temp;
		System.out.println("\n----------------------------------------\n");
		
		System.out.print("Enter account ID to be Deleted:");
		try {
			choice = Integer.valueOf(sc.nextLine());
			
			try(Statement statement = connection.connection.createStatement();){
				
				String sql = "select username from users where user_id = '"+choice+"';";
				ResultSet set = statement.executeQuery(sql);
				
				set.next();
				temp = set.getString(1);
				
				
				final String sql0 = "DELETE from customer_status where username = '"+temp+"';";
				final String sql1 = "DELETE from customers where user_id = '"+choice+"';";
				final String sql2 = "DELETE from users where user_id = '"+choice+"';";
				final String sql3 = "DELETE from user_types where user_id = '"+choice+"';";
				final String finalSql = sql0 + sql1 + sql2 + sql3;
				
				statement.executeUpdate(finalSql);
				
				}catch (SQLException e) {
					consoleLogger.debug("Invalid username entered. Enter valid entry.");
					fileLogger.debug(e.getMessage());
				}
		}catch(NumberFormatException e) {
			consoleLogger.debug("Invalid entry entered. Enter valid entry.");
				fileLogger.debug(e.getMessage());
			}
		
		
		System.out.println("\n----------------------------------------\n");
		
		}
		
	


	// Admin Account information
	public void viewAcc() {
		System.out.println("\n----------------------------------------\n");
		
		System.out.println("Here is your account information:");
		System.out.println("Account ID = 1");
		System.out.println("Username = "+this.username);
		System.out.println("Account Type: Admin");
		System.out.println("Name : "+this.fname+" "+this.lname);
		System.out.println("Email: "+this.email);
		System.out.println("Age : "+this.age);
		
		System.out.println("\n----------------------------------------\n");
	}
	
	// Main Admin Driver
	public void mainDriver() {
		boolean banking = true;
		int choice;
		
		while(banking) {
			
			System.out.println("1: Edit Account");
			System.out.println("2: View an Account");
			System.out.println("3: Delete an Account");
			System.out.println("4: Accept New Account");
			System.out.println("5: Decline New Account");
			System.out.println("6: View Account Information");
			System.out.println("7: Log Out");
			System.out.print("Please select a numbered option: ");
			choice = Integer.valueOf(sc.nextLine());
			
			if(choice == 1) {
				this.editAccount();
			}else if(choice == 2) {
				this.viewAccount();
			}else if(choice == 3) {
				this.deleteAccount();
			}else if(choice == 4) {
				this.acceptAccount();
			}else if(choice == 5) {
				this.declineAccount();
			}else if(choice == 6) {
				this.viewAcc();
			}else if(choice == 7) {
				banking = false;
				System.out.println("\n----------------------------------------\n");
				System.out.println("\nLogged Out!\n");
				System.out.println("\n----------------------------------------\n");
				
			}else {
				System.out.println("\nInvalid Entry! Please select again!\n");
			}
		}
	}

}
