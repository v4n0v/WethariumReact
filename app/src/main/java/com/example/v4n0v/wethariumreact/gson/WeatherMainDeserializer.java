package com.example.v4n0v.wethariumreact.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by avetc on 08.12.2017.
 */

public class WeatherMainDeserializer implements JsonDeserializer<WeatherMain> {
    @Override
    public WeatherMain deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WeatherMain main = new WeatherMain();
        JsonObject jsonObject = json.getAsJsonObject();
        main.setPressure(jsonObject.get("pressure").getAsString());
        main.setTemp(jsonObject.get("temp").getAsString());
        main.setHumidity(jsonObject.get("humidity").getAsString());

        main.setTempMax(jsonObject.get("temp_max").getAsString());
        main.setTempMin(jsonObject.get("temp_min").getAsString());

        return main;
    }
}
