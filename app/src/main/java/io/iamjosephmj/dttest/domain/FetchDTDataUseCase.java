package io.iamjosephmj.dttest.domain;

import javax.inject.Inject;

import io.iamjosephmj.dttest.data.DTRepository;
import io.iamjosephmj.dttest.data.Request;
import io.reactivex.Observable;

public class FetchDTDataUseCase {

    public DTRepository DTRepository;

    @Inject
    public FetchDTDataUseCase(DTRepository DTRepository) {
        this.DTRepository = DTRepository;
    }

    public Observable<FeedsResponse> execute(Request request) {
        return DTRepository.callDTApi(request)
                .map(FeedsMapper.mapItemToFeedsResponse());
    }
}
