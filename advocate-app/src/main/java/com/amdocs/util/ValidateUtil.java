package com.amdocs.util;

/*
 * This class validates data taken from user 
 */

public class ValidateUtil {
	public static boolean isValidName(String name) {
		for(char ch : name.toCharArray()) {
			if(!Character.isLetter(ch) && ch!=' ')
				return false;
		}
		return true;
	}
	
	public static boolean isValidMobileNumber(String number) {
		if(number.length()!=10)
			return false;
		
		for(char ch:number.toCharArray()) {
			if(!Character.isDigit(ch))
				return false;
		}
		return true;
	}
	
	public static boolean checkIfCorrectInput(String number) {
		for(char ch:number.toCharArray()) {
			if(!Character.isDigit(ch))
				return false;
		}
		return true;
	}
}
