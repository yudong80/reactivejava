package com.yudong80.reactivejava.chapter05;

import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ObserveOnExample {
	public void basic() { 
		Integer[] data = { 1,2,3,4,5,6,7,8,9,10};
		Observable<Integer> source = Observable.fromArray(data)
			.map(num -> num + 48)
			.doOnNext(Log::d)
			.observeOn(Schedulers.computation())
			.filter(num -> (num %2) == 0);
		
		source.subscribe(Log::i);
	}
	
	
	public static void main(String[] args) { 
		ObserveOnExample demo = new ObserveOnExample();
		demo.basic();
	}
}
