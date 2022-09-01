package com.revature.Banking_0_Banking_Application.models;

public interface UserInterface {
	
	User login(String username, String password);
	
	boolean logout();
	
	boolean deleteUser();
	
	boolean updateUser(User updatedUser);
	

}
