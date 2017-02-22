package com.yudong80.reactivejava.chapter04.etc;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;

public class DelayPing {
	private Boolean[] pingResult = { 
		true, 
		true,
		true,
		true,
		false, 
		true,
		true
	};
	private int index = 0;
	
	public void run() {
		CommonUtils.exampleStart();
		Observable.interval(100L, TimeUnit.MILLISECONDS)
//			.doOnNext(i -> Log.dt("i = " + i))
			.map(i -> doPing())
			.map(success -> Pair.of(success, success ? 200 : 0))
			.scan((val1, val2) -> {
				int nextDelay = nextDelay(
						val2.getLeft(),        	//success
						val1.getRight());		//prevDelay	
				return Pair.of(val2.getLeft(), nextDelay);
			})
			.delay(pair -> { 
				CommonUtils.sleep(pair.getRight());
				return Observable.just(pair);
			})
			.subscribe();		
		
		CommonUtils.sleep(2000);
	}
	
	private int nextDelay(boolean success, int prevDelay) { 
		return success ? 
			   prevDelay + 100: 
			   100;		
	}
	
	private boolean doPing() { 
		if (index >= pingResult.length) { 
			index = 0;
		}
		boolean res = pingResult[index++]; 
		Log.it("doPing() : " + res);
		return res;
	}
	
	public static void main(String[] args) { 
		DelayPing demo = new DelayPing();
		demo.run();
	}
}
