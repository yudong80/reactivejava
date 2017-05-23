package com.yudong80.reactivejava.chapter05;

import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;

public class HelloRxJava2V2 {	
	public void emit() {
		Observable.just("Hello", "RxJava2!!")
		.subscribe(Log::i);
	}

	public static void main(String args[]) { 
		HelloRxJava2V2 demo = new HelloRxJava2V2();
		demo.emit();
	}	
}