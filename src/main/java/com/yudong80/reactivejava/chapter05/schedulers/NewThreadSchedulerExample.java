package com.yudong80.reactivejava.chapter05.schedulers;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewThreadSchedulerExample {
	public void run() { 
		String[] data = {"RED", "GREEN", "BLUE"};
		Observable.fromArray(data)
			.doOnNext(item -> Log.v("Original Value : " + item))
			.map(item -> "<<" + item + ">>")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
		CommonUtils.sleep(500);
		
		Observable.fromArray(data)
			.doOnNext(item -> Log.v("Original Value : " + item))
			.map(item -> "##" + item + "##")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) { 
		NewThreadSchedulerExample demo = new NewThreadSchedulerExample();
		demo.run();
	}
}
