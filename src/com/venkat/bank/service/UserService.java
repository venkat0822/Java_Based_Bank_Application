package com.venkat.bank.service;
import com.venkat.bank.Exceptions.NoUserFoundException;
import com.venkat.bank.data.userdata.users;
import java.util.*;
public class UserService {
	
	
	public LinkedList<users> userList=users.getUserList();
	public Boolean checkIfUserExists(String userName) {
		
		for(users user: userList) {
			if(user.getName().equals(userName))
				return true;
		}
		return false;
	}

	public void addUser(users user) {
		
		boolean flag= checkIfUserExists(user.getName());
		if(flag) {
			System.out.println("User exists");
			return;
		}
		userList.add(user);
	}

	public void removeUser(users user) {
		try {
			boolean flag= checkIfUserExists(user.getName());
			if(!flag){
				throw new NoUserFoundException("User not found");
			}
			userList.remove(user);
		}catch(NoUserFoundException e) {
			
			System.out.println("Caught");
			System.out.println(e.getMessage());
			return;
		}
		
	}

	public users returnUser(String userName) {
			
			for(users user: userList) {
				if(user.getName().equals(userName))
					return user;
		}
			return null;
		}

	public boolean CheckPass(String usname, String pass) {

		for(users user: userList) {
			
			if(user.getName().equals(usname) && user.getPass().equals(pass))
				return true;
		}
		return false;
	}
	public void viewUsers() {
		for(users user: userList) {
			
			if(user.getRole().equals("user"))
				System.out.println("UserName: "+user.getName() +" Balance:"+user.getBalance());
		}
	}

}
