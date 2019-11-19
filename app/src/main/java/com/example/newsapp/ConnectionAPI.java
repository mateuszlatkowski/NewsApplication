package com.example.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionAPI {

    public static boolean isNewtork(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static String executeGet (String targetURL) {
        URL url;
        HttpURLConnection httpURLConnection = null;

        try {
            url = new URL(targetURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
            httpURLConnection.setRequestProperty("Content-Language", "pl-PL");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);

            InputStream inputStream;
            int status = httpURLConnection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getErrorStream(); }
            else {
                inputStream = httpURLConnection.getInputStream(); }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            bufferedReader.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
