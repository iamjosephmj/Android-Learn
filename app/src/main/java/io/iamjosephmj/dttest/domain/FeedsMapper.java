package io.iamjosephmj.dttest.domain;

import java.util.ArrayList;
import java.util.List;

import io.iamjosephmj.dttest.data.Offer;
import io.iamjosephmj.dttest.data.Response;
import io.reactivex.functions.Function;


public class FeedsMapper {

    public static Function<Response, FeedsResponse> mapItemToFeedsResponse() {
        return (Function<Response, FeedsResponse>) response -> {
            List<Feed> feeds = new ArrayList<>();

            for (Offer offer : response.getOffers()) {
                Feed feed = new Feed();
                feed.setImage(offer.getThumbnail().getHires());
                feed.setTitle(offer.getTitle());
                feeds.add(feed);
            }
            return new FeedsResponse(feeds);
        };
    }
}