package io.iamjosephmj.dttest.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("SerializedName")
    private List<Offer> offers;

    public Response(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
