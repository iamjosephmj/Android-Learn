package io.iamjosephmj.dttest.di;

import javax.inject.Singleton;

import dagger.Component;
import io.iamjosephmj.dttest.presentation.MainActivity;
import io.iamjosephmj.dttest.presentation.injection.PresentationModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                PresentationModule.class
        }
)
public interface AppComponent {

    void inject(MainActivity activity);

}
