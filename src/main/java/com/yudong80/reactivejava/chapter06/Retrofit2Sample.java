package com.yudong80.reactivejava.chapter06;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** This will be re-wrote soon.  
 *  from https://zeroturnaround.com/rebellabs/getting-started-with-retrofit-2/ */
public class Retrofit2Sample {
	public static void main(String[] args){
		new Retrofit2Sample().run();
	}
	
	public void run() {
		GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
		Call<List<Contributor>> call = gitHubService.repoContributors("yudong80", "reactivejava");
		
		call.enqueue(new Callback<List<Contributor>>() {
			@Override
			public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
				System.out.println("result : " + response.body().toString());	
			}
			
			@Override
			public void onFailure(Call<List<Contributor>> call, Throwable t) {
				System.out.println("failure : " + t.toString());
			}
		});
	}
}
