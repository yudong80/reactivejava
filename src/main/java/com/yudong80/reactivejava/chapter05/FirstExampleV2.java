package com.yudong80.reactivejava.chapter05;

import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;

public class FirstExampleV2 {	
	public void emit() {
		Observable.just("Hello", "RxJava2!!")
		.subscribe(Log::i);
	}

	public static void main(String args[]) { 
		FirstExampleV2 demo = new FirstExampleV2();
		demo.emit();
	}	
}