package com.example.v4n0v.wethariumreact.okApi;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by v4n0v on 22.04.18.
 */

public class ConnectionCore {
    private static ConnectionCore core;

    public static ConnectionCore getConnection() {
        if (core==null) return new ConnectionCore();
        return core;
    }

    public String getResponse(URL url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder()
                    .url(url)
                    .build();

            return client.newCall(req).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
