package com.bahaa.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

public class MyApplication extends Application {

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static synchronized Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
