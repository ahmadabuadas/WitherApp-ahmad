package com.example.ahmadabuadas.weather_app_ahmad;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadabuadas.weather_app_ahmad.data.Channel;
import com.example.ahmadabuadas.weather_app_ahmad.serves.WetherServesCallback;
import com.example.ahmadabuadas.weather_app_ahmad.serves.YahooWitherServes;

public class MainActivity extends AppCompatActivity implements WetherServesCallback {

    private ImageView witherimageView;
    private TextView TempruserText;
    private TextView CondtionText;
    private TextView LocationText;
    private YahooWitherServes serves;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        witherimageView = (ImageView) findViewById(R.id.witherimageView);
        TempruserText  = (TextView) findViewById(R.id.TempruserText);
        CondtionText = (TextView) findViewById(R.id.CondtionText);
        LocationText = (TextView) findViewById(R.id.LocationText);
        serves = new YahooWitherServes(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading .....");
        dialog.show();
        serves.refreshWither("London,UK");

    }

    @Override
    public void ServerSuccess(Channel channel) {
      dialog.hide();
      int resourceId = getResources().getIdentifier("drawable/icon_"+channel.getItem().getCondtion().getCode(),null,getPackageName());
        Drawable weatherIconDrawble = getResources().getDrawable(resourceId);
        witherimageView.setImageDrawable( weatherIconDrawble);

        TempruserText.setText(channel.getItem().getCondtion().getTemperature()+ "\u00Be "+ channel.getUnits().getTemperature());
        LocationText.setText(serves.getLocation());
        CondtionText.setText(channel.getItem().getCondtion().getDescription()) ;
    }

    @Override
    public void Serverfailuure(Exception exception) {
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
