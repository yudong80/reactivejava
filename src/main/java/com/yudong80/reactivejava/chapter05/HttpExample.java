package com.yudong80.reactivejava.chapter05;

import java.io.IOException;

import com.yudong80.reactivejava.common.CommonUtils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpExample {
	public void httpGet() { 
		String url = "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";
		CommonUtils.logWithThread(blockingGet(url));
	}
	
	private String blockingGet(String url) { 
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(url)
				.build();		
		try {
			Response res = client.newCall(request).execute();
			return res.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static void main(String[] args) { 
		HttpExample demo = new HttpExample();
		demo.httpGet();
	}
}
