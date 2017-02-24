package com.yudong80.reactivejava.chapter05.examples;

import static com.yudong80.reactivejava.common.CommonUtils.GITHUB_ROOT;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;

public class CallbackHeaven {
	private static final String URL_CALL = "https://api.github.com/zen";
	private static final String URL_ON_SUCCESS = GITHUB_ROOT + "/samples/callback_heaven";
	
	public void run() { 
		Observable<String> source = Observable.just(URL_CALL)
			.map(OkHttpHelper::get)
			.concatWith(Observable.just(URL_ON_SUCCESS)
					           .map(OkHttpHelper::get));
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) { 
		CallbackHeaven demo = new CallbackHeaven();
		demo.run();
	}
}
