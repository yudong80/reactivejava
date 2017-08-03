package com.yudong80.reactivejava.chapter07;

import static com.yudong80.reactivejava.common.CommonUtils.GITHUB_ROOT;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ExceptionHandling {
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
		});
		CommonUtils.exampleComplete();
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
		Observable<Integer> source = Observable.fromArray(salesData)
			.map(Integer::parseInt)
			.onErrorResumeNext(Observable.just(-1,-1));
		
		source.subscribe(data -> { 
			if (data < 0) { 
				Log.e("Wrong Data found!!");
				return;
			}		
			
			Log.i("Sales data : " + data);
		});
	}
	
	public static void main(String[] args) { 
		ExceptionHandling demo = new ExceptionHandling();
//		demo.cannotCatch();
//		demo.onError();
//		demo.onErrorReturn();
//		demo.onErrorReturnItem();
//		demo.onErrorResumeNext();
		demo.test();
	}
	
	private void test() { 
		Observable.just(1,0)
		.map(v -> {
			try { 
				return 100 / v;
			} catch (ArithmeticException e) { 
				throw e;
			}
		}).subscribe(v -> System.out.println("value = " + v), 
				e -> e.printStackTrace());		
	}
}
