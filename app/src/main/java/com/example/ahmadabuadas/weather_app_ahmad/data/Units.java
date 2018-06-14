package com.example.ahmadabuadas.weather_app_ahmad.data;

import org.json.JSONObject;

public class Units implements JOSNopulater {
    private  String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void poupolate(JSONObject data) {
       temperature = data.optString("temperature");
    }
}
