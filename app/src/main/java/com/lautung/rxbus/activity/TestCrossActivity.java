package com.lautung.rxbus.activity;

import android.os.Bundle;

import com.lautung.rxbus.app.BaseActivity;
import com.lautung.rxbus.domain.CrossActivityEvent;


public class TestCrossActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBus.post(new CrossActivityEvent());
    }
}
