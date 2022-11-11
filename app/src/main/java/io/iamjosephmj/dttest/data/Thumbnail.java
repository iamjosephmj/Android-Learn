package io.iamjosephmj.dttest.data;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("hires")
    String hires;

    public Thumbnail(String hires) {
        this.hires = hires;
    }

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }
}
