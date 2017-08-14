package com.yudong80.reactivejava.chapter07.flowcontrol;

import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.PUPPLE;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.YELLOW;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class BufferExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		CommonUtils.exampleStart();
		
		//앞의 3개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(3)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//가운데 1개는 300ms 후에 발행 
		Observable<String> middleSource = Observable.just(data[3])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 2개는 100ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//3개씩 모아서 한번에 발행함  
		Observable<List<String>> source = Observable.concat(
				earlySource,
				middleSource,
				lateSource)
				.buffer(3);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public void bufferSkip() { 
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		CommonUtils.exampleStart();
		
		//앞의 3개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(3)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//가운데 1개는 300ms 후에 발행 
		Observable<String> middleSource = Observable.just(data[3])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 2개는 100ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//2는 모으고 1개는 skip함   
		Observable<List<String>> source = Observable.concat(
				earlySource,
				middleSource,
				lateSource)
				.buffer(2,3);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		BufferExample demo = new BufferExample();
		demo.marbleDiagram();
//		demo.bufferSkip();
	}
}

