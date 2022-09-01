package com.revature.Banking_0_Banking_Application;

import java.util.Scanner;

import com.revature.Banking_0_Banking_Application.controller.UserController;
import com.revature.Banking_0_Banking_Application.models.User;
import com.revature.Banking_0_Banking_Application.repository.UserDao;
import com.revature.Banking_0_Banking_Application.repository.UserDaoInterface;
import com.revature.Banking_0_Banking_Application.services.DatabaseConnection;
import com.revature.Banking_0_Banking_Application.services.LoginService;

public class Driver {
	
	public static void main(String[] args) {
		
		boolean running = true;
		int choice;
		User user = null;
		
		UserDaoInterface uDao = new UserDao();
		LoginService loginService = new LoginService(uDao);
		UserController userController = new UserController(new Scanner(System.in),loginService);
		DatabaseConnection connection = new DatabaseConnection();
		
		System.out.println("----------------------------------------");
		System.out.println("Welcome to Project Zero's Banking Application!\n");
		
		while(running) {
			System.out.println("1: Login to Account");
			System.out.println("2: Create an Account");
			System.out.println("3: Exit Application");
			
			System.out.print("Please select a numbered option: ");
			choice = Integer.valueOf(userController.getUserInput());
			
			if(choice == 1 ) {
				System.out.println("\n----------------------------------------\n");
				userController.login();
				
			}else if(choice == 2 ) {
				System.out.println("\n----------------------------------------");
				user = uDao.createdUser(user);
				
			}else if(choice == 3 ) {
				System.out.println("\n----------------------------------------\n");
				running = false;
				
			}else {
				System.out.println("Invalid Entry! Please select again!");
			}
			
		}
		
		
		
		
		
		
		System.out.println("Thank you for using my banking application!");
		
	}

}
