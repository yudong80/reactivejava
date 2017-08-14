package com.yudong80.reactivejava.chapter07.flowcontrol;

import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class DebounceExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, BLUE};
		
		Observable<String> source = Observable.concat(
			Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[0]),
			Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[1]),
			Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[2]),
			Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[3]))
			.debounce(200L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		DebounceExample demo = new DebounceExample();
		demo.marbleDiagram();
	}
}
