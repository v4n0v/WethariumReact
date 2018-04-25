package com.example.v4n0v.wethariumreact.okApi.cache;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.entities.realm.RealmWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;


public class RealmWeatherCache implements ICacheWeather {

    @Override
    public void readWeatherFromCache(String city, ObservableEmitter<Weather> e) {
        Realm realm = Realm.getDefaultInstance();
        RealmWeather realmWeather = realm.where(RealmWeather.class).equalTo("city", city).findFirst();
        if (realmWeather == null) {
            e.onError(new RuntimeException("No weather in realm"));
        } else {
            e.onNext(new Weather(realmWeather.getCity(), realmWeather.getTemperature(), realmWeather.getDescription(),
                    realmWeather.getWind(), realmWeather.getPressure(), realmWeather.getHumidity(),
                    realmWeather.getId(), realmWeather.getTempMin(), realmWeather.getTempMax(), realmWeather.getTime()));
        }
        realm.close();
        e.onComplete();

        Timber.d("weather loaded from cache");
    }


    @Override
    public void writeWeatherToCache(Weather weather) {
        Realm realm = Realm.getDefaultInstance();
        RealmWeather realmWeather = realm.where(RealmWeather.class).equalTo("city", weather.getCity()).findFirst();
        // если такая щзапись есть. удаляю, затем пишу новую
        if (realmWeather != null) {
            realm.executeTransaction(realm1 -> {
                //  realmWeather.setCity(weather.getCity());
                realmWeather.setTemperature(weather.getTemperature());
                realmWeather.setDescription(weather.getDescription());
                realmWeather.setTemperature(weather.getTemperature());
                realmWeather.setWind(weather.getWind());
                realmWeather.setPressure(weather.getPressure());
                realmWeather.setHumidity(weather.getHumidity());
                realmWeather.setId(weather.getId());
                realmWeather.setTempMin(weather.getTempMin());
                realmWeather.setTempMax(weather.getTempMax());
                realmWeather.setTime(weather.getTime());
            });
        } else {
            realm.executeTransaction(realm2 -> {
                RealmWeather newRealmWeather = realm.createObject(RealmWeather.class, weather.getCity());
                //    newRealmWeather.setCity(weather.getCity());
                newRealmWeather.setTemperature(weather.getTemperature());
                newRealmWeather.setDescription(weather.getDescription());
                newRealmWeather.setWind(weather.getWind());
                newRealmWeather.setPressure(weather.getPressure());
                newRealmWeather.setHumidity(weather.getHumidity());
                newRealmWeather.setId(weather.getId());
                newRealmWeather.setTempMin(weather.getTempMin());
                newRealmWeather.setTempMax(weather.getTempMax());
                newRealmWeather.setTime(weather.getTime());
            });
        }
        realm.close();
        Timber.d("weather was written to cache");

    }


    @Override
    public void readWeathersListFromCache(ObservableEmitter<List<Weather>> e) {
        Realm realm = Realm.getDefaultInstance();

        RealmWeather realmWea = realm.where(RealmWeather.class).findFirst();
        RealmResults<RealmWeather> realmWeathers = realm.where(RealmWeather.class).findAll();

        if (realmWeathers == null) {
            e.onError(new RuntimeException("No weather in realm"));
        } else {
            List<Weather> weathers = new ArrayList<>();
            Timber.d("size = " + weathers.size());

            for (RealmWeather realmWeather : realmWeathers) {
                weathers.add(new Weather(realmWeather.getCity(), realmWeather.getTemperature(), realmWeather.getDescription(),
                        realmWeather.getWind(), realmWeather.getPressure(), realmWeather.getHumidity(),
                        realmWeather.getId(), realmWeather.getTempMin(), realmWeather.getTempMax(),
                        realmWeather.getTime()));
            }
            e.onNext(weathers);
            Timber.d("weathers loaded from cache");
        }
        realm.close();
        e.onComplete();
    }

    @Override
    public void clearBase(ObservableEmitter<Boolean> e) {
        Realm realm = Realm.getDefaultInstance();
//        if (realm.where(RealmWeather.class).findAll().deleteAllFromRealm())
//            e.onNext(true);
//        else
//            e.onError(new RuntimeException("Error realm deleting"));

        RealmResults<RealmWeather> realmWeathers = realm.where(RealmWeather.class).findAll();
        if (realmWeathers == null) {
            e.onError(new RuntimeException("No weather in realm"));
        } else {

            realm.executeTransaction(realm2 -> {
                        for (RealmWeather realmWeather : realmWeathers) {
                            realmWeather.deleteFromRealm();
                        }
                    });
            e.onNext(true);

        }


        realm.close();
        e.onComplete();
    }


}
