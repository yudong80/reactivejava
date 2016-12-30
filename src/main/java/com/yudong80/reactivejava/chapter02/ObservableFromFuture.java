package com.yudong80.reactivejava.chapter02;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;

public class ObservableFromFuture {
	public void run() { 
		Future<String> future = Executors.newSingleThreadExecutor()
				.submit(getCallable());
		Observable<String> source = Observable.fromFuture(future);
		source.subscribe(System.out::println);
	}
	
	private Callable<String> getCallable() { 
		return () -> {
			Thread.sleep(1000);
			return "Hello Future";
		};		
	}
	
	private Callable<String> getCallableNoLambda() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "Hello Future(No Lambda)";
			}			
		};		
	}	
	
	public static void main(String[] args) { 
		ObservableFromFuture demo = new ObservableFromFuture();
		demo.run();
	}
}
