package com.desafio.mytmdb.common;

import android.app.Application;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        onInjectApplication();
    }

    protected abstract void onInjectApplication();
}
