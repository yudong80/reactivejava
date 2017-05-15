package com.yudong80.reactivejava.chapter02;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ObservableLifecycleV1 {
	public void lifecycle() { 
		Observable<String> source = Observable.just("RED", "GREEN", "YELLOW");
		
		Disposable d = source.subscribe(
			v -> System.out.println("onNext() : value : " + v), 
			err -> System.err.println("onError() : err : " + err.getMessage()),
			() -> System.out.println("onComplete()")
		);	
		
		System.out.println("isDisposed() : " + d.isDisposed());
	}
	
	public static void main(String[] args) { 
		ObservableLifecycleV1 demo = new ObservableLifecycleV1();
		demo.lifecycle();
	}

}
