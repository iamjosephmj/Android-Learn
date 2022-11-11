package io.iamjosephmj.dttest.domain;

import static io.iamjosephmj.dttest.TestData.FEED_RESPONSE;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import io.iamjosephmj.dttest.TestData;
import io.iamjosephmj.dttest.data.DTRepository;
import io.iamjosephmj.dttest.data.Request;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class FetchDTDataUseCaseTest {

    DTRepository dTRepository = Mockito.mock(DTRepository.class);
    Request request = Mockito.mock(Request.class);
    CompositeDisposable disposable = new CompositeDisposable();
    FetchDTDataUseCase fetchDTDataUseCase = new FetchDTDataUseCase(dTRepository);

    @Before
    public void before() {
        disposable.clear();
    }

    @Test(expected = Exception.class)
    public void WHEN_use_case_is_invoked_SHOULD_return_exception() {
        Mockito.when(dTRepository.callDTApi(request)).then(invocation -> {
            throw new Exception("some network exception");
        });

        fetchDTDataUseCase.execute(request);
    }

    @Test
    public void WHEN_use_case_is_invoked_SHOULD_return_success_response() {
        Mockito.when(dTRepository.callDTApi(request)).thenReturn(TestData.getSuccessResponse());

        Observable<FeedsResponse> response = fetchDTDataUseCase.execute(request);

        disposable.add(
                response.subscribe(
                        feedsResponse -> Truth.assertThat(feedsResponse).isEqualTo(FEED_RESPONSE)
                ));
    }
}