package com.revature.Banking_0_Banking_Application.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer extends User{
	
	protected float balance;
	Scanner sc;

	public Customer(String username, String password, String email, String fname, String lname, int age, float balance) {
		super(username, password, email, fname, lname, age);
		this.balance = balance;
		sc = new Scanner(System.in);
	}
	
	// Checks balance --------------------------------------------------------------------------------
	public void checkBal() {
		System.out.println("\n----------------------------------------\n");
		
		final String sql = "select Distinct balance from customers where user_id = '"+this.getUser_id()+"';";
		
		try(Statement statement = connection.connection.createStatement();){
			ResultSet set = statement.executeQuery(sql);
			if(set.next()) {
				balance = set.getFloat(1);
				System.out.println("Current Balance: "+ balance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n----------------------------------------\n");
		
	}
	
	// Withdraw money --------------------------------------------------------------------------------
	public void withdraw() {
		float temp = 0;
		//final String withdrawSql = "update customers set balance = '"+temp+"' where user_id = '"+this.getUser_id()+"';";
		
		System.out.println("\n----------------------------------------\n");
		
		this.checkBal();
		System.out.println("How much would you like to withdraw?");
		System.out.print("Enter Amount: ");
		
		
		try {
			temp = Float.valueOf(sc.nextLine());
			
			if(temp <= balance) {
				temp = balance - temp;
				balance = temp;
				final String withdrawSql = "update customers set balance = '"+temp+"' where user_id = '"+this.getUser_id()+"';";

				try(Statement statement = connection.connection.createStatement();){
					statement.executeUpdate(withdrawSql);				
				} catch (SQLException e) {
					
				}
			}else if(temp > balance) {
				System.out.println("Withdrawal request denied, account lacks sufficent amount.");
			}else {
				System.out.println("Invalid entry detected. Please Enter valid amount.");
			}
		}catch(NumberFormatException e) {
			System.out.println("Invalid entry detected. Please Enter valid amount.\n");
			temp =  0;
		}
			
		
		System.out.println("\n----------------------------------------\n");
	}
	
	// Deposit money --------------------------------------------------------------------------------
	public void deposit() {
		float temp = 0;
		//final String depositSql = "update customers set balance = '"+temp+"' where user_id = '"+this.user_id+"';";
		
		System.out.println("\n----------------------------------------\n");
		
		
		System.out.println("How much would you like to deposit?");
		System.out.print("Enter Amount: ");
		
		
		try {
			temp = Float.valueOf(sc.nextLine());
			final String depositSql = "update customers set balance = '"+temp+"' where user_id = '"+this.user_id+"';";
			
			if(temp > 0) {
				
				temp = temp + balance;
				balance = temp;

				try(Statement statement = connection.connection.createStatement();){
					System.out.println(temp);
					statement.executeUpdate(depositSql);				
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}else if(temp <= 0) {
				System.out.println("Withdrawal request denied, insufficent deposit requested.");
			}else {
				System.out.println("Invalid entry detected. Please Enter valid amount.");
			}
		}catch(NumberFormatException e) {
			System.out.println("Invalid entry detected. Please Enter valid amount.");
		}
			
		
		System.out.println("\n----------------------------------------\n");
	}
	
	// Transfer money between accounts--------------------------------------------------------------------------------
	public void transfer() {
		int transfer_id;
		float transfer_value;
		
		System.out.println("\n----------------------------------------\n");
		
		System.out.println("Enter Transfer Destination Account ID: ");
		try {
			transfer_id = Integer.valueOf(sc.nextLine());
			System.out.println("Enter Transfer Amount: ");
			transfer_value = Float.valueOf(sc.nextLine());
			
			this.balance = this.balance - transfer_value;
			
			final String transferSql = "update customers set balance = '"+this.balance+"' where user_id = '"+this.user_id+"';";
			final String balanceSql = "update customers set balance = @balance + '"+transfer_value+"' where user_id = '"+transfer_id+"';";
			
			if(balance <= transfer_value) {
				try(Statement statement = connection.connection.createStatement();){
					statement.executeUpdate(transferSql);
					statement.executeUpdate(balanceSql);
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
				
			}
			
			
		}catch(NumberFormatException e) {
			System.out.println("Invalid entry detected. Please Enter valid amount.");
		}
		
		
		System.out.println("\n----------------------------------------\n");
		
	}
	
	// View Account information --------------------------------------------------------------------------------
	public void viewAcc() {
		System.out.println("\n----------------------------------------\n");
		
		System.out.println("\nYou viewed your account!\n");
		
		System.out.println("\n----------------------------------------\n");
	}
	
	// Customer Banking Main --------------------------------------------------------------------------------
	public void bankingMain() {
		boolean banking = true;
		Scanner sc = new Scanner(System.in);
		int choice;
		
		
		
		while(banking) {
			
			System.out.println("1: Check Balance");
			System.out.println("2: Withdraw Funds");
			System.out.println("3: Deposit Funds");
			System.out.println("4: Transfer Funds");
			System.out.println("5: View Account Information");
			System.out.println("6: Log Out");
			System.out.print("Please select a numbered option: ");
			choice = Integer.valueOf(sc.nextLine());
			if(choice == 1) {
				this.checkBal();
			}else if(choice == 2) {
				this.withdraw();
			}else if(choice == 3) {
				this.deposit();
			}else if(choice == 4) {
				this.transfer();
			}else if(choice == 5) {
				this.viewAcc();
			}else if(choice == 6) {
				banking = false;
				System.out.println("\n----------------------------------------\n");
				System.out.println("\nLogged Out!\n");
				System.out.println("\n----------------------------------------\n");
				
			}else {
				System.out.println("\nInvalid Entry! Please select again!\n");
			}
			
			
		}
		
	}
	
	

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", email=" + email;
	}

}
