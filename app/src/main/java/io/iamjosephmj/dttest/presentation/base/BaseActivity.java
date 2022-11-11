package io.iamjosephmj.dttest.presentation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.iamjosephmj.dttest.DTApplication;
import io.iamjosephmj.dttest.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initializeDI(AppComponent appComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDI(
                DTApplication.
                        application
                        .getAppComponent()
        );
    }
}
