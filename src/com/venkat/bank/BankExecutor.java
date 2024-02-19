package com.venkat.bank;

import java.util.*;

import com.venkat.bank.service.UserService;
import com.venkat.bank.service.bankService;
import com.venkat.bank.Exceptions.InvalidUserCredException;
import com.venkat.bank.data.bank.BankStatement;
import com.venkat.bank.data.configdata.configs;
import com.venkat.bank.data.userdata.users;

public class BankExecutor{	
	
        public void loginAndRun(Scanner s) {
	        UserService us= new UserService();
	        BankExecutor b = new BankExecutor();
	        users name;
	        while(true) {
				System.out.println("Enter the username: ");
				String user=s.nextLine();
				name = us.returnUser(user);
				if(name==null) {
					System.out.println("User Not Found!!");
					continue;
				}
				System.out.println("Enter the password: ");
				String pass=s.nextLine();
				boolean flagPass = us.CheckPass(user,pass);
				try {
				if(!flagPass) {
					
					throw new InvalidUserCredException("Incorrect Password!");
				}
				}catch (InvalidUserCredException e) {
					
					System.out.println("Caught");
					System.out.println(e);
				}
				if(flagPass) break;
	        }
			
			String userType = name.getRole();
			
			switch(userType) {
			
				case "user":
					runUser(s,us,name,b);
					break;
				case "admin":
					runAdmin(s,us,name,b);
					break;
				default:
					System.out.println("Invalid user role");
		}
			
    }
    private static void runUser(Scanner s, UserService us, users name,BankExecutor bb) {

		Scanner input = new Scanner(System.in);
		configs c = new configs(0.14,0.012,1000);
		bankService b= new bankService(name,c);
		int option, withdraw, deposit;
        String continu;
        System.out.println("Welcome to Bank Application : ");
    
		do{
				System.out.println("1. Withdraw Amount");
				System.out.println("2. Deposit Amount");
				System.out.println("3. Online Transfer");
				System.out.println("4. Show Balance Amount");
				System.out.println("5. Show Bank Statement");
				System.out.println("6. Help and Support");
				System.out.println("7. Logout");
				System.out.println("Enter the choice: ");
				option = s.nextInt();

				switch (option) {
					case 1:
						System.out.println("Enter the amount to be withdrawn: ");
						withdraw = s.nextInt();
						b.withdrawAmount(withdraw);
						break;
					case 2:
						System.out.println("Enter the amount to be deposited: ");
						deposit = s.nextInt();
						b.depositAmount(deposit);
						break;
					case 3:
						System.out.println("Enter the person to whom you wannna Transfer: ");
						s.nextLine();
						String uName = s.nextLine();
						System.out.println("Enter the amount:");
						int amount = s.nextInt();
						b.onlineTransfer(uName,amount);
						break;
					case 4:
						System.out.println("The current balance is: " + b.showBalance());
						break;
					case 5:
						LinkedList<BankStatement> state= b.returnStatement();
						if(state!=null) {
							System.out.println("Bank Statement: ");
							System.out.println();
							for(BankStatement bt: state) {
								
								System.out.println();
								System.out.println("The user name: "+bt.getUserName());
								System.out.println("Transaction type: "+bt.getType());
								System.out.println("The amount: "+bt.getAmount());
								System.out.println("The current balance: "+bt.getUserBalance());
								System.out.println("Date: "+bt.getTransactionDate());
								System.out.println();
							}
						}
						break;
					case 6:
						System.out.println("Enter the issue that you are facing: ");
						s.nextLine();
						String compliant = s.nextLine();
						b.addComplaint(compliant);
						System.out.println("Compliant has been raised successfully !!");
						break;
					case 7:
						System.out.println("Do you want to login again? (Yes/No)");
	                    s.nextLine();
	                    String ch = s.nextLine();
	                    if (ch.equalsIgnoreCase("Yes")) {
	                        bb.loginAndRun(s);
	                    } else {
	                        System.out.println("Thank you !!!");
	                        System.exit(0);
	                    }
	                    break;
					default:
						System.out.println("Invalid option !");
				}
				System.out.println("Do you wish to continue???");
				continu=input.nextLine();
			}while(continu.equals("Y") || continu.equals("y"));
		input.close();
    }
    private static void runAdmin(Scanner s, UserService us, users name,BankExecutor bb) {
    	
    	Scanner input = new Scanner(System.in);
    	configs c = new configs(0.14,0.012,1000);
		bankService b= new bankService(name,c);
    	int option;
        String continu;
    	System.out.println("Welcome Admin");
    	do{
			System.out.println("1. Add User");
			System.out.println("2. Remove User");
			System.out.println("3. Display User Details");
			System.out.println("4. View Complaints");
			System.out.println("5. Resolve Complaints");
			System.out.println("6. Logout");
			System.out.println("Enter the choice: ");
			option = s.nextInt();
			s.nextLine();
			switch (option) {
				case 1:
					System.out.println("Enter new Username: ");
					String newName= s.nextLine();
					System.out.println("Enter new Password: ");
					String newPass= s.nextLine();
					System.out.print("Enter initial balance for the new user: ");
                    double newBalance = s.nextDouble();
                    s.nextLine();
                    users newUser= new users(newName,newPass,newBalance,"user");
                    boolean flag= us.checkIfUserExists(newName);
                    if(flag) {
                    	System.out.println("User already exists");
                    }
                    else {
                    	us.addUser(newUser);
                    	System.out.println("New user added successfully !");
                    }
;					break;
				case 2:
					System.out.println("Enter user to remove: ");
					String remUser= s.nextLine();
					users remove = us.returnUser(remUser);
					if(remove !=null) {
						us.removeUser(remove);
						System.out.println("User removed Successfully !");
					}
					else
						System.out.println("User not found");
					break;
				case 3:
                    System.out.println("\nAll User Details:");
                    System.out.println();
                    us.viewUsers();
                    System.out.println();
                    break;
				case 4:
					b.displayComplaint();
					break;
				case 5:
					System.out.println("Enter User's Name: ");
					String u=s.nextLine();
					b.resolveCompliant(u);
					break;
				case 6:
					System.out.println("Do you want to login again? (Yes/No)");
                    s.nextLine();
                    String ch = s.nextLine();
                    if (ch.equalsIgnoreCase("Yes")) {
                        bb.loginAndRun(s);
                    } else {
                        System.out.println("Thank you !!!");
                        System.exit(0);
                    }
					break;
				default:
					System.out.println("Invalid option !");
			}
			System.out.println("Do you wish to continue???");
			continu=input.nextLine();
		}while(continu.equals("Y") || continu.equals("y"));
    	
    	input.close();
    }
    public static void main(String[] args){
    	
    	BankExecutor bankExec = new BankExecutor();
        Scanner s = new Scanner(System.in);
        bankExec.loginAndRun(s);
    }
}

