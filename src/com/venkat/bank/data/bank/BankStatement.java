package com.venkat.bank.data.bank;

import java.util.Date;

public class BankStatement {
	
	private String userName;
	private String type;
	private double amount;
	private double userBalance;
	private Date transactionDate;
	
	
	public BankStatement(String userName, String type, double amount, double userBalance, Date transactionDate) {
		super();
		this.userName = userName;
		this.type = type;
		this.amount = amount;
		this.userBalance = userBalance;
		this.transactionDate = transactionDate;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getUserBalance() {
		return userBalance;
	}


	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}


	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
