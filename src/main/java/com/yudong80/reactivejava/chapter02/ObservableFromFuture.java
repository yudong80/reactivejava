package com.yudong80.reactivejava.chapter02;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;

public class ObservableFromFuture {
	public void basic() { 
		Future<String> future = Executors.newSingleThreadExecutor()
				.submit(() -> {
					Thread.sleep(1000);
					return "Hello Future";
				});
		Observable<String> source = Observable.fromFuture(future);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void withoutLambda() { 
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "Hello Future(No Lambda)";
			}			
		};
		
		Future<String> future = Executors.newSingleThreadExecutor()
				.submit(callable);
		Observable<String> source = Observable.fromFuture(future);
		source.subscribe(System.out::println);		
	}
	
	public static void main(String[] args) { 
		ObservableFromFuture demo = new ObservableFromFuture();
		demo.basic();
		demo.withoutLambda();
	}
}
