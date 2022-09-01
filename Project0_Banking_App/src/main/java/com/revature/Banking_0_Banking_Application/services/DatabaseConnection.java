package com.revature.Banking_0_Banking_Application.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public String url;
	
	public Connection connection;
	
	private int port = 5555;
	
	private String databaseName = "postgres";
	
	private String databaseUser = "postgres";
	
	private String databasePassword = "database";
	
	 private String host = "jdduncan-revature-database.cudk92rje4ex.us-west-1.rds.amazonaws.com";
	 
	 public DatabaseConnection(){
		 try{
		        url = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
		        connection = DriverManager.getConnection(url, databaseUser, databasePassword);
		       // System.out.println("Connection Success!");
	        }catch (SQLException e) {
	            //System.out.println("Connection Failure: " + url);
	            e.printStackTrace();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	}
		 
}
