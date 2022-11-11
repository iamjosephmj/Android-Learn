package io.iamjosephmj.dttest.presentation.injection;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.iamjosephmj.dttest.domain.FeedsResponse;
import io.iamjosephmj.dttest.presentation.adapter.MainAdapter;

@Module
public class PresentationModule {

    @Provides
    MainAdapter providesAdapter() {
        return new MainAdapter(new FeedsResponse(new ArrayList<>()));
    }
}
