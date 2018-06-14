package com.example.ahmadabuadas.weather_app_ahmad.data;

import org.json.JSONObject;

public class Condtion implements JOSNopulater {
    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void poupolate(JSONObject data) {
      code = data.optInt("code");
      temperature = data.optInt("temp");
      description = data.optString("text");
    }
}
