package com.yudong80.reactivejava.chapter01;

import io.reactivex.Observable;

public class SimplyReactiveV1 {
	public static void main(String[] args) { 
		new SimplyReactiveV1().run();
	}
	
	public void run() {
		Observable<Integer> a = Observable.just(1);
		Observable<Integer> b = Observable.just(100);
		Observable.combineLatest(a, b, (x,y) -> x + y)
		.subscribe(System.out::println);
	}
}
