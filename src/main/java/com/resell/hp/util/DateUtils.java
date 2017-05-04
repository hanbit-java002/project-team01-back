package com.resell.hp.util;

import java.text.SimpleDateFormat;

public class DateUtils {
	
	// YYYY/MM/DD 형식
	public static String convertDatePattern(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
		String formattedDate = formatter.format(date);
				
		return formattedDate;
	}
}
