package com.yudong80.reactivejava.chapter05;

import java.io.IOException;

import com.yudong80.reactivejava.common.CommonUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpExample {
	private final OkHttpClient client = new OkHttpClient();
	
	private static final String URL_README = 
			"https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";
	private static final String URL_NESTED_CALLBACK = 
			"https://raw.githubusercontent.com/yudong80/reactivejava/master/IF_SUCCESS.txt";
	
	public void singleCallback() { 
		Request request = new Request.Builder()
	        .url(URL_README)
	        .build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				CommonUtils.logWithThread(response.body().string());
			}			
		});
		CommonUtils.exampleComplete();
	}
	
	private Callback nestedCallback = new Callback() {
		@Override
		public void onFailure(Call call, IOException e) {
			e.printStackTrace();
		}

		@Override
		public void onResponse(Call call, Response response) throws IOException {
			CommonUtils.logWithThread(response.body().string());
		} 
	};
	
	public void nestedCallbacks() { 
		Request request = new Request.Builder()
		        .url(URL_README)
		        .build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				CommonUtils.logWithThread(response.body().string());
				
				//add callback again
				Request request = new Request.Builder()
				        .url(URL_NESTED_CALLBACK)
				        .build();
				client.newCall(request).enqueue(nestedCallback);				
			}			
		});		
	}
		
	public static void main(String[] args) { 
		HttpExample demo = new HttpExample();
		demo.singleCallback();
		demo.nestedCallbacks();
	}
}
