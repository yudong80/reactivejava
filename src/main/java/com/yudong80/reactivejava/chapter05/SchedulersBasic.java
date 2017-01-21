package com.yudong80.reactivejava.chapter05;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SchedulersBasic {
	public void noScheduler() { 
		Observable.just("ONE", "TWO", "THREE")
		.subscribe(CommonUtils::logWithThread);
		CommonUtils.exampleComplete();
	}
	
	public void singleScheduler() { 
		Observable.just("ONE", "TWO", "THREE")
		.subscribeOn(Schedulers.single())
		.subscribe(CommonUtils::logWithThread);
		CommonUtils.exampleComplete();
	}

	public void multiSingleScheduler() { 
		Observable<Integer> source1 = Observable.range(0,1000);
		Observable<String> source2 = Observable.range(0,5)
				.map(CommonUtils::numberToAlphabet);		
		
		source1.subscribeOn(Schedulers.single())
				.subscribe(CommonUtils::logWithThread);
		source2.subscribeOn(Schedulers.single())
				.subscribe(CommonUtils::logWithThread);		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public void intervalWithComputationScheduler() { 
		Observable<Long> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(i -> i * 100);
		Observable<String> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);				
		source1.subscribe(CommonUtils::logWithThread);
		source2.subscribe(CommonUtils::logWithThread);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SchedulersBasic demo = new SchedulersBasic();
//		demo.noScheduler();
//		demo.singleScheduler();
		demo.multiSingleScheduler();
	}
}
