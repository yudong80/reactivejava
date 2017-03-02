package com.yudong80.reactivejava.chapter07.flowcontrol;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class BufferExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "YELLOW", "GREEN", "SKY", "BLUE", "PUPPLE"};
		
		CommonUtils.exampleStart();
		Observable<List<String>> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
			.map(Long::intValue)
			.map(i -> data[i])
			.doOnNext(Log::onNextT)
			.take(3) //Green까지만 
			.concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS)
								  .map(i -> data[3])
								  .doOnNext(Log::onNextT)) //SKY 만 
			.concatWith(Observable.interval(100L, TimeUnit.MILLISECONDS)
								  .map(Long::intValue)
								  .map(i -> data[i+4])
								  .take(2)
								  .doOnNext(Log::onNextT)
								  .doOnComplete(Log::onCompleteT)) //Blue ~ Pupple발행  
			.buffer(3);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public void bufferSkip() { 
		String[] data = {"RED", "YELLOW", "GREEN", "SKY", "BLUE", "PUPPLE"};
		
		CommonUtils.exampleStart();
		Observable<List<String>> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
			.map(Long::intValue)
			.map(i -> data[i])
			.doOnNext(Log::onNextT)
			.take(3) //Green까지만 
			.concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS)
								  .map(i -> data[3])
								  .doOnNext(Log::onNextT)) //SKY 만 
			.concatWith(Observable.interval(100L, TimeUnit.MILLISECONDS)
								  .map(Long::intValue)
								  .map(i -> data[i+4])
								  .take(2)
								  .doOnNext(Log::onNextT)
								  .doOnComplete(Log::onCompleteT)) //Blue ~ Pupple발행  
			.buffer(2, 3);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		BufferExample demo = new BufferExample();
		demo.marbleDiagram();
		demo.bufferSkip();
	}
}

