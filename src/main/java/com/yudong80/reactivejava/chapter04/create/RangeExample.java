package com.yudong80.reactivejava.chapter04.create;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class RangeExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() { 
		Observable<Integer> source = Observable.range(1, 10)
			.filter(number -> number % 2 == 0);	
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) { 
		RangeExample demo = new RangeExample();
		demo.marbleDiagram();
	}
}
