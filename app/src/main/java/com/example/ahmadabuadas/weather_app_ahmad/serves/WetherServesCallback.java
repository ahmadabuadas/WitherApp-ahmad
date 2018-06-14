package com.example.ahmadabuadas.weather_app_ahmad.serves;

import com.example.ahmadabuadas.weather_app_ahmad.data.Channel;

public interface WetherServesCallback {
    void ServerSuccess(Channel channel);
    void  Serverfailuure (Exception exception);
}
