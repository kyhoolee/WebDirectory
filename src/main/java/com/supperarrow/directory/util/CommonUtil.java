package com.supperarrow.directory.util;

import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
	public static String toString(Object o) throws Exception {
		return ResourceUtil.getObjectMapper().writeValueAsString(o);
	}
	
	
	public static String timeStamp() {
		return (new Date()).toString();
	}
	
	public static long currentHourTime() {
		Calendar c = Calendar.getInstance();
		// System.out.println(c.getTimeInMillis());
		// c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		//System.out.println(c.toString());
		long time = c.getTimeInMillis();
		
		//Date date = new Date(time);
		//System.out.println(date.toString());
		return time;
	}
	
	public static long currentDayTime() {
		Calendar c = Calendar.getInstance();
		//System.out.println(c.getTimeInMillis());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		//System.out.println(c.toString());
		long time = c.getTimeInMillis();
		
		//Date date = new Date(time);
		//System.out.println(date.toString());
		return time;
	}
	
	public static long time() {
		return System.currentTimeMillis();
	}
	
	public static long duration(long start) {
		return System.currentTimeMillis() - start;
	}
	
	public static byte[] stringToBytes(String input) {
		return input.getBytes();
	}
	
	public static String bytesToString(byte[] input) {
		return new String(input);
	}
	
	public static String bytes(byte[] input) {
		String result = "";
		for(int i = 0 ; i < input.length ; i ++) {
			result += input[i];
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(currentDayTime());
	}


}
