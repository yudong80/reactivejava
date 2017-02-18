package com.yudong80.reactivejava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class CombineLatestExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		Observable<String> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);
		Observable<Long> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS);
		BiFunction<String, Long, String> combiner = 
				(val1, val2) -> val1 + val2;
		
		Observable<String> source = Observable.combineLatest(source1, source2, combiner);
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) {
		CombineLatestExample demo = new CombineLatestExample();
		demo.marbleDiagram();
	}
}
