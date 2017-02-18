package com.yudong80.reactivejava.chapter04.create;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class IntervalExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(val -> val + 100)
				.take(5);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public void noInitialDelay() { 
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
				.map(val -> val + 100)
				.take(5);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		IntervalExample demo = new IntervalExample();
		demo.marbleDiagram();
		demo.noInitialDelay();
	}
}
