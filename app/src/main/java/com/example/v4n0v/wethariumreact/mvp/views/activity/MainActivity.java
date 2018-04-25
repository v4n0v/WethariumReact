package com.example.v4n0v.wethariumreact.mvp.views.activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.v4n0v.wethariumreact.App;
import com.example.v4n0v.wethariumreact.R;
import com.example.v4n0v.wethariumreact.image.IImageLoader;
import com.example.v4n0v.wethariumreact.mvp.presenter.MainPresenter;
import com.example.v4n0v.wethariumreact.mvp.views.MainView;
import com.example.v4n0v.wethariumreact.mvp.views.fragment.WeatherFragment;
import com.example.v4n0v.wethariumreact.service.WeatherService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    private static final String TAG = "MainActivity";
    @InjectPresenter
    MainPresenter presenter;
    ServiceConnection sConn;
    boolean bind;
    @BindView(R.id.header)
    ImageView cityImage;

    @BindView(R.id.toolbar_loading)
    TextView cityTextView;

    @BindView(R.id.tv_app_name)
    TextView appTextView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Inject
    IImageLoader<ImageView> imageLoader;

    @ProvidePresenter
    MainPresenter providePresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getInstance().getAppComponent().inject(this);
        App.getInstance().getAppComponent().inject(presenter);




        appTextView.animate().alpha(0).setDuration(2000);

        presenter.init();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_frame, new WeatherFragment()).commit();

        setSupportActionBar(toolbar);
        fab.setOnClickListener(view -> selectCityDialog());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void selectCityDialog() {
        presenter.selectCity();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            presenter.update();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(MainActivity.this, WeatherHistoryActivity.class);
            Timber.d("Start WeatherHistoryActivity");
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void snack(String msg) {

    }

    @Override
    public void connectService(String city) {

        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //    Toast.makeText(getBaseContext(), "Splash service connected", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Service connected");
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

    @Override
    public void loadCityImage(String city) {
        showCityTitleText(city);
        toolbarLayout.setTitle(city);
        //cityTextView.setText(city);
       // toolbarLayout.animate().alpha(100).setDuration(2000);
        imageLoader.loadInto(city, cityImage);
        if (cityImage.getAlpha()==0){
            cityImage.animate().alpha(100).setDuration(2000);
        }
    }

    @Override
    public void selectCityDialog(String city) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.select_city));
        builder.setTitle("Выбирите город");
        final EditText input = new EditText(this);
        //input.setTypeface(Preferences.fontOswaldLight(MainActivity.this.getAssets()));
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(city);
        builder.setView(input);
        builder.setPositiveButton("Show me the weather", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.changeCity(input.getText().toString());

                //   fillFragment(weatherInfoFragment);

            }
        });
        builder.show();
    }

    void showAppText(){
        appTextView.setVisibility(View.VISIBLE);
        cityTextView.setVisibility(View.INVISIBLE);

    }

    void showCityTitleText(String city){
       // appTextView.setVisibility(View.INVISIBLE);

        cityTextView.setVisibility(View.INVISIBLE);
    }


    public void reloadPicture(View view) {

        presenter.update();
    }
}
