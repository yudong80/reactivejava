package com.yudong80.reactivejava.chapter07;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DoOnExample {
	public void basic() { 
		String[] orgs = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(orgs);
		
		source.doOnNext(data -> Log.d("onNext()", data))
			.doOnComplete(() -> Log.d("onComplete()"))
			.doOnError(e -> Log.e("onError()", e.getMessage()))
			.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void withError() { 
		Integer[] divider = {10, 5, 0};  //0 으로는 나눌 수 없다 
		
		Observable.fromArray(divider)
				.map(div -> 1000 / div)
				.doOnNext(data -> Log.d("onNext()", data))
				.doOnComplete(() -> Log.d("onComplete()"))
				.doOnError(e -> Log.e("onError()", e.getMessage()))
				.subscribe(Log::i);			
		CommonUtils.exampleComplete();
	}
	
	public void doOnEach() { 
		String[] orgs = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(orgs);
		
		source.doOnEach(noti -> {
			if (noti.isOnNext()) Log.d("onNext()", noti.getValue());
			if (noti.isOnComplete()) Log.d("onComplete()");
			if (noti.isOnError()) Log.e("onError()", noti.getError().getMessage());			
			})
			.subscribe(Log::i);
		CommonUtils.exampleComplete();		
	}
	
	public void doOnEachObserver() { 
		String[] orgs = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(orgs);
		
		source.doOnEach(new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				//doOnEach()에서는 onSubscribe()가 호출되지 않습니다. 
			}

			@Override
			public void onNext(String value) {
				Log.d("onNext()", value);
			}

			@Override
			public void onError(Throwable e) {
				Log.e("onError()", e.getMessage());
			}

			@Override
			public void onComplete() {
				Log.d("onComplete()");
			}})
			.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void doOnSubscribeAndDispose() { 
		String[] orgs = {"RED", "GREEN", "BLUE", "YELLOW", "PUPPLE"};
		Observable<String> source = Observable.fromArray(orgs)
			.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), 
					(a,b) -> a)
			.doOnSubscribe(d -> Log.d("onSubscribe()"))
			.doOnDispose(() -> Log.d("onDispose()"));
		Disposable d = source.subscribe(Log::i);
		
		CommonUtils.sleep(200);
		d.dispose();
		CommonUtils.sleep(300);
		CommonUtils.exampleComplete();	
	}
	
	public void doOnLifecycle() { 
		String[] orgs = {"RED", "GREEN", "BLUE", "YELLOW", "PUPPLE"};
		Observable<String> source = Observable.fromArray(orgs)
			.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), 
					(a,b) -> a)
			.doOnLifecycle(
					d -> Log.d("onSubscribe()"), 
					() -> Log.d("onDispose()"));
		Disposable d = source.subscribe(Log::i);
		
		CommonUtils.sleep(200);
		d.dispose();
		CommonUtils.sleep(300);
		CommonUtils.exampleComplete();	
	}
	
	public void doOnTerminate() { 
		String[] orgs = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(orgs);
		
		source.doOnTerminate(() -> Log.d("onTerminate()"))
		.doOnComplete(() -> Log.d("onComplete()"))
		.doOnError(e -> Log.e("onError()", e.getMessage()))
		.subscribe(Log::i);
		CommonUtils.exampleComplete();	
	}
	
	public static void main(String[] args) { 
		DoOnExample demo = new DoOnExample();
//		demo.basic();
//		demo.withError();
//		demo.doOnEach();
//		demo.doOnEachObserver();
//		demo.doOnSubscribeAndDispose();
//		demo.doOnLifecycle();
		demo.doOnTerminate();
	}
}
