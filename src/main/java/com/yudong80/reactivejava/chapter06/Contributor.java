package com.yudong80.reactivejava.chapter06;

public class Contributor {
	String login;
	String html_url;
	int contributions;
	
	@Override
	public String toString() {
		return login + " (" + contributions + ")";
	}
}
