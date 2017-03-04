package com.yudong80.reactivejava.chapter07.flowcontrol;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class WindowExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "YELLOW", "GREEN", "SKY", "BLUE", "PUPPLE"};
		
		CommonUtils.exampleStart();
		Observable<Observable<String>> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(i -> data[i])
				.take(3) 
				.doOnNext(Log::onNextT)//Red ~ Green 
				.concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS)
						              .map(i -> data[3])
						              .doOnNext(Log::onNextT)) //Sky 발행 
				.concatWith(Observable.interval(100L, TimeUnit.MILLISECONDS)
						.map(Long::intValue)
						.map(i -> data[4 + i])
						.take(2)
						.doOnNext(Log::onNextT)
						.doOnComplete(Log::onCompleteT)) //나머지 2개 발행 
				.window(3);
		
		source.subscribe(observable -> {
			Log.dt("New Observable Started!!");
			observable.subscribe(Log::it);
		});
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		WindowExample demo = new WindowExample();
		demo.marbleDiagram();
	}
}
