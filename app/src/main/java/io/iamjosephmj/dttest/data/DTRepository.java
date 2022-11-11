package io.iamjosephmj.dttest.data;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.iamjosephmj.dttest.constants.Constants;
import io.reactivex.Observable;

@Singleton
public class DTRepository {

    private final DTService mDTService;

    @Inject
    public DTRepository(DTService DTService) {
        this.mDTService = DTService;
    }

    public Observable<Response> callDTApi(Request request) {
        return mDTService.callDTApi(
                request.getAppId(),
                request.getuId(),
                request.getToken(),
                Constants.FORMAT,
                Constants.LOCALE,
                Constants.OFFER_TYPE,
                Constants.IP
        );
    }
}
