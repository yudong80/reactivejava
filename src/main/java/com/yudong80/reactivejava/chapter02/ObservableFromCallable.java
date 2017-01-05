package com.yudong80.reactivejava.chapter02;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class ObservableFromCallable {
	public void run() { 
		Callable<String> callable = () -> { 
			Thread.sleep(1000);
			return "Hello Callable";
		};
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
	}
	
	public void withoutLambda() { 
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "Hello Callable";
			}			
		};
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
	}
	
	public static void main(String[] args) { 
		ObservableFromCallable demo = new ObservableFromCallable();
		demo.run();		
//		demo.withoutLambda();
	}
}
