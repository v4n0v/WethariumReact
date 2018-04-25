package com.example.v4n0v.wethariumreact.common;

import android.content.Context;

import com.example.v4n0v.wethariumreact.R;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class WeatherDecorator {
private Context context;

    public WeatherDecorator(Context context) {
        this.context = context;
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

    public String temperatureFormat(float temperature) {
        String temp="";
        int roudedTemp= Math.round(temperature);
        if (roudedTemp > 0)
            temp+="+";
        else if(roudedTemp<0)
            temp+="-";
        temp+=String.valueOf(Math.round(temperature))+context.getString(R.string.cels);
        return temp;
    }

    public String getTemperatureBetween(float min, float max){
      return   temperatureFormat(min) + " " + temperatureFormat(max);
    }

    public  String getWeatherDescription(int id){
        String[] desc = context.getResources().getStringArray(R.array.description_wether);
        return desc[id/100];
    }

    public String getLastUpdate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM' at ' HH:mm", Locale.getDefault());
        return  "Updated: " + dateFormat.format(time);
    }
}
