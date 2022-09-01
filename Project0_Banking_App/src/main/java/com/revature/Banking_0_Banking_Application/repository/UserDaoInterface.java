package com.revature.Banking_0_Banking_Application.repository;

import com.revature.Banking_0_Banking_Application.models.*;

public interface UserDaoInterface {
	
	// Must Use CRUD Methods
	
	
	// C = Create
	
	Object consoleLogger = null;

	User createdUser(User newUser);
	
	// R = Read 
	
	User getUser(String username, String password);
	
	// U = Update
	
	User updatedUser(User newUser);
	
	// D = Delete
	
	User deleteUser(User newUser);

}
