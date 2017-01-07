package com.yudong80.reactivejava.chapter02;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.ThreeSubscribers;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservableExample extends ThreeSubscribers{
	public void basic() { 
		String[] balls = {"RED", "GREEN", "BLUE"}; 
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(idx -> Integer.parseInt(Long.toString(idx)))
				.map(idx -> {
					if (idx < balls.length)
						return balls[idx];
					else
						return "..."; //FIXME 
				});
		ConnectableObservable<String> conSource = source.publish();
		Disposable sub1 = conSource.subscribe(firstSubscriber);
		Disposable sub2 = conSource.subscribe(secondSubscriber);
		conSource.connect();
		
		Disposable sub3 = null;
		try { 
			Thread.sleep(250); //실행환경에 따라 조정 필요 
			sub3 = conSource.subscribe(thirdSubscriber);
			Thread.sleep(100);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
		sub1.dispose();
		sub2.dispose();
		sub3.dispose();
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ConnectableObservableExample demo = new ConnectableObservableExample();
		demo.basic();
	}
}
