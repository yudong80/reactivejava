package com.yudong80.reactivejava.chapter05.schedulers;

import java.io.File;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IOSchedulerExample {
	public void basic() { 
		//list up files on C drive root
		String root = "c:\\";
		File[] files = new File(root).listFiles();		
		Observable<String> source = Observable.fromArray(files)
				.filter(f -> !f.isDirectory())
				.map(f -> f.getAbsolutePath())
				.subscribeOn(Schedulers.io());
		
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		IOSchedulerExample demo = new IOSchedulerExample();
		demo.basic();
	}
}
