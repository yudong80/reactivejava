package com.yudong80.reactivejava.chapter05;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
	
	public void usingInterval() { 
		Observable<Long> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS);
		Observable<String> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);				
		Disposable sub1 = source1.subscribe(CommonUtils::logWithThread);
		Disposable sub2 = source2.subscribe(CommonUtils::logWithThread);
		CommonUtils.sleep(500);
		sub1.dispose();
		sub2.dispose();		
		CommonUtils.exampleComplete();
	}
	
	public void usingIOScheduler() { 
		String root = "c:\\";
		Observable<String> source = Observable.fromArray(new File(root).listFiles())
				.map(f -> f.getAbsolutePath());
		
		Disposable sub = source.subscribeOn(Schedulers.io())
				.subscribe(CommonUtils::logWithThread);
		CommonUtils.sleep(500);
		sub.dispose();
		CommonUtils.exampleComplete();
	}
	
	public void usingNewThreadScheduler() { 
		Observable<String> source = Observable.just("ONE", "TWO", "THREE");
		
		Disposable sub1 = source.subscribeOn(Schedulers.newThread())
				.subscribe(CommonUtils::logWithThread);
		Disposable sub2 = source.subscribeOn(Schedulers.newThread())
				.subscribe(CommonUtils::logWithThread);
		CommonUtils.sleep(500);		
		sub1.dispose();
		sub2.dispose();
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SchedulersBasic demo = new SchedulersBasic();
//		demo.noScheduler();
//		demo.singleScheduler();
//		demo.multiSingleScheduler();
//		demo.usingInterval();
//		demo.usingIOScheduler();
		demo.usingNewThreadScheduler();
	}
}
