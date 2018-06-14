package com.example.ahmadabuadas.weather_app_ahmad.data;

import org.json.JSONObject;

public class Channel implements JOSNopulater{
    private Units units;
    private Item item;

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void poupolate(JSONObject data) {
      units = new Units();
      units.poupolate(data.optJSONObject("units"));

      item = new Item();
      item.poupolate(data.optJSONObject("item"));
    }
}
