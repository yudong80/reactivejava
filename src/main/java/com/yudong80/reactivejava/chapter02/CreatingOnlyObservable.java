package com.yudong80.reactivejava.chapter02;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

/**
 * @deprecated see ObservableCreateExample
 */
public class CreatingOnlyObservable {
	public void run() { 
		Observable<Integer> source = 
		    Observable.create((ObservableEmitter<Integer> emitter) -> { 
		    	emitter.onNext(100);
		    	emitter.onNext(200);
		    	emitter.onNext(300);
		    	emitter.onComplete();
		    }); 
	}
	
	public static void main(String[] args){ 
		new CreatingOnlyObservable().run();
	}
}
