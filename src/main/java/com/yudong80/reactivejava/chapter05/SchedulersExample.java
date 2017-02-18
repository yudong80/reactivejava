package com.yudong80.reactivejava.chapter05;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SchedulersExample {
	public void noScheduler() { 
		Observable.just("ONE", "TWO", "THREE")
		.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void singleScheduler() { 
		Observable.just("ONE", "TWO", "THREE")
		.subscribeOn(Schedulers.single())
		.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void multiSingleScheduler() { 
		Observable<Integer> source1 = Observable.range(0,1000);
		Observable<String> source2 = Observable.range(0,5)
				.map(CommonUtils::numberToAlphabet);		
		
		source1.subscribeOn(Schedulers.single())
				.subscribe(Log::i);
		source2.subscribeOn(Schedulers.single())
				.subscribe(Log::i);		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public void usingInterval() { 
		Observable<Long> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS);
		Observable<String> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);				
		Disposable sub1 = source1.subscribe(Log::i);
		Disposable sub2 = source2.subscribe(Log::i);
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
				.subscribe(Log::i);
		CommonUtils.sleep(500);
		sub.dispose();
		CommonUtils.exampleComplete();
	}
	
	public void usingNewThreadScheduler() { 
		Observable<String> source = Observable.just("ONE", "TWO", "THREE");
		
		Disposable sub1 = source.subscribeOn(Schedulers.newThread())
				.subscribe(Log::i);
		Disposable sub2 = source.subscribeOn(Schedulers.newThread())
				.subscribe(Log::i);
		CommonUtils.sleep(500);		
		sub1.dispose();
		sub2.dispose();
		CommonUtils.exampleComplete();
	}
	
	public void usingTrampolineScheduler() { 
		Observable<String> source = Observable.just("ONE", "TWO", "THREE");
		
		Disposable sub1 = source.subscribeOn(Schedulers.trampoline())
				.subscribe(Log::i);
		Disposable sub2 = source.subscribeOn(Schedulers.trampoline())
				.subscribe(Log::i);
		CommonUtils.sleep(500);		
		sub1.dispose();
		sub2.dispose();		
		CommonUtils.exampleComplete();
	}
	
	public void usingExecutorScheduler() { 
		CommonUtils.exampleStart();
		Observable<String> source = Observable.just("ONE", "TWO", "THREE");
		final int THREAD_NUM = 10;
		Executor executor = Executors.newFixedThreadPool(THREAD_NUM);
		
		Disposable sub1 = source.subscribeOn(Schedulers.from(executor))
				.subscribe(Log::i);
		Disposable sub2 = source.subscribeOn(Schedulers.from(executor))
				.subscribe(Log::i);
		CommonUtils.sleep(500);		
		sub1.dispose();
		sub2.dispose();				
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SchedulersExample demo = new SchedulersExample();
//		demo.noScheduler();
//		demo.singleScheduler();
//		demo.multiSingleScheduler();
//		demo.usingInterval();
//		demo.usingIOScheduler();
//		demo.usingNewThreadScheduler();
//		demo.usingTrampolineScheduler();
		demo.usingExecutorScheduler();
	}
}
