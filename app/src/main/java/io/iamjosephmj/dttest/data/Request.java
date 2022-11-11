package io.iamjosephmj.dttest.data;

public class Request {

    private final String appId;
    private final String uId;
    private final String token;

    public Request(String appId,
                   String uId,
                   String token) {
        this.appId = appId;
        this.uId = uId;
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public String getuId() {
        return uId;
    }

    public String getToken() {
        return token;
    }
}
