package com.FleetX.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidationUtil {
	
	 // 1. Validate if a field is null or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // 2. Validate if a string contains only letters
    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }

    // 3. Validate if a string starts with a letter and is composed of letters and numbers
    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }
    
    // 4 Validate if the dob is at least 21 years before today
    public static boolean isAgeAtLeast21(LocalDate dob) {
    	if(dob == null) {
    		return false;
    	}
    	LocalDate todayDate = LocalDate.now();
    	return Period.between(dob, todayDate).getYears() >= 18;
    }
    
    // 5. Validate if a string is a valid email address
    public static boolean isValidEmail(String email) {
    	String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    // 6. Validate if a number is of 10 digits and starts with 98
    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^98\\d{8}$");
    }

    // 7. Validate if a password is composed of at least 1 capital letter, 1 number, and 1 symbol
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }
    
    // Check if repassword & password matches
    public static boolean doPasswordsMatch(String password, String repassword) {
    	return password != null && password.equals(repassword);
    }

}
