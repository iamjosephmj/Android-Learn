package io.iamjosephmj.dttest.data;

import com.google.gson.annotations.SerializedName;

public class Offer {
    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private Thumbnail thumbnail;

    public Offer(String title, Thumbnail thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
