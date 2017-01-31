package com.yudong80.reactivejava.chapter07;

import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;

public class ExceptionsBasic {
	public void notThisWay() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("100");
				emitter.onError(new Exception("Some Error"));
			});
		
		try { 
			source.subscribe(new Consumer<String>() {
				@Override
				public void accept(String val) throws Exception {
					System.out.println(val);
				} 
			});
		} catch (Exception e) { 
			Log.e(e.getMessage());
		}
	}
	
	public void onErrorReturn() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("100");
				emitter.onError(new Exception("Some Error"));
			});
		
		source.onErrorReturn(e -> "THREE") //same as onErrorReturnItem("THREE")
			.subscribe(System.out::println);
	}
	
	public static void main(String[] args) { 
		ExceptionsBasic demo = new ExceptionsBasic();
		demo.notThisWay();
	}
}
