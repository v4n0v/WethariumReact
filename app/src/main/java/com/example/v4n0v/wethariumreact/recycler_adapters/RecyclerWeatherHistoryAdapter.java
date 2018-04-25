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

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by v4n0v on 25.04.18.
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
            String pressureInfo = weather.getPressure()+ root.getResources().getString(R.string.pressure_dim);
            String windInfo = weather.getWind()+  root.getResources().getString(R.string.wind_dim);
            String humInfo = weather.getHumidity()+ root.getResources().getString(R.string.humidity_dim);

            cityTV.setText(weather.getCity());
            tempTV.setText(weatherDecorator.temperatureFormat(weather.getTemperature()));//  getString(R.string.cels));
            pressureTV.setText(pressureInfo);
            humidityTV.setText(humInfo);
            windTV.setText(windInfo);
            timeTV.setText(weatherDecorator.getLastUpdate(weather.getTime()));
            int weatherIcon = weatherDecorator.getWeatherIcon(weather.getId());
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
}
