package com.yudong80.reactivejava.chapter05.examples;

import java.io.IOException;

import com.yudong80.reactivejava.common.Log;
import static com.yudong80.reactivejava.common.CommonUtils.GITHUB_ROOT;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CallbackHell {
	private static final String FIRST_URL = "https://api.github.com/zen";
	private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_hell";

	private final OkHttpClient client = new OkHttpClient();
	
	private Callback onSuccess = new Callback() {
		@Override
		public void onFailure(Call call, IOException e) {
			e.printStackTrace();
		}

		@Override
		public void onResponse(Call call, Response response) throws IOException {
			Log.i(response.body().string());
		} 
	};
	
	public void run() { 
		Request request = new Request.Builder()
		        .url(FIRST_URL)
		        .build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i(response.body().string());
				
				//add callback again
				Request request = new Request.Builder()
				        .url(SECOND_URL)
				        .build();
				client.newCall(request).enqueue(onSuccess);				
			}			
		});		
	}
		
	public static void main(String[] args) { 
		CallbackHell demo = new CallbackHell();
		demo.run();
	}
}
