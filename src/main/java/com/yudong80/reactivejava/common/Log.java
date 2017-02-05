package com.yudong80.reactivejava.common;

public class Log {
	public static void d(String tag, Object obj) { 
		System.out.println(getThreadName() + "| " + tag + " | debug = " + obj);
	}
	
	public static void e(String tag, Object obj) { 
		System.out.println(getThreadName() + "| " + tag + " | error = " + obj);		
	}

	public static void i(String tag, Object obj) { 
		System.out.println(getThreadName() + "| " + tag + " | value = " + obj);
	}
		
	public static void d(Object obj) { 
		System.out.println(getThreadName() + " | debug = " + obj);
	}
	
	public static void e(Object obj) { 
		System.out.println(getThreadName() + " | error = " + obj);		
	}

	public static void i(Object obj) { 
		System.out.println(getThreadName() + " | value = " + obj);		
	}
	
	public static void it(Object obj) { 
		long time = System.currentTimeMillis() - CommonUtils.startTime;
		System.out.println(getThreadName() + " | " + time + " | " + "value = " + obj);		
	}
	
	public static String getThreadName() { 
		String threadName = Thread.currentThread().getName(); 
		if (threadName.length() > 15) {
			threadName = threadName.substring(0, 15) + "...";
		}		
		return threadName;
	}
}
