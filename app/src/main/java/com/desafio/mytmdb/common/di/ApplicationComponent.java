package com.desafio.mytmdb.common.di;

import android.app.Application;
import android.content.Context;

import com.desafio.mytmdb.common.MyTmdbApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    Application getApplication();

    Context getContext();

    void inject(MyTmdbApplication myTmdbApplication);

}
