package com.yudong80.reactivejava.chapter04.create;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;

public class RepeatExample implements MarbleDiagram {
	public void marbleDiagram() { 
		String[] balls = {RED, GREEN, BLUE};
		Observable<String> source = Observable.fromArray(balls)
				.repeat(3);
		
		source.doOnComplete(() -> Log.d("onComplete"))
		.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void heartbeatV1() { 
		CommonUtils.exampleStart();
		String serverUrl = "https://api.github.com/zen";
		
		Observable.timer(2, TimeUnit.SECONDS) 		//2초 간격으로 서버에 ping 날리기
			.map(val -> serverUrl)
			.map(OkHttpHelper::get)
			.repeat()
			.subscribe(res -> Log.it("Ping Result : " + res));	
		CommonUtils.sleep(10000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		RepeatExample demo = new RepeatExample();
		demo.marbleDiagram();
		demo.heartbeatV1();
	}
}
