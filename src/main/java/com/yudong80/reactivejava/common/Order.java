package com.yudong80.reactivejava.common;

public class Order { 
	private String mId;
	
	public Order(String id) { 
		mId = id;
	}
	
	public String getId() { 
		return mId;
	}		
	
	@Override
	public String toString() { 
		return "Order ID: " + mId;
	}
}

