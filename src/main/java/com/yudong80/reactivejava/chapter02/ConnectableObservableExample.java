package com.yudong80.reactivejava.chapter02;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservableExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data = {"RED", "GREEN", "BLUE"}; 
		Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(i -> data[i])
				.take(data.length);
		ConnectableObservable<String> source = balls.publish();
		source.subscribe(v -> Log.i("구독자 #1", v)); 
		source.subscribe(v -> Log.i("구독자 #2", v)); 
		source.connect();
		
		CommonUtils.sleep(250);
		source.subscribe(v -> Log.i("구독자 #3", v)); 
		CommonUtils.sleep(100);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ConnectableObservableExample demo = new ConnectableObservableExample();
		demo.marbleDiagram();
	}
}
