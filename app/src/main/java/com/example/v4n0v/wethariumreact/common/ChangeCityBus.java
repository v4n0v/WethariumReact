package com.example.v4n0v.wethariumreact.common;

import com.squareup.otto.Bus;

/**
 * Created by v4n0v on 22.04.18.
 */

public class ChangeCityBus {
    public static Bus getBus() {
        return bus;
    }

    private static Bus bus = new Bus();
}
