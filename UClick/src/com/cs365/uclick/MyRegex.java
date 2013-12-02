package com.cs365.uclick;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.EditText;

public class MyRegex {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";;

	public static boolean isEmailValid(String email) {

		Pattern pattern = Pattern.compile(EMAIL_PATTERN,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidPassword(String password) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean isValidEditText(String s) {
		if (s.length() > 0)
			return true;
		return false;
	}

}
