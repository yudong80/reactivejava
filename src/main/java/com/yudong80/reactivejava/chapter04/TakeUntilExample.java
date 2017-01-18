package com.yudong80.reactivejava.chapter04;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;

public class TakeUntilExample {
	public void basic() { 
		String[] balls = {"RED", "YELLOW", "GREEN", "SKY", "BLUE", "PUPPLE"};
		Observable<String> source = Observable.interval(0L, 50L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx])
				.take(balls.length);
		Observable<Long> other = Observable.timer(200L, TimeUnit.MILLISECONDS);
		source = source.takeUntil(other);
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		TakeUntilExample demo = new TakeUntilExample();
		demo.basic();
	}
}
