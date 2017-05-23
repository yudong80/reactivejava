package com.yudong80.reactivejava.chapter05.schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ExecutorSchedulerExample {
	public void run() { 
		final int THREAD_NUM = 10;
		
		String[] data = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(data);
		Executor executor = Executors.newFixedThreadPool(THREAD_NUM);
		
		source.subscribeOn(Schedulers.from(executor))
				.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
				.subscribe(Log::i);
		CommonUtils.sleep(500);		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ExecutorSchedulerExample demo = new ExecutorSchedulerExample();
		demo.run();
	}
}
