package com.yudong80.reactivejava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.PUPPLE;

public class MergeExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data1 = {RED, GREEN}; //1, 3
		String[] data2 = {YELLOW, SKY, PUPPLE}; //2, 4, 6
		
		Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data1[idx])
				.take(data1.length);
		Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data2[idx])
				.take(data2.length);

		Observable<String> source = Observable.merge(source1, source2);
		source.subscribe(Log::i);		
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		MergeExample demo = new MergeExample();
		demo.marbleDiagram();
	}
}
