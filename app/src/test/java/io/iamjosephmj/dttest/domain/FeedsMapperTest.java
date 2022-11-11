package io.iamjosephmj.dttest.domain;

import com.google.common.truth.Truth;

import org.junit.Test;

import io.iamjosephmj.dttest.TestData;
import io.iamjosephmj.dttest.data.Response;

public class FeedsMapperTest {

    @Test
    public void TEST_mapping() throws Exception {
        Response response = TestData.RESPONSE;
        FeedsResponse feedsResponse = FeedsMapper.mapItemToFeedsResponse().apply(response);

        Truth.assertThat(feedsResponse.getFeeds().get(0).getTitle()).isEqualTo(TestData.FEED_RESPONSE.getFeeds().get(0).getTitle());
        Truth.assertThat(feedsResponse.getFeeds().get(0).getImage()).isEqualTo(TestData.FEED_RESPONSE.getFeeds().get(0).getImage());
    }


    @Test(expected = NullPointerException.class)
    public void TEST_mapping_exception() throws Exception {
        FeedsMapper.mapItemToFeedsResponse().apply(null);
    }
}