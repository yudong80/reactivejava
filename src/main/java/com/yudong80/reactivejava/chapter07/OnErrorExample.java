package com.yudong80.reactivejava.chapter07;

import java.util.concurrent.Callable;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;
import com.yudong80.reactivejava.common.Shape;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;

import static com.yudong80.reactivejava.common.CommonUtils.GITHUB_ROOT;

public class OnErrorExample {
	public void cannotCatch() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("RED");
				emitter.onError(new Exception("Some Error"));
				emitter.onNext("BLUE");
				emitter.onComplete();
			});
		
		try { 
			source.subscribe(Log::i);
		} catch (Exception e) { 
			Log.e(e.getMessage());
		}
	}

	public void cannotCatchV2() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("RED");
				emitter.onNext("BLUE");
				emitter.onComplete();
			});

		try { 
			source.doOnNext(data -> { 
					if ("BLUE".equals(data)) { 
						throw new Exception("Some Error on doNext()");
					}
				})
				.subscribe(Log::i);
		} catch (Exception e) { 
			Log.e(e.getMessage());
		}
	}
	
	public void canCatchBut() { 
		String[] grades = {"70", "88", "100", "93", "*83"};
		
		Maybe<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data))
				.reduce((a,b) -> (a+b));
		
		source.subscribe(sum -> Log.i("Sum : " + sum), 
				error -> Log.d("error : " + error));		
	}

	public void onErrorReturn() { 
		String[] grades = {"70", "88", "100", "93", "*83"};
		
		Maybe<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data))
				.onErrorReturn(error -> 0)
				.reduce((a,b) -> (a+b));
		
		source.subscribe(sum -> Log.i("Sum : " + sum));
		CommonUtils.exampleComplete();
	}

	public void onErrorReturnItem() { 
		String[] grades = {"70", "88", "100", "93", "*83"};
		
		Maybe<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data))
				.onErrorReturnItem(0)
				.reduce((a,b) -> (a+b));
		
		source.subscribe(sum -> Log.i("Sum : " + sum));
		CommonUtils.exampleComplete();
	}	
	
	public void onErrorResumeNext() { 
		final String URL_RESERVE_ROOM = GITHUB_ROOT + "README.md";
		
		Observable.just(URL_RESERVE_ROOM)
		.map(OkHttpHelper::get)		
		.subscribe(Log::i);
	
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
//		demo.cannotCatch();
//		demo.cannotCatchV2();
//		demo.canCatchBut();
//		demo.onErrorReturn();
//		demo.onErrorReturnItem();
		demo.onErrorResumeNext();
//		demo.onErrorResumeNextThrowable();
	}
}
