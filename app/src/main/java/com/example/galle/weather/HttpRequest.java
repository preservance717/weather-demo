package com.example.galle.weather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends AsyncTask<HttpCall, String, String> {
    private static final String UTF_8 = "UTF-8";

    @Override
    protected String doInBackground(HttpCall... params) {
        HttpURLConnection urlConnection = null;
        HttpCall httpCall = params[0];
        StringBuilder response = new StringBuilder();

        try {
            String dataParams = getDataString(httpCall.getParams(), httpCall.getMethodType());
            Log.i("dataParams", dataParams + "");

//            URL url = new URL(httpCall.getUrl());
            URL url = new URL(httpCall.getMethodType() == HttpCall.GET ? httpCall.getUrl() + dataParams : httpCall.getUrl());
            Log.i("url", url + "");

            urlConnection = (HttpURLConnection) url.openConnection();
            Log.i("connect", "start");
            urlConnection.setRequestMethod(httpCall.getMethodType() == HttpCall.GET ? "GET" : "POST");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            if (httpCall.getParams() != null && httpCall.getMethodType() == HttpCall.POST) {
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, UTF_8));
                writer.append("");
                writer.flush();
                writer.close();
                os.close();
            }
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResponse(s);
    }

    public void onResponse(String response) {
    }

    private String getDataString(HashMap<String, String> params, int methodType) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        String city = "", state ="";
        for (Map.Entry<String, String> entry : params.entrySet()) {
//            if (isFirst) {
//                isFirst = false;
//                if (methodType == HttpCall.GET) {
//                    result.append("/");
//                }
//            } else {
//                result.append("/");
//            }
            if (URLEncoder.encode(entry.getKey()).equals("city")) {
                city = URLEncoder.encode(entry.getValue());
            }else {
                state = URLEncoder.encode(entry.getValue());
            }
//            result.append(URLEncoder.encode(entry.getKey(), UTF_8));
//            result.append("=");
//            result.append(URLEncoder.encode(entry.getValue(), UTF_8));
        }
        result.append("/" + state + "/" + city);
        return result.toString();
    }
}
