package com.yudong80.reactivejava.chapter04.conditional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.Shape.rectangle;

public class AmbExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data1 = {RED, GREEN, BLUE};
		String[] data2 = {rectangle(YELLOW), rectangle(SKY)};
		
		List<Observable<String>> sources = Arrays.asList(
				Observable.fromArray(data1)
						  .doOnComplete(() -> Log.d("Observable #1 : onComplete()")), 
				Observable.fromArray(data2)
						  .delay(100L, TimeUnit.MILLISECONDS)
						  .doOnComplete(() -> Log.d("Observable #2 : onComplete()")));
		
		Observable.amb(sources)
				  .doOnComplete(() -> Log.d("Result : onComplete()"))
				  .subscribe(Log::i);		
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		AmbExample demo = new AmbExample();
		demo.marbleDiagram();
	}
}
