package com.bahaa.mvvm.ui.fragment.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.ActivitySplashBinding;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;

public class SplashFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivitySplashBinding view = DataBindingUtil.inflate(inflater,R.layout.activity_splash,container,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3000);
        return view.getRoot();
    }
}
