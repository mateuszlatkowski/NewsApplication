package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.newsapp.R.id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapterCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewCountry);

        final String[] country = {"Argentyna", "Brazylia", "Bułgaria", "Chiny", "Czechy", "Francja", "Niemcy", "Norwegia", "Polska", "Ukraina", "Wielka Brytania"};
        final String[] country_ID = {"ar", "br", "bg", "cn", "cz", "fr", "de", "no", "pl", "ua", "gb"};

        int[] flags = {R.drawable.argentyna, R.drawable.brazylia, R.drawable.bulgaria, R.drawable.chiny, R.drawable.czechy, R.drawable.francja, R.drawable.niemcy, R.drawable.norwegia, R.drawable.polska, R.drawable.ukraina, R.drawable.gb};

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for (int i = 0; i < 11; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("txt", country[i]);
            hashMap.put("id", country_ID[i]);
            hashMap.put("flag", Integer.toString(flags[i]));
            aList.add(hashMap);
        }

        String[] from = {"flag", "txt", "id"};
        int[] to = {R.id.imageViewFlag, R.id.textViewCountry, R.id.textViewCountryID};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_country, from, to);

        arrayAdapterCountry = new ArrayAdapter<String>(MainActivity.this, R.layout.list_country, R.id.textViewCountry, country);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent categoryList = new Intent(MainActivity.this, Category.class);
                categoryList.putExtra("Kraj", country_ID[position]);
                startActivity(categoryList);
            }
        });

    }
}