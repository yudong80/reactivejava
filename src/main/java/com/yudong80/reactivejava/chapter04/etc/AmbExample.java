package com.yudong80.reactivejava.chapter04.etc;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;

public class AmbExample {
	public void basic() { 
		String[] balls = {"RED", "GREEN", "BLUE"};
		String[] rects = {"YELLOW-R", "SKY-R"};
		
		Observable<String> source1 = Observable.fromArray(balls);
		Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx-> rects[idx])
				.take(rects.length);
		Observable<String> source = Observable.amb(Arrays.asList(source1, source2));
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		AmbExample demo = new AmbExample();
		demo.basic();
	}
}
