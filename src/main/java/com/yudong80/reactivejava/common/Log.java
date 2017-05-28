package com.yudong80.reactivejava.common;

import java.util.List;

public class Log {
	public static void d(String tag, Object obj) { 
		System.out.println(getThreadName() + " | " + tag + " | debug = " + obj);
	}
	
	public static void e(String tag, Object obj) { 
		System.out.println(getThreadName() + " | " + tag + " | error = " + obj);		
	}

	public static void i(String tag, Object obj) { 
		System.out.println(getThreadName() + " | " + tag + " | value = " + obj);
	}

	public static void v(Object obj) { 
		System.out.println(getThreadName() + " | " + obj);
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
	
//	public static void it(List<String> list) { 
//		long time = System.currentTimeMillis() - CommonUtils.startTime;
//		StringBuffer sb = new StringBuffer();
//		sb.append(getThreadName() + " | " + time + " | " + "value = {"); 
//		for (String str : list) { 
//			sb.append(str);
//			sb.append(",");
//		}
//		sb.append("}");
//		System.out.println(sb.toString().replace(",}", "}"));
//	}

	public static void dt(Object obj) { 
		long time = System.currentTimeMillis() - CommonUtils.startTime;
		System.out.println(getThreadName() + " | " + time + " | " + "debug = " + obj);		
	}

	public static void et(Object obj) { 
		long time = System.currentTimeMillis() - CommonUtils.startTime;
		System.out.println(getThreadName() + " | " + time + " | " + "error = " + obj);		
	}

	public static void onNextT(Object obj) { 
		long time = System.currentTimeMillis() - CommonUtils.startTime;
		System.out.println(getThreadName() + " | " + time + " | " + "onNext >> " + obj);		
	}

	public static void onCompleteT() { 
		long time = System.currentTimeMillis() - CommonUtils.startTime;
		System.out.println(getThreadName() + " | " + time + " | " + "onComplete");		
	}
	
	public static String getThreadName() { 
		String threadName = Thread.currentThread().getName(); 
		if (threadName.length() > 30) {
			threadName = threadName.substring(0, 30) + "...";
		}		
		return threadName;
	}
}
