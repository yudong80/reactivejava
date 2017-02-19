package com.yudong80.reactivejava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.functions.Action;

public class ConcatExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		Action onCompleteAction = () -> Log.d("onComplete()");
		
		String[] data1 = {"RED", "GREEN", "BLUE"};
		String[] data2 = {"YELLOW", "SKY", "PUPPLE"};
		Observable<String> source1 = Observable.fromArray(data1)
				.doOnComplete(onCompleteAction);
		Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data2[idx])
				.take(data2.length)
				.doOnComplete(onCompleteAction);
		
		Observable<String> source = Observable.concat(source1, source2)
				.doOnComplete(onCompleteAction);
		source.subscribe(Log::i);		
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ConcatExample demo = new ConcatExample();
		demo.marbleDiagram();
	}
}
