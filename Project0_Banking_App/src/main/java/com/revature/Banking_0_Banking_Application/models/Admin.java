package com.revature.Banking_0_Banking_Application.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

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
		
		System.out.print("Enter Target Account ID: ");
		
		
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
				}
				
			} catch (SQLException e) {
				consoleLogger = LoggerFactory.getLogger("consoleLogger");
				fileLogger = LoggerFactory.getLogger("fileLogger");
			}
		
		
		}catch(NumberFormatException e) {
			consoleLogger = LoggerFactory.getLogger("consoleLogger");
			fileLogger = LoggerFactory.getLogger("fileLogger");
			
		}
		
		
		System.out.println("\n----------------------------------------\n");
		
	
		
	}
	
	
	// Delete a selected account
	public void deleteAccount() {
		System.out.println("\n----------------------------------------\n");
		
		System.out.print("Enter Target Account ID: ");
		
		
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
		Scanner sc = new Scanner(System.in);
		int choice;
		
		while(banking) {
			
			System.out.println("1: Edit Account");
			System.out.println("2: View an Account");
			System.out.println("3: Delete an Account");
			System.out.println("4: View Account Information");
			System.out.println("5: Log Out");
			System.out.print("Please select a numbered option: ");
			choice = Integer.valueOf(sc.nextLine());
			
			if(choice == 1) {
				this.editAccount();
			}else if(choice == 2) {
				this.viewAccount();
			}else if(choice == 3) {
				this.deleteAccount();
			}else if(choice == 4) {
				this.viewAcc();
			}else if(choice == 5) {
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
