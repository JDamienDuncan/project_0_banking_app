package com.revature.Banking_0_Banking_Application.controller;

import com.revature.Banking_0_Banking_Application.models.User;

public interface LoginInterface {
	
	void login();
	
	User validateLogin(String username, String password);

}
