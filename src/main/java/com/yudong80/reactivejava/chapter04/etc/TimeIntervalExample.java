package com.yudong80.reactivejava.chapter04.etc;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

public class TimeIntervalExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() {
		String[] data = {"RED", "GREEN", "ORANGE"};
		
		CommonUtils.exampleStart();
		Observable<Timed<String>> source = Observable.fromArray(data)
			.delay(item -> { 
				CommonUtils.doSomething();
				return Observable.just(item);
			})
			.timeInterval();
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) { 
		TimeIntervalExample demo = new TimeIntervalExample();
		demo.marbleDiagram();	
	}
}
