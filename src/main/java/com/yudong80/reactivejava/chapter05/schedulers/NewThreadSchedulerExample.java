package com.yudong80.reactivejava.chapter05.schedulers;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;

public class NewThreadSchedulerExample {
	public void basic() { 
		String[] orgs = {RED, GREEN, BLUE};
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "<<" + data + ">>")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
		CommonUtils.sleep(500);
		
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "##" + data + "##")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) { 
		NewThreadSchedulerExample demo = new NewThreadSchedulerExample();
		demo.basic();
	}
}
