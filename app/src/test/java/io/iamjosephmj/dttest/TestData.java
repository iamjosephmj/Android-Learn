package io.iamjosephmj.dttest;

import java.util.ArrayList;
import java.util.List;

import io.iamjosephmj.dttest.data.Offer;
import io.iamjosephmj.dttest.data.Response;
import io.iamjosephmj.dttest.data.Thumbnail;
import io.iamjosephmj.dttest.domain.Feed;
import io.iamjosephmj.dttest.domain.FeedsResponse;
import io.reactivex.Observable;

public class TestData {
    public static final String APP_ID = "APP_ID";
    public static final String U_ID = "U_ID";
    public static final String TOKEN = "TOKEN";

    private static List<Offer> getOffers() {
        Offer offer = new Offer(
                "title",
                new Thumbnail(
                        "hires"
                )
        );
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        return offers;
    }

    private static List<Feed> getFeeds() {
        Feed feed = new Feed();
        feed.setTitle("title");
        feed.setImage("hires");
        List<Feed> feeds = new ArrayList<>();
        feeds.add(feed);
        return feeds;
    }

    public static final Response RESPONSE = new Response(getOffers());
    public static final FeedsResponse FEED_RESPONSE = new FeedsResponse(getFeeds());


    public static Observable<Response> getSuccessResponse() {
        return Observable.just(RESPONSE);
    }

    public static Observable<FeedsResponse> getSuccessFeedResponse() {
        return Observable.just(FEED_RESPONSE);
    }


}
