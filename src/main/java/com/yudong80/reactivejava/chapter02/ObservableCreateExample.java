package com.yudong80.reactivejava.chapter02;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;

public class ObservableCreateExample {
	public void basic() { 
		Observable<Integer> source = 
		    Observable.create((ObservableEmitter<Integer> emitter) -> { 
		    	emitter.onNext(100);
		    	emitter.onNext(200);
		    	emitter.onNext(300);
		    	emitter.onComplete();
		    }); 
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void notSubscribed() { 
		Observable<Integer> source = 
		    Observable.create((ObservableEmitter<Integer> emitter) -> { 
		    	emitter.onNext(100);
		    	emitter.onNext(200);
		    	emitter.onNext(300);
		    	emitter.onComplete();
		    }); 
		CommonUtils.exampleComplete();		
	}
	
	public void subscribeLambda() { 
		Observable<Integer> source = 
		    Observable.create((ObservableEmitter<Integer> emitter) -> { 
		    	emitter.onNext(100);
		    	emitter.onNext(200);
		    	emitter.onNext(300);
		    	emitter.onComplete();
		    });
		source.subscribe(data -> System.out.println("Result : " + data));
		CommonUtils.exampleComplete();
	}
	
	public void subscribeAnonymously() { 
		Observable<Integer> source = 
		    Observable.create((ObservableEmitter<Integer> emitter) -> { 
		    	emitter.onNext(100);
		    	emitter.onNext(200);
		    	emitter.onNext(300);
		    	emitter.onComplete();
		    });
		source.subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer data) throws Exception {
				System.out.println("Result : " + data);
			}				
		});
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args){ 
		ObservableCreateExample demo = new ObservableCreateExample();
		demo.basic();
		demo.notSubscribed();
		demo.subscribeLambda();
		demo.subscribeAnonymously();
	}
}
