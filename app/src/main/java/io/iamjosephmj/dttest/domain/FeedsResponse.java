package io.iamjosephmj.dttest.domain;

import java.util.List;

public class FeedsResponse {
    private final List<Feed> feeds;

    public FeedsResponse(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
}
