package com.revature.Banking_0_Banking_Application.models;

import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.Banking_0_Banking_Application.services.DatabaseConnection;

public abstract class User {
	
	protected static int users = 3;
	protected int user_id;
	protected String username;
	protected String password;
	protected String email;
	protected String fname;
	protected String lname;
	protected int age;
	protected String type;
	protected DatabaseConnection connection;
	protected Random r;
	protected Logger consoleLogger;
	protected Logger fileLogger;
	
	
	
	
	public User(String username, String password, String email, String fname, String lname, int age) {
		super();
		r = new Random();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		connection = new DatabaseConnection();
		consoleLogger = LoggerFactory.getLogger("consoleLogger");
		fileLogger = LoggerFactory.getLogger("fileLogger");
		
	}

	public static int getUsers() {
		return users;
	}

	public static void setUsers(int users) {
		User.users = users;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	



	@Override
	public int hashCode() {
		return Objects.hash(email, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	public abstract void mainDriver();

	

}
