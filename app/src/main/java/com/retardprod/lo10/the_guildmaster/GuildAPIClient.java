package com.retardprod.lo10.the_guildmaster;

import com.loopj.android.http.*;


public class GuildAPIClient {
    private static final String BASE_URL = "http://lowcost-env.pebrvp3sjs.eu-west-1.elasticbeanstalk.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
