package com.yudong80.reactivejava.chapter01;

import io.reactivex.Observable;

public class HelloReactive {
	public static void main(String args[]) { 
		new HelloReactive().run();
	}
	
	public void run() {
		Observable.just("Hello", "Reactive", "Java!!")
		.subscribe(System.out::println);
	}
}