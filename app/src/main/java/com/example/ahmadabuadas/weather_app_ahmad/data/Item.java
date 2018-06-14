package com.example.ahmadabuadas.weather_app_ahmad.data;

import org.json.JSONObject;

public class Item implements JOSNopulater{
    private Condtion condtion;



    @Override
    public void poupolate(JSONObject data) {
    condtion = new Condtion();
    condtion.poupolate(data.optJSONObject("condition"));
    }

    public Condtion getCondtion() {
        return condtion;
    }
}
