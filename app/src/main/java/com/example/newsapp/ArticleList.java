package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleList extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    private String API_KEY = "6804dc4e59854c6a931d2a290a3e5f2f";

    private String country;
    private String category;
    private String url;

    private ProgressBar progressBar;

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
        setContentView(R.layout.activity_article_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listNews);
        progressBar = findViewById(R.id.loader);

        listView.setEmptyView(progressBar);
        listView.setTextFilterEnabled(true);

        if (ConnectionAPI.isNewtork(getApplicationContext())) {
            DownloadNews downloadNews = new DownloadNews();
            downloadNews.execute();
        } else {
            Toast.makeText(ArticleList.this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        }
    }

    class DownloadNews extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            country = getIntent().getExtras().get("Kraj").toString();
            category = getIntent().getExtras().get("Kategoria").toString();

            if (category.equals("general")) {
                url = ConnectionAPI.executeGet("https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=" + API_KEY);
            }
            else {
                url = ConnectionAPI.executeGet("https://newsapi.org/v2/top-headlines?country=" + country + "&category=" + category + "&apiKey=" + API_KEY);
            }

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
                Toast.makeText(ArticleList.this, "Błąd pobierania informacji!", Toast.LENGTH_SHORT).show();
            }

            ListNews adapter = new ListNews(ArticleList.this, arrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Intent intent = new Intent(ArticleList.this, ArticleActivity.class);
                    intent.putExtra("url", arrayList.get(+position).get(URL));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean OnCreateOptionsMenu(Menu menu) {
        return true;
    }
}
