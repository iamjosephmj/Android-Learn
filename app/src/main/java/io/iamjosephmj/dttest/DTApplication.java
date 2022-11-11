package io.iamjosephmj.dttest;

import android.app.Application;

import io.iamjosephmj.dttest.di.AppComponent;
import io.iamjosephmj.dttest.di.AppModule;
import io.iamjosephmj.dttest.di.DaggerAppComponent;
import io.iamjosephmj.dttest.di.NetworkModule;

public class DTApplication extends Application {

    public static DTApplication application;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mAppComponent = DaggerAppComponent.builder()
                // Decided to go for the vanilla approach as the project is small.
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
