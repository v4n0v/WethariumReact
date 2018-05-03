package com.example.v4n0v.wethariumreact.mvp.views.activity;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v4n0v.wethariumreact.App;
import com.example.v4n0v.wethariumreact.R;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.image.IImageLoader;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.example.v4n0v.wethariumreact.service.WeatherService;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import io.paperdb.Paper;
import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {
    ServiceConnection sConn;
    boolean bind;
    Weather weather;

    @Inject
    ConnectionHolder connection;
    @Inject
    IImageLoader<ImageView> imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        App.getInstance().getAppComponent().inject(this);
        WeatherBroadcastBus.getBus().register(this);
        connectService(loadData());
    }

    public void connectService(String city) {
        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //    Toast.makeText(getBaseContext(), "Splash service connected", Toast.LENGTH_SHORT).show();
                Timber.d("Service connected");
                WeatherService serviceWeather = ((WeatherService.WeatherBinder) service).getService();
                serviceWeather.changeCity(city);
                bind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bind = false;
            }
        };

        Intent intent = new Intent(getBaseContext(), WeatherService.class);
        bindService(intent, sConn, BIND_AUTO_CREATE);

    }
    private  String  loadData(){
        return Paper.book("city").read("city", "Moscow");
    }


    @Subscribe
    public void recievedMessage(Weather msg){
        weather =msg;
        Timber.d("WeatherBroadcastBus message "+ weather.getCity() + ", temp = " + weather.getTemperature());
        Paper.book("weather").write("weather", weather);
        WeatherBroadcastBus.getBus().unregister(this);

        Bundle bundle = null;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            TextView v = (TextView) findViewById(R.id.tv_app_name);
            if (v != null) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "trans");
                bundle = options.toBundle();
            }
        }



        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        if (bundle == null) {
          startActivity(intent);
        } else {
            startActivity(intent, bundle);
        }

    }
}
