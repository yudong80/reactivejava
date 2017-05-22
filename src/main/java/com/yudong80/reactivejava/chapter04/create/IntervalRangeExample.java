package com.yudong80.reactivejava.chapter04.create;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class IntervalRangeExample {
	public void printNumbers() { 
		Observable<Long> source = Observable.intervalRange(1, 
				5, 
				100L ,
				100L, 
				TimeUnit.MILLISECONDS);
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public void makeWithInterval() { 
		Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(val -> val + 1)
				.take(5);
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();		
	}
	
	
	public static void main(String[] args) { 
		IntervalRangeExample demo = new IntervalRangeExample();
		demo.printNumbers();
		demo.makeWithInterval();
	}
}
