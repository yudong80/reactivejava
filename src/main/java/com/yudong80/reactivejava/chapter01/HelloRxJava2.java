package com.yudong80.reactivejava.chapter01;

import io.reactivex.Observable;

public class HelloRxJava2 {	
	public void emit() {
		Observable.just("Hello", "RxJava2!!")
		.subscribe(System.out::println);
	}

	public static void main(String args[]) { 
		HelloRxJava2 demo = new HelloRxJava2();
		demo.emit();
	}	
}