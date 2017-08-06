package com.yudong80.reactivejava.chapter04.conditional;

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
import static com.yudong80.reactivejava.common.Shape.PUPPLE;

public class TakeUntilExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), 
						(val, notUsed) -> val)
				.takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		TakeUntilExample demo = new TakeUntilExample();
		demo.marbleDiagram();
	}
}
