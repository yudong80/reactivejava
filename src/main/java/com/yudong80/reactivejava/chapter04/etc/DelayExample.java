package com.yudong80.reactivejava.chapter04.etc;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class DelayExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		CommonUtils.exampleStart();
		
		String[] data = {"RED", "ORANGE", "YELLOW", "GREEN", "SKY"};
		Observable<String> source = Observable.fromArray(data);
//				.delay(100L, TimeUnit.MILLISECONDS);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		DelayExample demo = new DelayExample();
		demo.marbleDiagram();
	}
}
