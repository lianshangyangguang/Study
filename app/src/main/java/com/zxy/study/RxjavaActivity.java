package com.zxy.study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity extends AppCompatActivity {

    private String TAG = RxjavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

    }



    //对应于new Observer中的四个方法
    private void customer() {
        Observable.just("Hello", "World")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i("JAVA", "被观察者向观察者发送的数据:" + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                    }
                });
    }

    private void changeThread() {
        // 创建被观察者
        Observable.just("Hello", "World")
             // 将被观察者切换到子线程
                .subscribeOn(Schedulers.io())
            // 将观察者切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
            // 创建观察者并订阅
                .subscribe(new Observer<String>() {
                    // Disposable 相当于RxJava1.x中的 Subscription，用于解除订阅
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("JAVA", "被观察者向观察者发送的数据:" + s);
                        if (s == "-1") {   // "-1" 时为异常数据，解除订阅
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public Observable create() {
        // 发送对应的方法
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                // 默认在主线程里执行该方法
                e.onNext(2);
                e.onNext(3);
                //结束标识
                e.onComplete();
            }
        });
        //Observer中输出
        // onNext: 2
        // onNext: 3
        // onComplete

    }

    public Observable fromCallable() {
        // 发送一个数据
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 2;
            }
        });
        //Observer中输出
        // onNext: 2
        // onComplete
    }

    public Observable just() {
        // 发送多个数据
        return Observable.just("Hello", "Rxjava");
        //Observer中输出
        // onNext: Hello
        // onNext: Rxjava
        // onComplete
    }

    public Observable fromArray() {
        // 发送数组
        return Observable.fromArray("Hello", "Rxjava");
        //Observer中输出
        // onNext: Hello
        // onNext: Rxjava
        // onComplete
    }

    private void test1() {
        fromCallable().subscribe(new Observer() {

            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: " + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }
}
