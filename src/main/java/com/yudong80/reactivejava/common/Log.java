package com.yudong80.reactivejava.common;

public class Log {
	public static void d(String tag, Object obj) { 
		System.out.println(getThreadName() + "| " + tag + " | value = " + obj);
	}
	
	public static void e(String tag, Object obj) { 
		System.out.println(getThreadName() + "| " + tag + " | error = " + obj);		
	}
	
	public static void d(Object obj) { 
		System.out.println(getThreadName() + " | value = " + obj);
	}
	
	public static void e(Object obj) { 
		System.out.println(getThreadName() + " | error = " + obj);		
	}
	
	public static String getThreadName() { 
		String threadName = Thread.currentThread().getName(); 
		if (threadName.length() > 15) {
			threadName = threadName.substring(0, 15) + "...";
		}		
		return threadName;
	}
}
