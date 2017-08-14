package com.yudong80.reactivejava.chapter07.flowcontrol;

import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.ORANGE;
import static com.yudong80.reactivejava.common.Shape.PUPPLE;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class SampleExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, ORANGE, YELLOW, GREEN, PUPPLE};
		
		CommonUtils.exampleStart();
		//앞의 4개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(4)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 데이터는 300ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//두개의 옵저버블을 결합하고 300ms으로 샘플링 
		Observable<String> source = Observable.concat(
				earlySource, 
				lateSource)
				.sample(300L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public void emitLast() {
		String[] data = {RED, ORANGE, YELLOW, GREEN, PUPPLE};
		
		CommonUtils.exampleStart();
		//앞의 4개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(4)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 데이터는 300ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//두개의 옵저버블을 결합하고 300ms으로 샘플링 (emitLast = true)
		Observable<String> source = Observable.concat(
				earlySource, 
				lateSource)
				.sample(300L, TimeUnit.MILLISECONDS, true);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SampleExample demo = new SampleExample();
		demo.marbleDiagram();
//		demo.emitLast();
	}
}
