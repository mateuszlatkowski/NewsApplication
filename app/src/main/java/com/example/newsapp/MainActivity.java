package com.example.newsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    private String API_KEY = "6804dc4e59854c6a931d2a290a3e5f2f";

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String AUTHOR = "author";
    static final String URL = "url";
    static final String URL_IMAGE = "urlToImage";
    static final String DATE = "publishedAt";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listNews);

        if (ConnectionAPI.isNewtork(getApplicationContext())) {
            DownloadNews downloadNews = new DownloadNews();
            downloadNews.execute();
        } else {
            Toast.makeText(MainActivity.this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        }
    }

    class DownloadNews extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            String url = ConnectionAPI.executeGet("https://newsapi.org/v2/top-headlines?country=pl&apiKey=" + API_KEY);
            return url;
        }

        @Override
        protected void onPostExecute(String url) {

            try {
                JSONObject jsonResponse = new JSONObject(url);
                JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<>();
                    map.put(TITLE, jsonObject.optString(TITLE));
                    map.put(DESCRIPTION, jsonObject.optString(DESCRIPTION));
                    map.put(AUTHOR, jsonObject.optString(AUTHOR));
                    map.put(URL, jsonObject.optString(URL));
                    map.put(URL_IMAGE, jsonObject.optString(URL_IMAGE));
                    map.put(DATE, jsonObject.optString(DATE));
                    arrayList.add(map);
                }
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Błąd pobierania informacji!", Toast.LENGTH_SHORT).show();
            }

            ListNews adapter = new ListNews(MainActivity.this, arrayList);
            listView.setAdapter(adapter);
        }
    }
}
