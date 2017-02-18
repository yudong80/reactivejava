package com.yudong80.reactivejava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class CombineLatestExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data1 = {"PUPPLE", "ORANGE", "SKY", "YELLOW"};
		String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};
		
		Observable<String> source = Observable.combineLatest(
				Observable.interval(100L, TimeUnit.MILLISECONDS)
						  .map(Long::intValue)
						  .map(i -> data1[i])
						  .map(Shape::getColor)
						  .take(data1.length),
				Observable.interval(150L, 200L, TimeUnit.MILLISECONDS)
						  .map(Long::intValue)
						  .map(i -> data2[i])
						  .map(Shape::getSuffix)
						  .take(data2.length),
				(v1, v2) -> v1 + v2);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
		
	public void tmp01() {	
		Observable<String> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);
		Observable<Long> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS);
		BiFunction<String, Long, String> combiner = 
				(val1, val2) -> val1 + val2;
		
		Observable<String> source = Observable.combineLatest(source1, source2, combiner);
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) {
		CombineLatestExample demo = new CombineLatestExample();
		demo.marbleDiagram();
	}
}
