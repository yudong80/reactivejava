package com.yudong80.reactivejava.chapter04.etc;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * @deprecated 실패, 다시 도전 필요 
 */
public class DelayPing {
	public void run() { 
		CommonUtils.exampleStart();
		Boolean[] pingResult = {true, true, true, false, false, true, true};
		final int PING_INTERVAL = 1000;
		
		//ping 간격 1초 , 성공하면 1초 늘어남, 실패하면 다시 1초부터 시도 , 3회 실패시 중단 
		Observable<Boolean> source1 = Observable.fromArray(pingResult);		
		Observable<Integer> itemDelay = Observable.fromArray(pingResult)
				.map(val -> val ? 1 : 0)
				.scan((prev, now) -> now != 0 ? prev + 1000 * now : 0);
		
		Observable<Boolean> source = itemDelay
				.delay(millis -> { 
					CommonUtils.sleep(PING_INTERVAL + millis);
					return Observable.just(millis);
				})
				//컴파일 에러가 날때는 이렇게 직접 익명 객체를 넣어주자 
				.zipWith(source1, new BiFunction<Integer, Boolean, Boolean>() {
					@Override
					public Boolean apply(Integer t1, Boolean t2) throws Exception {
						return t2;
					}					
				});
		
		source.subscribe(ping -> { 
			Log.it(ping ? "PING SUCCESS" : "PING FAILURE");
		});
	}
	
	public static void main(String[] args) { 
		DelayPing demo = new DelayPing();
		demo.run();
	}
}
