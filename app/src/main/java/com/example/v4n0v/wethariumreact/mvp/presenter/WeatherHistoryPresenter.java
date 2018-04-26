package com.example.v4n0v.wethariumreact.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.v4n0v.wethariumreact.common.ChangeCityBus;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.mvp.model.WeatherHistoryModel;
import com.example.v4n0v.wethariumreact.mvp.views.WeatherHistoryView;
import com.example.v4n0v.wethariumreact.recycler_adapters.IListPresenter;
import com.example.v4n0v.wethariumreact.recycler_adapters.IListWeatherRaw;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

@InjectViewState
public class WeatherHistoryPresenter extends MvpPresenter<WeatherHistoryView> {

    private WeatherHistoryModel model;
    private Scheduler scheduler;
    private PublishSubject<String> subject;
    Observer<String> observer;


    public ListPresenter getListPresenter() {
        return listPresenter;
    }

    private ListPresenter listPresenter = new ListPresenter();


    class ListPresenter implements IListPresenter {
        List<Weather> items = new ArrayList<>();

        @Override
        public void bindView(IListWeatherRaw view) {
            view.setWeather(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void selectItem(int pos) {

            Weather weather = items.get(pos);
            getViewState().toast("Loading "+weather.getCity()+" weather");
            ChangeCityBus.getBus().post(weather.getCity());

        }
    }

    public void clearList(){
        model.clearList()
                .observeOn(scheduler)
                .subscribeOn(Schedulers.io())
                .subscribe(boo-> getViewState().startActivity());
    }


    public WeatherHistoryPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        WeatherBroadcastBus.getBus().register(this);
        model = new WeatherHistoryModel(scheduler);
    }



    public void init() {

        model.getWeathers()
                .observeOn(scheduler)
                .subscribeOn(Schedulers.io())
                .subscribe(weathers -> {
                    Timber.d("Weather list capacity = " + weathers.size());
                    listPresenter.items=weathers;
                    getViewState().updateList();
                });

    }

    @Subscribe
    public void onRecieve(Weather weather){
        Paper.book("weather").write("weather", weather);
        getViewState().startActivity();
    }


}
