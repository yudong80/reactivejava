package com.yudong80.reactivejava.chapter04.transform;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class ScanExample implements MarbleDiagram {
	public void marbleDiagram() { 
		String[] balls = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(balls)
				.scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ScanExample demo = new ScanExample();
		demo.marbleDiagram();
	}
}
