package com.example.galle.weather.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.galle.weather.HttpCall;
import com.example.galle.weather.HttpRequest;
import com.example.galle.weather.MyApp;
import com.example.galle.weather.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HomeFragment extends Fragment {
    private TextView tvAddress;
    private TextView tvWeatherDesc;
    private TextView tvTempC;
    private ImageView ivWeather;

    private TextView tvTemperature;
    private TextView tvHumidity;
    private TextView tvFeelsLike;
    private TextView tvDewPonint;
    private TextView tvVisibility;
    private TextView tvHeatIndex;
    private View view;
    private MyApp myApp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        myApp = (MyApp) getActivity().getApplication();

        HttpCall httpCall = new HttpCall();
        httpCall.setMethodType(HttpCall.GET);
        httpCall.setUrl("http://api.wunderground.com/api/a8449ff9f8d33572/conditions/q");
//        httpCall.setUrl("http://api.wunderground.com/api/a8449ff9f8d33572/conditions/q/AU/Sydney.json");
        HashMap<String, String> params = new HashMap<>();
        String state = myApp.getState();
        String city = myApp.getCity();
        params.put("state", state);
        params.put("city", city);
        httpCall.setParams(params);
        new HttpRequest() {
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONObject observation = new JSONObject(jsonObj.getString("current_observation"));
                    JSONObject display = new JSONObject(observation.getString("display_location"));

                    tvAddress = view.findViewById(R.id.id_address);
                    tvAddress.setText(display.getString("full"));

//                    JSONObject image = new JSONObject(observation.getString("image"));
                    ivWeather = view.findViewById(R.id.id_img_weather);
                    Glide.with(HomeFragment.this).load(observation.getString("icon_url")).into(ivWeather);

                    tvWeatherDesc = view.findViewById(R.id.id_desc_weather);
                    tvWeatherDesc.setText(observation.getString("weather"));

                    tvTempC = view.findViewById(R.id.id_temp_c);
                    tvTempC.setText(observation.getString("temp_c") + "℃");

                    tvTemperature = view.findViewById(R.id.id_temperature);
                    tvTemperature.setText(observation.getString("temperature_string"));

                    tvHumidity = view.findViewById(R.id.id_humidity);
                    tvHumidity.setText(observation.getString("relative_humidity"));

                    tvFeelsLike = view.findViewById(R.id.id_feels_like);
                    tvFeelsLike.setText(observation.getString("feelslike_string"));

                    tvDewPonint = view.findViewById(R.id.id_dewpoint);
                    tvDewPonint.setText(observation.getString("dewpoint_string"));

                    tvVisibility = view.findViewById(R.id.id_visibility);
                    tvVisibility.setText(observation.getString("visibility_mi"));

                    tvHeatIndex = view.findViewById(R.id.id_heat_index);
                    tvHeatIndex.setText(observation.getString("heat_index_string"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(httpCall);
        return view;
    }
}
