package com.lautung.rxbus.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.lautung.rxbus.R;
import com.lautung.rxbus.app.BaseActivity;
import com.lautung.rxbus.domain.Fragment1Event;
import com.lautung.rxbus.domain.Fragment2Event;
import com.lautung.rxbus.fragment.Fragment1;
import com.lautung.rxbus.fragment.Fragment2;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class TestEventBusActivity extends BaseActivity {
    private static final String TAG = "TestEventBusActivity";

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    // Disposable容器，onDestroy中取消订阅
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);

        initViews();
        registerEvents();
    }

    private void initViews() {

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.layout1, fragment1, fragment1.getClass().getName());
        mTransactiont.replace(R.id.layout2, fragment2, fragment2.getClass().getName());
        mTransactiont.commit();
    }

    private void registerEvents() {

        //rxbus注册


        //FlowableProcessor 订阅
        compositeDisposable.add(rxBus.toFlowable(Fragment1Event.class)
                .subscribe(new Consumer<Fragment1Event>() {
                    @Override
                    public void accept(Fragment1Event crossActivityEvent) throws Exception {
                        Log.i(TAG, "我是来自fragment2的event");
                    }
                }));

        compositeDisposable.add(rxBus.toFlowable(Fragment2Event.class)
                .subscribe(new Consumer<Fragment2Event>() {
                    @Override
                    public void accept(Fragment2Event crossActivityEvent) throws Exception {
                        Log.i(TAG, "我是来自fragment1的event");
                    }
                }));




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
