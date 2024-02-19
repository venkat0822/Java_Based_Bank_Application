package com.venkat.bank.data.userdata;

import java.util.LinkedList;

public class users {
	
	public static LinkedList<users> userList;
	static
	{
		userList = new LinkedList<>();
		userList.add(new users("Venkat","Venkat@123",10000,"admin"));
		userList.add(new users("Roshan","Roshan@123",15000,"user"));
		userList.add(new users("Madhu","Madhu@123",14000,"user"));
		userList.add(new users("Kiran","Kiran@123",20000,"admin"));
		
	}
	private String username;
	private String password;
	private double accountBalance;
	private String role;
	
	

	public users(String username, String password, double accountBalance, String role) {
		
		this.username = username;
		this.password = password;
		this.accountBalance = accountBalance;
		this.role = role;
	}
	
	public static LinkedList<users> getUserList() {
		
		return userList;
	}

	public void setName(String username) {
		
		this.username = username;
	}
	
	public void setPass(String password) {
		
		this.password = password;
	}
	
	public void setBalance(double accountBalance) {
		
		this.accountBalance = accountBalance;
	}
	
	public void setRole(String role) {
		
		this.role = role;
	}
	
	public String getName() {
		return username;
	}
	
	public String getPass() {
		return password;
	}
	
	public double getBalance() {
		return accountBalance;
	}

	public String getRole() {
		return role;
	}
}
