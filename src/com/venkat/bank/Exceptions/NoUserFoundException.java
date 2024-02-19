package com.venkat.bank.Exceptions;

public class NoUserFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoUserFoundException(String message) {
		super(message);
	}
	
	

}
