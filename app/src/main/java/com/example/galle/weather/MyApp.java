package com.example.galle.weather;

import android.app.Application;

public class MyApp extends Application {
    private String state;
    private String city;

    @Override
    public void onCreate() {
        super.onCreate();
        setState("Australia"); //初始化全局变量
        setCity("Sydney");
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String getCity() {
        return city+".json";
    }

    public void setCity(String c) {
        city = c;
    }
}

