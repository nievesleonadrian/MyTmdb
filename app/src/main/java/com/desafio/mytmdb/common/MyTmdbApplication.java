package com.desafio.mytmdb.common;

import androidx.appcompat.app.AppCompatDelegate;

import com.desafio.mytmdb.common.BaseApplication;
import com.desafio.mytmdb.common.di.ApplicationComponent;
import com.desafio.mytmdb.common.di.ApplicationModule;
import com.desafio.mytmdb.common.di.DaggerApplicationComponent;

public class MyTmdbApplication extends BaseApplication {

  private static ApplicationComponent applicationComponent;

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  @Override public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
  }

  @Override protected void onInjectApplication() {
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

    applicationComponent.inject(this);

  }
}
