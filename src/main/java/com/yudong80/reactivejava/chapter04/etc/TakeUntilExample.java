package com.yudong80.reactivejava.chapter04.etc;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class TakeUntilExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "YELLOW", "GREEN", "SKY", "BLUE", "PUPPLE"};
		
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data[idx])
				.take(data.length) //shield 
				.takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		TakeUntilExample demo = new TakeUntilExample();
		demo.marbleDiagram();
	}
}
