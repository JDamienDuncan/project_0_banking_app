package com.revature.Banking_0_Banking_Application.services;

import com.revature.Banking_0_Banking_Application.models.*;
import com.revature.Banking_0_Banking_Application.repository.UserDaoInterface;

public class LoginService implements UserInterface {
	
	private UserDaoInterface userDao;
	
	public LoginService(UserDaoInterface userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public User login(String username, String password) {
		return userDao.getUser(username, password);
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User updatedUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
