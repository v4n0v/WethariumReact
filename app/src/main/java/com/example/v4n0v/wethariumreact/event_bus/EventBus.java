package com.example.v4n0v.wethariumreact.event_bus;


import com.squareup.otto.Bus;

public class EventBus {
    public static Bus getBus() {
        return bus;
    }

    private static Bus bus = new Bus();
}
