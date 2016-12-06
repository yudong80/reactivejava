package com.yudong80.reactivejava.chapter06;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
	@GET("repos/{owner}/{repo}/contributors")
	Call<List<Contributor>> repoContributors(
			@Path("owner") String owner,
			@Path("repo") String repo);
	
	public static final Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://api.github.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.build();
}
