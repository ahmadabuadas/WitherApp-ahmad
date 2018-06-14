package com.example.ahmadabuadas.weather_app_ahmad.serves;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.ahmadabuadas.weather_app_ahmad.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWitherServes {

    private WetherServesCallback callback;
    private  String location;
    private Exception error;

    public YahooWitherServes(WetherServesCallback callback){
        this.callback = callback;

    }
    public String getLocation(){
        return location;
    }

    public void refreshWither(String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u ='c' ", location);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputstream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                    StringBuilder result = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                      result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    error = e;

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null){
                   callback.Serverfailuure(error);
                   return;
                }
                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject quaryresult = data.optJSONObject("query");
                    int count = quaryresult.optInt("count");
                    if(count == 0){
                        callback.Serverfailuure(new LocationWitherException("no wither information found for "+ location));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.poupolate(quaryresult.optJSONObject("results").optJSONObject("channel"));
                    callback.ServerSuccess(channel);
                } catch (JSONException e) {
                   callback.Serverfailuure(e);
                }
            }
        }.execute(location);
    }
    public class LocationWitherException extends Exception {

        public LocationWitherException(String message) {
            super(message);
        }
    }


}
