package com.example.v4n0v.wethariumreact.recycler_adapters;

/**
 * Created by avetc on 01.04.2018.
 */

public interface IListPresenter {
    int pos = -1;
    void bindView(IListWeatherRaw view);
    int getViewCount();
    void selectItem(int pos);
}
