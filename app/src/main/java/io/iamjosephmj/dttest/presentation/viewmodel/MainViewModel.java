package io.iamjosephmj.dttest.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.iamjosephmj.dttest.data.Request;
import io.iamjosephmj.dttest.domain.FetchDTDataUseCase;
import io.iamjosephmj.dttest.rx.DTSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<ViewState> mFeedsResponse = new MutableLiveData<>(new ViewState.StaleViewState());
    private final FetchDTDataUseCase fetchDTDataUseCase;
    private final CompositeDisposable compositeDisposable;
    private final DTSchedulers schedulers;

    public MainViewModel(
            FetchDTDataUseCase fetchDTDataUseCase,
            CompositeDisposable compositeDisposable,
            DTSchedulers schedulers) {
        this.fetchDTDataUseCase = fetchDTDataUseCase;
        this.compositeDisposable = compositeDisposable;
        this.schedulers = schedulers;
    }

    public LiveData<ViewState> getFeedsResponse() {
        return mFeedsResponse;
    }

    public void fetchData(Request request) {
        mFeedsResponse.setValue(new ViewState.LoadingViewState());

        compositeDisposable.add(fetchDTDataUseCase.execute(request)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(feedsResponse -> {
                    mFeedsResponse.setValue(
                            new ViewState.SuccessViewState(feedsResponse)
                    );
                }, throwable -> mFeedsResponse.setValue(
                        new ViewState.ErrorViewState()
                )));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
