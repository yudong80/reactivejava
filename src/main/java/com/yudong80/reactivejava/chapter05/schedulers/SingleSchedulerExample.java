package com.yudong80.reactivejava.chapter05.schedulers;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SingleSchedulerExample {
	public void run() { 
		Observable<Integer> source1 = Observable.range(100, 5);
		Observable<String> source2 = Observable.range(0, 5)
				.map(CommonUtils::numberToAlphabet);		
		
		source1.subscribeOn(Schedulers.single())
				.subscribe(Log::i);
		source2.subscribeOn(Schedulers.single())
				.subscribe(Log::i);		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SingleSchedulerExample demo = new SingleSchedulerExample();
		demo.run();
	}

}
