package com.yudong80.reactivejava.chapter07;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ExceptionsBasic {
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
	
	public static void main(String[] args) { 
		ExceptionsBasic demo = new ExceptionsBasic();
		//demo.notThisWay();
		demo.onErrorReturn();
	}
}
