package com.yudong80.reactivejava.chapter02;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Observable;

public class ObservableFromPublisher {
	public void run() { 
		Publisher<String> publisher = (Subscriber<? super String> s) -> { 
			s.onNext("Hello Observable.fromPublisher()");
			s.onComplete();
		};
		Observable<String> source = Observable.fromPublisher(publisher);
		source.subscribe(System.out::println);		
	}
	
	public void noLambda() { 
		Publisher<String> publisher = new Publisher<String>() {
			@Override
			public void subscribe(Subscriber<? super String> s) {
				s.onNext("Hello Observable.fromPublisher()");
				s.onComplete();				
			}
		};
		Observable<String> source = Observable.fromPublisher(publisher);
		source.subscribe(System.out::println);				
	}
	
	public static void main(String[] args) { 
		ObservableFromPublisher demo = new ObservableFromPublisher();
		demo.run();
		demo.noLambda();
	}
}
