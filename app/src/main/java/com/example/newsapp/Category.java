package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity{

    private Button buttonGeneral;
    private Button buttonBusiness;
    private Button buttonEntertainment;
    private Button buttonHealth;
    private Button buttonScience;
    private Button buttonSports;
    private Button buttonTechnology;
    private Button buttonFavorite;

    private String country;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonGeneral = findViewById(R.id.buttonGeneral);
        buttonBusiness = findViewById(R.id.buttonBusiness);
        buttonEntertainment = findViewById(R.id.buttonEntertainment);
        buttonHealth = findViewById(R.id.buttonHealth);
        buttonScience = findViewById(R.id.buttonScience);
        buttonSports = findViewById(R.id.buttonSports);
        buttonTechnology = findViewById(R.id.buttonTechnology);
        buttonFavorite = findViewById(R.id.buttonFavorite);

        country = getIntent().getExtras().get("Kraj").toString();

        buttonGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "general";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });

        buttonBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "business";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });
        buttonEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "entertainment";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });

        buttonHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "health";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });

        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "science";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });

        buttonSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "sports";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });

        buttonTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "technology";
                Intent articleList = new Intent(Category.this, ArticleList.class);
                articleList.putExtra("Kraj", country);
                articleList.putExtra("Kategoria", category);
                startActivity(articleList);
            }
        });

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category.this, Favorite.class));
            }
        });

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
