package com.yudong80.reactivejava.common;

import java.io.IOException;
import java.net.InetAddress;

public class CommonUtils {
	public static long startTime;

	public static void exampleStart() {
		startTime = System.currentTimeMillis();
	}
	
	public static void exampleComplete() { 
		System.out.println("-----------------------");
	}
	
	public static void sleep(int millis) { 
		try { 
			Thread.sleep(millis);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}		
	}
	
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String numberToAlphabet(long x) { 
		return Character.toString(ALPHABET.charAt((int) x % ALPHABET.length()));
	}
	
	/**
	 * @deprecated 분리 필요 
	 */
	public static String getShape(String obj) {
		if (obj == null || obj.equals("")) return "NO-SHAPE";		
		if (obj.endsWith("-H")) return "HEXAGON";
		if (obj.endsWith("-O")) return "OCTAGON";
		if (obj.endsWith("-R")) return "RECTANGLE";
		if (obj.endsWith("-T")) return "TRIANGLE";
		if (obj.endsWith("<>")) return "DIAMOND";
		if (obj.endsWith("-P")) return "PETAGON";
		return "BALL";
	}
		
	public static boolean isNetworkAvailable() { 
		try {
			return InetAddress.getByName("www.google.com").isReachable(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
