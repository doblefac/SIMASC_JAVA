package com.ccb.simasc.transversal.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilMascaraTexto {

	private static final String REGEX_ANY_CARACTER = "[\\s\\S]";
	private static final String REGEX_EMAIL_GROUPS = "([\\s\\S]+)(@[\\w\\.-]+\\.\\w{2,20}\\b)";
	private static final String REGEX_ONLY_DOTS = "[\\.]+";
	
	private UtilMascaraTexto() {
		
	}
	
	public static String replaceCharacterByDot(String text) {
				
		if(text == null || text.isEmpty()) return text;
		text = text.trim();
		if(text == null || text.isEmpty()) return text;
		Pattern pattern = Pattern.compile(REGEX_ANY_CARACTER);
		Matcher matcher = pattern.matcher(text); 
		return matcher.replaceAll(".");
	}
	
	public static Matcher obtainPartEmail(String email) {
		
		Pattern pattern = Pattern.compile(REGEX_EMAIL_GROUPS);
		Matcher matcher = pattern.matcher(email);
		matcher.matches();
		return matcher;		
	}
	
	public static String replaceEmailCharactersByDot(String email) {
				
		if(email == null || email.isEmpty()) return email;
		email = email.trim();
		if(email == null || email.isEmpty()) return email;
		Matcher emailMatcher = obtainPartEmail(email);
		String firtsPartEmail = emailMatcher.group(1);
		String secondPartEmail = emailMatcher.group(2);
		String emailWithDot = replaceCharacterByDot(firtsPartEmail);
		if(emailWithDot != null) {
			return emailWithDot.concat(secondPartEmail);
		}else {
			return email;
		}
		
	}
	
	public static Boolean hasOnlyDots(String text) {
				
		if(text == null || text.isEmpty()) return false;
		text = text.trim();
		if(text == null || text.isEmpty()) return false;
		Pattern pattern = Pattern.compile(REGEX_ONLY_DOTS);
		Matcher matcher = pattern.matcher(text); 
		return matcher.matches();
	}
	
	public static Boolean hasEmailOnlyDots(String email) {
				
		if(email == null || email.isEmpty()) return false;
		email = email.trim();
		if(email == null || email.isEmpty()) return false;
		Matcher emailMatcher = obtainPartEmail(email);
		String firtsPartEmail = emailMatcher.group(1);			
		return hasOnlyDots(firtsPartEmail);		
	}
}
