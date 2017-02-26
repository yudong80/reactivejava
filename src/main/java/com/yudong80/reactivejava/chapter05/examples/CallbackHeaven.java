package com.yudong80.reactivejava.chapter05.examples;

import static com.yudong80.reactivejava.common.CommonUtils.GITHUB_ROOT;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CallbackHeaven {
	private static final String URL_CALL = "https://api.github.com/zen";
	private static final String URL_ON_SUCCESS = GITHUB_ROOT + "/samples/callback_heaven";
	
	public void run() { 
		CommonUtils.exampleStart();
		Observable<String> source = Observable.just(URL_CALL)
			.subscribeOn(Schedulers.io())
			.map(OkHttpHelper::get)
			.concatWith(Observable.just(URL_ON_SUCCESS)
					           .map(OkHttpHelper::get));
		source.subscribe(Log::it);
		CommonUtils.sleep(5000);
	}
	
	public static void main(String[] args) { 
		CallbackHeaven demo = new CallbackHeaven();
		demo.run();
	}
}
