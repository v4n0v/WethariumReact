package com.example.v4n0v.wethariumreact.recycler_adapters;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v4n0v.wethariumreact.R;
import com.example.v4n0v.wethariumreact.common.WeatherDecorator;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * RecyclerView адаптер для списка просмотренных городов
 */

public class RecyclerWeatherHistoryAdapter  extends RecyclerView.Adapter<RecyclerWeatherHistoryAdapter.ViewHolder> {
    private IListPresenter presenter;

    public RecyclerWeatherHistoryAdapter(IListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_shown, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getViewCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IListWeatherRaw, View.OnClickListener {
        int pos = -1;
        @BindView(R.id.last_city)
        TextView cityTV;
        @BindView(R.id.last_temp)
        TextView tempTV;
        @BindView(R.id.last_humidity)
        TextView humidityTV;
        @BindView(R.id.last_wind)
        TextView windTV;
        @BindView(R.id.last_pressure)
        TextView pressureTV;
        @BindView(R.id.last_description)
        TextView descriptionTV;
        @BindView(R.id.last_time)
        TextView timeTV;
        @BindView(R.id.last_img)
        ImageView img;
        @BindView(R.id.root)
                View root;

        WeatherDecorator weatherDecorator;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            weatherDecorator = new WeatherDecorator(itemView.getContext());
            root.setOnClickListener(this);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setWeather(Weather weather) {

            cityTV.setText(weather.getCity());
            tempTV.setText(weatherDecorator.temperatureFormat(weather.getTemperature()));//  getString(R.string.cels));
            pressureTV.setText(String.valueOf(weather.getPressure()));
            humidityTV.setText(String.valueOf(weather.getHumidity()));
            windTV.setText(String.valueOf(weather.getWind()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM: HH:mm", Locale.getDefault());

            timeTV.setText(dateFormat.format(weather.getTime()));
            int weatherIcon = getWeatherIcon(weather.getId());
            Drawable drawable = ResourcesCompat.getDrawable(root.getContext().getResources(), weatherIcon, null);
            img.setImageDrawable(drawable);
        }




        @Override
        public void onClick(View view) {
            int pos = getLayoutPosition();
            Timber.d("click "+pos);
            presenter.selectItem(pos);
        }
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



}
