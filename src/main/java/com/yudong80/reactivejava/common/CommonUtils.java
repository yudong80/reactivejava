package com.yudong80.reactivejava.common;

public class CommonUtils {
	public static long startTime;
	
	public static void exampleStart(String title) {
		startTime = System.currentTimeMillis();
		System.out.println("Example: " + title);
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
	
	public static String getShape(String obj) {
		if (obj == null || obj.equals("")) return "NO-SHAPE";		
		if (obj.endsWith("-H")) return "HEXAGON";
		if (obj.endsWith("-O")) return "OCTAGON";
		if (obj.endsWith("-R")) return "RECTANGLE";
		if (obj.endsWith("-T")) return "TRIANGLE";
		if (obj.endsWith("<>")) return "DIAMOND";
		return "BALL";
	}
	
	public static void logWithThread(Object obj) { 
		String threadName = Thread.currentThread().getName(); 
		if (threadName.length() > 15) {
			threadName = threadName.substring(0, 15) + "...";
		}		
		System.out.println(threadName + "| value = " + obj);	
	}
}
