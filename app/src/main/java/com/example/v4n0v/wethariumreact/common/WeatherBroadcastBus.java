package com.example.v4n0v.wethariumreact.common;


import com.squareup.otto.Bus;

public class WeatherBroadcastBus {
    public static Bus getBus() {
        return bus;
    }

    private static Bus bus = new Bus();
}
