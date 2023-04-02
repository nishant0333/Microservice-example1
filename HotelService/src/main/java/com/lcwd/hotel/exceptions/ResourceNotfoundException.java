package com.lcwd.hotel.exceptions;

public class ResourceNotfoundException extends RuntimeException {

	public ResourceNotfoundException() {
		
		super("Resource not found !!");
	}
	
	public ResourceNotfoundException(String s) {
		
		super(s);
	}
}
