package com.example.v4n0v.wethariumreact.mvp.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.v4n0v.wethariumreact.R;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.mvp.presenter.WeatherHistoryPresenter;
import com.example.v4n0v.wethariumreact.mvp.views.WeatherHistoryView;
import com.example.v4n0v.wethariumreact.recycler_adapters.RecyclerWeatherHistoryAdapter;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class WeatherHistoryActivity extends MvpAppCompatActivity implements WeatherHistoryView {


    @InjectPresenter
    WeatherHistoryPresenter presenter;

    @ProvidePresenter
    WeatherHistoryPresenter providePresenter() {
        return new WeatherHistoryPresenter(AndroidSchedulers.mainThread());
    }

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
@BindView(R.id.history_toolbar)
Toolbar toolbar;
    RecyclerWeatherHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_history);
        ButterKnife.bind(this);
        init();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
    @Override
    protected void onPause() {
        super.onPause();
        WeatherBroadcastBus.getBus().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WeatherBroadcastBus.getBus().register(this);
    }


    @Subscribe
    public void onRecieve(Weather weather){
        Paper.book("weather").write("weather", weather);
        startActivity();
    }

    void init() {
        presenter.init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerWeatherHistoryAdapter(presenter.getListPresenter());
        recyclerView.setAdapter(adapter);

//        PublishSubject<String> subject = PublishSubject.create();
//        presenter.setSubject(subject);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.db_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            Toast.makeText(this, "Clear", Toast.LENGTH_SHORT).show();
            presenter.clearList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WeatherHistoryActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startActivity() {

        Intent intent = new Intent(WeatherHistoryActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
