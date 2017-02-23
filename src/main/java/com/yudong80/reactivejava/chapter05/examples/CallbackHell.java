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
	private static final String URL_CALL = "https://api.github.com/zen";
	private static final String URL_ON_SUCCESS = GITHUB_ROOT + "/samples/callback_hell";

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
		        .url(URL_CALL)
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
				        .url(URL_ON_SUCCESS)
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
