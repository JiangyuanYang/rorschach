package com.wxun.proxy.website;

import com.wxun.proxy.http.OkHttp;
import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;

import static com.wxun.proxy.website.SiteConstant.*;

/**
 * @author Zhuwx
 * @since 2018-06-14 15:40
 */
@Slf4j
public class Test {
	public static void main(String[] args) {

// 第一步：初始化Observable
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
				e.onNext(1);
				e.onNext(2);
				e.onNext(3);
				System.out.println("33333"+Thread.currentThread().getName());
				e.onComplete();
				e.onNext(4);
			}
		}).map(a->{
			return "String";
		}).subscribeOn(Schedulers.newThread()).subscribe( /*new Consumer<String>() {
			@Override
			public void accept(String integer) throws Exception {
				System.out.println(integer);
				System.out.println(Thread.currentThread().getName());
			}
		}*/);
		System.out.println("完全异步的方式");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Flowable.create(new FlowableOnSubscribe<Object>() {
			@Override
			public void subscribe(FlowableEmitter<Object> e) throws Exception {

			}
		}, BackpressureStrategy.MISSING).subscribe(new Subscriber<Object>() {
			@Override
			public void onSubscribe(Subscription subscription) {

			}

			@Override
			public void onNext(Object o) {

			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onComplete() {

			}
		});
	}
}
