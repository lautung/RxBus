package com.lautung.rxbus.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lautung.rxbus.RxBus;



public class BaseFragment extends Fragment {

    /**
     * Fragment 所在的 FragmentActivity
     */
    public Activity mContext;

    protected RxBus rxBus;

    /**
     * Deprecated on API 23
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            this.mContext = activity;
        }
    }

    @TargetApi(23)
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.mContext = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBus = RxBus.get();
    }
}
