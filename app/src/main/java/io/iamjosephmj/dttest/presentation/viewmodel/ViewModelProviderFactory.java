package io.iamjosephmj.dttest.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.iamjosephmj.dttest.domain.FetchDTDataUseCase;
import io.iamjosephmj.dttest.presentation.viewmodel.MainViewModel;
import io.iamjosephmj.dttest.rx.DTSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.NotImplementedError;

@Singleton
public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    @Inject
    FetchDTDataUseCase fetchDTDataUseCase;

    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    DTSchedulers schedulers;

    @Inject
    public ViewModelProviderFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            if (modelClass.isAssignableFrom(MainViewModel.class)) {
                return (T) new MainViewModel(
                        fetchDTDataUseCase,
                        compositeDisposable,
                        schedulers
                );
            } else {
                throw new NotImplementedError("viewModel not initialized");
            }
        } catch (NotImplementedError e) {
            throw new RuntimeException("couldn't create class");
        }
    }
}
