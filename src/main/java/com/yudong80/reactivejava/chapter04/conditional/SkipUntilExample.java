package com.yudong80.reactivejava.chapter04.conditional;

import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.PUPPLE;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.YELLOW;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class SkipUntilExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), 
						(val, notUsed) -> val)
				.skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SkipUntilExample demo = new SkipUntilExample();
		demo.marbleDiagram();
	}
}
