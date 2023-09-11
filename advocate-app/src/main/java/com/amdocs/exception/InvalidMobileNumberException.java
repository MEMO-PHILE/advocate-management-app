package com.amdocs.exception;

@SuppressWarnings("serial")
public class InvalidMobileNumberException extends Exception {
	public InvalidMobileNumberException(String message) {
		super(message);
	}
}
