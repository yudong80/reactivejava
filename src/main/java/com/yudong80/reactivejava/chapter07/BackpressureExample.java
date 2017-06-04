package com.yudong80.reactivejava.chapter07;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class BackpressureExample {
	public void makeBackpressure() {
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함 
		
		PublishSubject<Integer> subject = PublishSubject.create();
		subject
		    .observeOn(Schedulers.computation())
			.subscribe(data -> {
				//100ms후 데이터를 처리함 
				CommonUtils.sleep(100);
				Log.it(data);
			}, err -> Log.e(err.toString()));
		
		//뜨거운 Observable로 50,000,000개의 데이터를 연속적으로 발행함 
		for (int i=0; i< 50_000_000; ++i) { 
			subject.onNext(i);
		}		
		subject.onComplete();
		CommonUtils.exampleComplete();
	}

	public void usingBuffer() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함 
		
		Flowable.range(1, 50_000_000)
		.onBackpressureBuffer(128, () -> {}, BackpressureOverflowStrategy.DROP_OLDEST)  
		.observeOn(Schedulers.computation())
		.subscribe(data -> { 
			//100ms후 데이터를 처리함 
			CommonUtils.sleep(100);
			Log.it(data);			
		}, err -> Log.e(err.toString()));
		CommonUtils.exampleComplete();
	}
	
	public void usingDrop() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함 
		
		Flowable.range(1, 50_000_000)
		.onBackpressureDrop()  
		.observeOn(Schedulers.computation())
		.subscribe(data -> { 
			//100ms후 데이터를 처리함 
			CommonUtils.sleep(100);
			Log.it(data);			
		}, err -> Log.e(err.toString()));
		
		CommonUtils.sleep(20_000);
		CommonUtils.exampleComplete();
	}
	
	public void usingLatest() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함 
		
		Flowable.range(1, 50_000_000)
		.onBackpressureLatest()  
		.observeOn(Schedulers.computation())
		.subscribe(data -> { 
			//100ms후 데이터를 처리함 
			CommonUtils.sleep(100);
			Log.it(data);			
		}, err -> Log.e(err.toString()));
		
		CommonUtils.sleep(20_000);
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		BackpressureExample demo = new BackpressureExample();
//		demo.makeBackpressure();
//		demo.usingBuffer();
//		demo.usingDrop();
		demo.usingLatest();
	}
}
