package com.yudong80.reactivejava.chapter07;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;

public class OnErrorExample {
	public void notThisWay() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("ONE");
				emitter.onError(new Exception("Some Error"));
			});
		
		try { 
			source.subscribe(System.out::println);
		} catch (Exception e) { 
			Log.e(e.getMessage());
		}
	}
	
	public void onErrorReturn() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("ONE");
				emitter.onNext("TWO");
				emitter.onError(new Exception("Some Error"));
			});
		
		source.onErrorReturn(e -> "THREE") //same as onErrorReturnItem("THREE")
			.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void onErrorReturnItem() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("ONE");
				emitter.onNext("TWO");
				emitter.onError(new Exception("Some Error"));
			});
		
		source.onErrorReturnItem("THREE")
			.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}	
	
	public void onErrorResumeNext() { 
		String[] salesData = {"100", "200", "A300"};
		Observable<Integer> source = Observable.fromArray(salesData)
			.map(Integer::parseInt)
			.onErrorResumeNext(Observable.just(1,2,3));
		
		source.subscribe(Log::i);
	}

	public void onErrorResumeNextThrowable() { 
		String[] salesData = {"100", "200", "A300"};
		Observable<Integer> source = Observable.fromArray(salesData)
			.map(Integer::parseInt)
			.onErrorResumeNext((Throwable t) -> {
				Log.e(t.getMessage());
				return Observable.just(1,2,3);	
			});
		
		source.subscribe(Log::i);
	}
	
	
	public static void main(String[] args) { 
		OnErrorExample demo = new OnErrorExample();
		//demo.notThisWay();
//		demo.onErrorReturn();
//		demo.onErrorReturnItem();
//		demo.onErrorResumeNext();
		demo.onErrorResumeNextThrowable();
	}
}
