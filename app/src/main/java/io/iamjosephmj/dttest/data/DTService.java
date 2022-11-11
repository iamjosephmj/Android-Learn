package io.iamjosephmj.dttest.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DTService {

    @GET("feed/v1/offers.json")
    Observable<Response> callDTApi(
            @Query("appid") String appId,
            @Query("uid") String uid,
            @Query("token") String token,
            @Query("format") String format,
            @Query("locale") String locale,
            @Query("offer_types") Integer offerTypes,
            @Query("ip") String ip
    );
}
