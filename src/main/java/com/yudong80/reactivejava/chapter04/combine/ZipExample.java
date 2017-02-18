package com.yudong80.reactivejava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;

public class ZipExample implements MarbleDiagram{
	@Override
	public void marbleDiagram(){
		String[] data1 = {"BALL", "PENTAGON", "STAR"};
		String[] data2 = {"YELLOW-T", "PUPPLE-T", "SKY-T"};
		
		Observable<String> source = Observable.zip(
			Observable.fromArray(data1)
					.map(Shape::getSuffix), 
			Observable.fromArray(data2)
					.map(Shape::getColor),
			(suffix, color) -> color + suffix); 
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void zipNumbers() {
		Observable<Integer> source = Observable.zip(
			Observable.just(100, 200, 300),
			Observable.just(10, 20, 30),
			Observable.just(1, 2, 3),
			(a, b, c) -> a + b + c );
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void intervalZip() { 
		Observable<String> source = Observable.zip(
				Observable.just("RED", "GREEN", "BLUE"),
				Observable.interval(200L, TimeUnit.MILLISECONDS),
				(value,i) -> value);		
		
		CommonUtils.exampleStart();
		source.subscribe(Log::it);			
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();		
	}	
	
	public static void main(String[] args) { 
		ZipExample demo = new ZipExample();
		demo.marbleDiagram();
		demo.zipNumbers();
		demo.intervalZip();
	}
}
