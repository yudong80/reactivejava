package com.yudong80.reactivejava.common;

import io.reactivex.functions.Consumer;

public class ThreeSubscribers {
	protected Consumer<String> firstSubscriber = str -> 
		System.out.println("#1 => " + str);

	protected Consumer<String> secondSubscriber = str -> 
	System.out.println("#2 => " + str);

	protected Consumer<String> thirdSubscriber = str -> 
	System.out.println("#3 => " + str);	
}
