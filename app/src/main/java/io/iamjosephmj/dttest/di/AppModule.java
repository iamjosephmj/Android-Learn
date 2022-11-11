package io.iamjosephmj.dttest.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.iamjosephmj.dttest.rx.DTSchedulers;
import io.iamjosephmj.dttest.rx.DTSchedulersImpl;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Singleton
    @Provides
    DTSchedulers providesSchedulers() {
        return new DTSchedulersImpl();
    }
}
