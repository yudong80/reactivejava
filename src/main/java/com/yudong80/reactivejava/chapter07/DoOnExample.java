package com.yudong80.reactivejava.chapter07;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DoOnExample {
	public void basic() { 
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		source.doOnNext(val -> Log.d("onNext()", val))
			.doOnComplete(() -> Log.d("onComplete()"))
			.doOnError(e -> Log.e("onError()", e.getMessage()))
			.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void withError() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("ONE");
				emitter.onNext("TWO");
				emitter.onError(new Exception("Some Error"));
			});
		
		source.doOnNext(val -> Log.d("onNext()", val))
		.doOnComplete(() -> Log.d("onComplete()"))
		.doOnError(e -> Log.e("onError()", e.getMessage()))
		.subscribe(System.out::println);			
		CommonUtils.exampleComplete();
	}
	
	public void doOnEach() { 
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		source.doOnEach(noti -> {
			if (noti.isOnNext()) Log.d("onNext()", noti.getValue());
			if (noti.isOnComplete()) Log.d("onComplete()");
			if (noti.isOnError()) Log.e("onError()", noti.getError().getMessage());			
			})
			.subscribe(System.out::println);
		CommonUtils.exampleComplete();		
	}
	
	public void doOnEachObserver() { 
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		source.doOnEach(new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				Log.d("onSubscribe() is not working here!");
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
			.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void doOnSubscribeAndDispose() { 
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		Disposable disposable = source.doOnSubscribe(d -> Log.d("onSubscribe()"))
		.doOnDispose(() -> Log.d("onDispose()"))
		.subscribe(System.out::println);
		
		CommonUtils.sleep(100);		
		disposable.dispose();
		CommonUtils.exampleComplete();
	}
	
	public void doOnLifecycle() { 
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);

		Disposable disposable = source.doOnLifecycle(
				d -> Log.d("onSubscribe()"), 
				() -> Log.d("onDispose()"))
		.subscribe(System.out::println);
		
		CommonUtils.sleep(100);
		disposable.dispose();		
		CommonUtils.exampleComplete();
	}
	
	public void doOnTerminate() { 
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		source.doOnTerminate(() -> Log.d("onTerminate()"))
		.doOnComplete(() -> Log.d("onComplete()"))
		.doOnError(e -> Log.e("onError()", e.getMessage()))
		.subscribe(System.out::println);
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
