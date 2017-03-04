package com.yudong80.reactivejava.chapter07.flowcontrol;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class ThrottleFirstExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "YELLOW", "GREEN", "SKY", "BLUE", "PUPPLE"};
		
		CommonUtils.exampleStart();
		Observable<String> source = Observable.timer(100L, TimeUnit.MILLISECONDS)
			.map(i -> data[0])
			.take(1)
			.doOnNext(Log::onNextT) //Red까지만
			.concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS)
					  .map(i -> data[1])
					  .doOnNext(Log::onNextT)) //Yellow 만 
			.concatWith(Observable.interval(100L, TimeUnit.MILLISECONDS)
								  .map(Long::intValue)
								  .map(i -> data[2 + i])
								  .take(4)
								  .doOnNext(Log::onNextT)
								  .doOnComplete(Log::onCompleteT)) //Pupple까지 발행  
			.throttleFirst(200L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		ThrottleFirstExample demo = new ThrottleFirstExample();
		demo.marbleDiagram();
	}
}
