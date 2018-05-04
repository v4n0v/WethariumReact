package com.example.v4n0v.wethariumreact.mvp.views.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import com.example.v4n0v.wethariumreact.R;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.mvp.presenter.WeatherPresenter;
import com.example.v4n0v.wethariumreact.mvp.views.WeatherView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;


public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {

    //    @BindView(R.id.) TextView additionalTextView;
    @BindView(R.id.info_temperature_tv)
    TextView temperatureTextView;
    @BindView(R.id.info_description_tv)
    TextView descriptionTextView;
    @BindView(R.id.tv_last_update)
    TextView lastUpdTextView;
    @BindView(R.id.info_pressure)
    TextView pressureTextView;
    @BindView(R.id.info_wind)
    TextView windTextView;
    @BindView(R.id.info_humidity)
    TextView humidityTextView;
    @BindView(R.id.info_min_max)
    TextView tempMinMaxTextView;
    @BindView(R.id.info_weather_ico)
    ImageView weatherImage;

    @InjectPresenter
    WeatherPresenter presenter;

    @ProvidePresenter
    WeatherPresenter providePresenter() {
        return new WeatherPresenter(AndroidSchedulers.mainThread(), getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
        ButterKnife.bind(this, view);
        Timber.d("onCreateView");
//        initObserver();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.d("onViewCreated");
        presenter.init();

    }


    @Override
    public void applyData(Weather weather) {
        Timber.d("weather applied: " + weather.getCity() + ", " + weather.getTemperature());
        pressureTextView.setText(String.valueOf(weather.getPressure()));
        windTextView.setText(String.valueOf(weather.getWind()));
        humidityTextView.setText(String.valueOf(weather.getHumidity()));
    }

    @Override
    public void  showDescription(int description){
        descriptionTextView.setText(getWeatherDescription(description));
    }

    @Override
    public void showIcon(int weatherIcon) {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), getWeatherIcon(weatherIcon), null);
        weatherImage.setImageDrawable(drawable);
    }

    @Override
    public void showLastUpdate(String lastUpd) {
        lastUpdTextView.setText(lastUpd);
    }

    @Override
    public void showTemperature(String temperature) {
        temperatureTextView.setText(temperature);
    }

    @Override
    public void showTemperatureBetween(String temperatureBetween) {
        tempMinMaxTextView.setText(temperatureBetween);
    }

    public  int getWeatherIcon(int id) {
        if (id == 800) return R.drawable.day_synny;
        else {
            id = id / 100;

            switch (id) {
                case 2:
                    return R.drawable.day_thunder;
                case 3:
                    return R.drawable.day_drizzle;
                case 5:
                    return R.drawable.day_rainy;
                case 6:
                    return R.drawable.day_snowie;
                case 7:
                    return R.drawable.day_foggy;
                case 8:
                    return R.drawable.day_cloudly;

                default:
                    return R.drawable.day_synny;
            }
        }

    }

    public  String getWeatherDescription(int id){
        String[] desc = getContext().getResources().getStringArray(R.array.description_wether);
        return desc[id/100];
    }

}
