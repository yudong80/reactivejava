package com.yudong80.reactivejava.chapter07.flowcontrol;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class SampleExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "ORANGE", "YELLOW", "GREEN", "PUPPLE"};
		
		CommonUtils.exampleStart();
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
			.map(Long::intValue)
			.map(i -> data[i])
			.doOnNext(Log::onNextT)
			.take(data.length - 1) //Green까지만 
			.concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS)
								  .map(i -> data[data.length -1])
								  .doOnNext(Log::onNextT)
								  .doOnComplete(Log::onCompleteT)) //Pupple발행  
			.sample(300L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public void emitLast() {
		String[] data = {"RED", "ORANGE", "YELLOW", "GREEN", "PUPPLE"};
		
		CommonUtils.exampleStart();
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
			.map(Long::intValue)
			.map(i -> data[i])
			.doOnNext(Log::onNextT)
			.take(data.length - 1) //Green까지만 
			.concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS)
								  .map(i -> data[data.length -1])
								  .doOnNext(Log::onNextT)
								  .doOnComplete(Log::onCompleteT)) //Pupple발행  
			.sample(300L, TimeUnit.MILLISECONDS, true);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SampleExample demo = new SampleExample();
		demo.marbleDiagram();
		demo.emitLast();
	}
}
