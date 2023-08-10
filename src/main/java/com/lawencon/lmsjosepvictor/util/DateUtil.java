package com.lawencon.lmsjosepvictor.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static String dateFormat (LocalDateTime localDateTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		
		return localDateTime.format(formatter);
	}
	
	public static LocalDateTime dateParse(String dateStr) {
		final LocalDateTime date = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
		return date;
	}
}
