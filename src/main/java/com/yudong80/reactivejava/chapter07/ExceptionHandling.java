package com.yudong80.reactivejava.chapter07;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;

public class ExceptionHandling {
	public void cannotCatch() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext(RED);
				emitter.onError(new Exception("Some Error"));
				emitter.onNext(BLUE);
				emitter.onComplete();
			});
		
		try { 
			source.subscribe(Log::i);
		} catch (Exception e) { 
			Log.e(e.getMessage());
		}
	}

	public void onErrorReturn() { 
		String[] grades = {"70", "88", "$100", "93", "83"};
		
		Observable<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data))
				.onErrorReturn(e -> { 
					if(e instanceof NumberFormatException) { 
						e.printStackTrace();
					}
					return -1;
				});
		
		source.subscribe(data -> { 
			if (data < 0) { 
				Log.e("Wrong Data found!!");
				return;
			} 
			
			Log.i("Grade is " + data);
		});
		CommonUtils.exampleComplete();
	}
	
	public void onError() { 
		String[] grades = {"70", "88", "$100", "93", "83"};
		
		Observable<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data));		
		
		source.subscribe(
			data -> Log.i("Grade is " + data), 
			e -> { 
				if(e instanceof NumberFormatException) { 
					e.printStackTrace();
				}
				Log.e("Wrong Data found!!");
		});
		CommonUtils.exampleComplete();
	}
	

	public void onErrorReturnItem() { 
		String[] grades = {"70", "88", "$100", "93", "83"};
		
		Observable<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data))
				.onErrorReturnItem(-1);
		
		source.subscribe(data -> { 
			if (data < 0) { 
				Log.e("Wrong Data found!!");
				return;
			} 
			
			Log.i("Grade is " + data);
		});
		CommonUtils.exampleComplete();
	}	
	
	public void onErrorResumeNext() { 
		String[] salesData = {"100", "200", "A300"};
		Observable<Integer> onParseError = Observable.defer(() -> {
			Log.d("send email to administrator");
			return Observable.just(-1);
		}).subscribeOn(Schedulers.io());
		
		Observable<Integer> source = Observable.fromArray(salesData)
			.map(Integer::parseInt)
			.onErrorResumeNext(onParseError);
		
		source.subscribe(data -> { 
			if (data < 0) { 
				Log.e("Wrong Data found!!");
				return;
			}		
			
			Log.i("Sales data : " + data);
		});
		
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) { 
		ExceptionHandling demo = new ExceptionHandling();
		demo.cannotCatch();
//		demo.onErrorReturn();
//		demo.onError();
//		demo.onErrorReturnItem();
//		demo.onErrorResumeNext();
	}
	
}
