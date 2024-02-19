package com.venkat.bank.RBI;

public interface RBI {
	
	public double showBalance();
	public void withdrawAmount(int amount);
	public void depositAmount(int amount);
	public void BankStatement(String userName, String transactionType, int amount);
}
