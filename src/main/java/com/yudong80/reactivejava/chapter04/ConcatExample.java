package com.yudong80.reactivejava.chapter04;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;

public class ConcatExample {
	public void basic() { 
		String[] balls1 = {"RED", "GREEN", "BLUE"};
		String[] balls2 = {"YELLOW", "SKY", "PUPPLE"};
		Observable<String> source1 = Observable.range(0, 3)
				.map(idx -> balls1[idx]);
		Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls2[idx])
				.take(balls2.length);
		Observable<String> source = Observable.concat(source1, source2);
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ConcatExample demo = new ConcatExample();
		demo.basic();
	}
}
