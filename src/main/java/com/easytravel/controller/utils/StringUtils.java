package com.easytravel.controller.utils;

public class StringUtils {

	private StringUtils() {
		super();
	}
	
	public static Boolean isNull( final String string ) {
		return string == null;
	}
	
	public static Boolean isEmpty( final String string ) {
		return isNull(string) || string.trim().equals("");
	}
}
