package io.iamjosephmj.dttest.data;

import org.junit.Test;
import org.mockito.Mockito;

import io.iamjosephmj.dttest.TestData;
import io.iamjosephmj.dttest.constants.Constants;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class DTRepositoryTest {

    DTService service = Mockito.mock(DTService.class);

    DTRepository dTRepository = new DTRepository(service);

    @Test
    public void WHEN_API_is_Fetched_THEN_Return_Success_with_data() {
        Mockito.when(service.callDTApi(
                TestData.APP_ID,
                TestData.U_ID,
                TestData.TOKEN,
                Constants.FORMAT,
                Constants.LOCALE,
                Constants.OFFER_TYPE,
                Constants.IP
        )).thenReturn(TestData.getSuccessResponse());
        Request request = new Request(TestData.APP_ID, TestData.U_ID, TestData.TOKEN);

        Observable<Response> observable = dTRepository.callDTApi(request);

        Mockito.verify(service).callDTApi(TestData.APP_ID,
                TestData.U_ID,
                TestData.TOKEN,
                Constants.FORMAT,
                Constants.LOCALE,
                Constants.OFFER_TYPE,
                Constants.IP);

        TestObserver<Response> actual = TestObserver.create();
        observable.subscribe(actual);

        actual.assertComplete();
        actual.assertResult(TestData.RESPONSE);
    }

    @Test
    public void WHEN_API_is_Fetched_THEN_Return_Exception_to_simulate_api_error() {
        Exception exception = new Exception("Some network exception");
        Mockito.when(service.callDTApi(
                TestData.APP_ID,
                TestData.U_ID,
                TestData.TOKEN,
                Constants.FORMAT,
                Constants.LOCALE,
                Constants.OFFER_TYPE,
                Constants.IP
        )).thenReturn(Observable.error(exception));
        Request request = new Request(TestData.APP_ID, TestData.U_ID, TestData.TOKEN);

        Observable<Response> responseObservable = dTRepository.callDTApi(request);

        TestObserver<Response> actual = TestObserver.create();
        responseObservable.subscribe(actual);

        actual.assertError(exception);
    }
}