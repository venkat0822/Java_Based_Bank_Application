package com.venkat.bank.data.configdata;

public class configs {
	
	private double withdraw_charges;
	private double deposit_charges;
	private int minimum_balance;
	
		

	public configs(double withdraw_charges, double deposit_charges, int minimum_balance) {
		
		this.withdraw_charges = withdraw_charges;
		this.deposit_charges = deposit_charges;
		this.minimum_balance = minimum_balance;
		
	}


	public double getWithdraw_charges() {
		return withdraw_charges;
	}

	public void setWithdraw_charges(double withdraw_charges) {
		this.withdraw_charges = withdraw_charges;
	}

	public double getDeposit_charges() {
		return deposit_charges;
	}

	public void setDeposit_charges(double deposit_charges) {
		this.deposit_charges = deposit_charges;
	}

	public int getMinimum_balance() {
		return minimum_balance;
	}

	public void setMinimum_balance(int minimum_balance) {
		this.minimum_balance = minimum_balance;
	}
	
	
	
}
