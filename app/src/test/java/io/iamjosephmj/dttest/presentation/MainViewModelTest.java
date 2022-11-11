package io.iamjosephmj.dttest.presentation;

import static io.iamjosephmj.dttest.TestData.getSuccessFeedResponse;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.common.truth.Truth;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import io.iamjosephmj.dttest.TestSchedulers;
import io.iamjosephmj.dttest.data.Request;
import io.iamjosephmj.dttest.domain.FetchDTDataUseCase;
import io.iamjosephmj.dttest.presentation.viewmodel.MainViewModel;
import io.iamjosephmj.dttest.presentation.viewmodel.ViewState;
import io.iamjosephmj.dttest.rx.DTSchedulers;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MainViewModelTest {
    Request request = Mockito.mock(Request.class);
    FetchDTDataUseCase fetchDTDataUseCase = Mockito.mock(FetchDTDataUseCase.class);
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    DTSchedulers schedulers = new TestSchedulers();

    MainViewModel mainViewModel = new MainViewModel(
            fetchDTDataUseCase,
            compositeDisposable,
            schedulers
    );

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Test
    public void WHEN_the_view_model_is_started_SHOULD_initialize_the_feed_response_sate_as_stale() {
        ViewState viewState = mainViewModel.getFeedsResponse().getValue();

        Truth.assertThat(viewState).isInstanceOf(ViewState.StaleViewState.class);
    }

    @Test
    public void WHEN_the_api_call_is_started_SHOULD_show_loading_in_the_beginning() {
        Mockito.when(fetchDTDataUseCase.execute(request)).thenReturn(getSuccessFeedResponse());

        mainViewModel.fetchData(request);
        ViewState viewState = mainViewModel.getFeedsResponse().getValue();

        Truth.assertThat(viewState).isInstanceOf(ViewState.LoadingViewState.class);
    }

    @Test
    public void WHEN_the_api_call_is_successful_SHOULD_show_the_date() {
        Mockito.when(fetchDTDataUseCase.execute(request)).thenReturn(getSuccessFeedResponse());
        mainViewModel.fetchData(request);

        ViewState viewStateInitial = mainViewModel.getFeedsResponse().getValue();
        Truth.assertThat(viewStateInitial).isInstanceOf(ViewState.LoadingViewState.class);

        TestSchedulers.testScheduler.triggerActions();

        ViewState viewStateFinal = mainViewModel.getFeedsResponse().getValue();
        Truth.assertThat(viewStateFinal).isInstanceOf(ViewState.SuccessViewState.class);
    }

    @Test
    public void WHEN_the_api_call_is_failure_SHOULD_show_the_error() {
        Mockito.when(fetchDTDataUseCase.execute(request)).thenReturn(Observable.error(new Exception("Some network exception.")));

        mainViewModel.fetchData(request);

        ViewState viewStateInitial = mainViewModel.getFeedsResponse().getValue();
        Truth.assertThat(viewStateInitial).isInstanceOf(ViewState.LoadingViewState.class);

        TestSchedulers.testScheduler.triggerActions();

        ViewState viewStateFinal = mainViewModel.getFeedsResponse().getValue();
        Truth.assertThat(viewStateFinal).isInstanceOf(ViewState.ErrorViewState.class);
    }

    // Not sure, how much values do is brings if we write a test for on-clear method.
}