package com.yudong80.reactivejava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;

public class ZipExample {
	public void simpleZip(){ 
		Observable<Integer> source = Observable.zip(
			Observable.just(100, 200, 300), 
			Observable.just(1, 2, 3),
			(a , b) -> a +b );
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void intervalZip() { 
		Observable<String> source = Observable.zip(
				Observable.just("RED", "GREEN", "BLUE"),
				Observable.interval(100L, TimeUnit.MILLISECONDS),
				(value,i) -> value);		
		
		long start = System.currentTimeMillis();
		source.subscribe(str -> { 
			long now = System.currentTimeMillis();
			System.out.println((now - start) + " | " + str);
		});		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();		
	}	
	
	public static void main(String[] args) { 
		ZipExample demo = new ZipExample();
		demo.simpleZip();
		demo.intervalZip();
	}
}
