package com.venkat.bank.service;
import com.venkat.bank.data.userdata.users;
import com.venkat.bank.data.configdata.configs;

import java.util.Date;
import java.util.LinkedList;

import com.venkat.bank.Exceptions.InvalidAmountException;
import com.venkat.bank.Exceptions.MaxIterationReachedException;
import com.venkat.bank.Exceptions.NoUserFoundException;
import com.venkat.bank.RBI.RBI;
import com.venkat.bank.data.bank.BankStatement;
import com.venkat.bank.data.bank.HelpSupport;

public class bankService implements RBI {
	
	private int withdraw_count=1;
	private users u;
	private configs c;
	public static LinkedList<BankStatement> statements=new LinkedList<>();
	public static LinkedList<HelpSupport> compliants=new LinkedList<>();
	
	public bankService(users u, configs c) {
	
		this.u = u;
		this.c = c;
	}
	
	@Override
	public double showBalance() {
        return u.getBalance();
    }
	
	@Override
    public void withdrawAmount(int amount) {
		try{
			if(amount % 100 ==0)
			{
				if(amount>u.getBalance())
				{
					System.out.println("Insufficient Balance !");
				}
				else if ((u.getBalance()-amount-(amount * (c.getWithdraw_charges()/ 100))< c.getMinimum_balance())) {
					System.out.println("Withdrawal failed: Minimum balance requirement not met.");
				}
				else
				{	
						if(withdraw_count<=3)
						{
							double amt= u.getBalance()
			;				amt = amt - amount - (amount * (c.getWithdraw_charges() / 100));
							u.setBalance(amt);
							System.out.println("The amount has been withdrawn successfully");
							withdraw_count++;
							System.out.println("The Current balance is " + showBalance());
						}
						else
							throw new MaxIterationReachedException("Withdraw limit exceeded !");
				}
			}
			else
				throw new InvalidAmountException("Please Enter the Amount in the multiples of 100");
		}catch (InvalidAmountException e){
			
			System.out.println("Caught");
			System.out.println(e.getMessage());
		}
		catch(MaxIterationReachedException e) {
			
			System.out.println("Caught");
			System.out.println(e.getMessage());
		}
		BankStatement(u.getName(),"withdraw",amount);
    }

	@Override
    public void depositAmount(int amount) {
    	
    	double amt= u.getBalance();
;       amt = amt + amount - ((c.getDeposit_charges() / 100) * amount);
		u.setBalance(amt);
        System.out.println("The amount has been deposited successfully");
        System.out.println("The Current balance is " + showBalance());
        
        BankStatement(u.getName(),"deposit",amount);
        
    }
	
	@Override
	public void BankStatement(String userName, String transactionType,int amount) {
		
		if(transactionType.equals("withdraw"))
			statements.add(new BankStatement(u.getName(),"WithDraw",amount,u.getBalance(),new Date()));
		else if(transactionType.equals("deposit"))
			statements.add(new BankStatement(u.getName(),"Deposit",amount,u.getBalance(),new Date()));
	}
	
	public void onlineTransfer(String userName,int amount) {
		
		try {
			UserService us = new UserService();
			users rec = us.returnUser(userName);
			
			if(rec==null) {
				throw new NoUserFoundException("User not found !!");
			}
			if(u.getBalance()<amount) {
				
				System.out.println("Insufficient Balance !!!");
				return;
			}
			if((u.getBalance()-amount)<c.getMinimum_balance()) {
				
				System.out.println("Transaction failed due to insufficient balance!");
				return;
			}
			u.setBalance(u.getBalance()-amount);
			BankStatement(u.getName(),"Transfer to",amount);
			rec.setBalance(rec.getBalance()+amount);
			BankStatement(rec.getName(),"Transfer from",amount);
		}catch (NoUserFoundException e) {
			
			System.out.println("Caught");
			System.out.println(e.getMessage());
			return;
		}
	}
	
	public void addComplaint(String complaint) {
		
		
		compliants.add(new HelpSupport(complaint,u.getName(),new Date(),"Venkat"));
	}
	public void displayComplaint() {
		System.out.println("userName "+"complaint "+"RaiseDate "+"resolved "+"admin "+"dateResolved ");
		for(HelpSupport h :compliants) {
			System.out.println(h.getUserName()+" "+h.getComplaint()+" "+h.getDate()+" "+h.getResolved()+" "+"admin "+" "+h.getDateResolved()+" " );
		}
	}
	public void resolveCompliant(String username) {
		//check if user exist
		for(HelpSupport h:compliants) {
			if(username.equals(h.getUserName())) {
				
				h.setResolved(true);
				h.setDateResolved(new Date());
				System.out.println("compliant resolved");
			}
		}
	}
    public LinkedList<BankStatement> returnStatement() {
    	
    	if(statements.isEmpty()) {
    		
    		System.out.println("No transactionns.");
    		return null;
    	}
    	
    	return statements;
    	
    }

}
