package com.yudong80.reactivejava.chapter05;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class FlipExampleV2 implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED-S", "YELLOW-T", "GREEN-P"};
		Observable<String> source = Observable.fromArray(data)
				.doOnNext(item -> Log.v("Origianl value = " + item))
				.subscribeOn(Schedulers.newThread())
				.observeOn(Schedulers.newThread())
				.map(Shape::flip);
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}

	public void observeOnRemoved() {
		String[] data = {"RED-S", "YELLOW-T", "GREEN-P"};
		Observable<String> source = Observable.fromArray(data)
				.doOnNext(item -> Log.v("Origianl value = " + item))
				.subscribeOn(Schedulers.newThread())
				//removed .observeOn(Schedulers.newThread())
				.map(Shape::flip);
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) { 
		FlipExampleV2 demo = new FlipExampleV2();
		demo.marbleDiagram();
		demo.observeOnRemoved();
	}
}
