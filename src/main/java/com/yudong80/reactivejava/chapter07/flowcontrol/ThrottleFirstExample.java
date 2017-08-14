package com.yudong80.reactivejava.chapter07.flowcontrol;

import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.PUPPLE;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.YELLOW;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class ThrottleFirstExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		CommonUtils.exampleStart();
		
		//앞의 1개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.just(data[0])
				.zipWith(Observable.timer(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//다음  1개는 300ms 후에 발행 
		Observable<String> middleSource = Observable.just(data[1])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 4개는 100ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[2], data[3], data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a)
				.doOnNext(Log::dt);
		
		//200ms 간격으로 throttleFirst() 실행함   
		Observable<String> source = Observable.concat(
				earlySource,
				middleSource,
				lateSource)
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
