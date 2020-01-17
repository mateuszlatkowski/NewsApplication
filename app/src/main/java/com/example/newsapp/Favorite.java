package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Favorite extends AppCompatActivity {

    private ListView listView;
    private FirebaseListAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listFavorite);

        final Query query = FirebaseDatabase.getInstance().getReference().child("Favorites");
        FirebaseListOptions<DatabaseHelper> options = new FirebaseListOptions.Builder<DatabaseHelper>()
                .setLayout(R.layout.list_favorite)
                .setLifecycleOwner(Favorite.this)
                .setQuery(query, DatabaseHelper.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView title = v.findViewById(R.id.textViewTitleFavorite);
                TextView description = v.findViewById(R.id.textViewTitleDescriptionFavorite);
                TextView author = v.findViewById(R.id.textViewAuthorFavorite);
                TextView date = v.findViewById(R.id.textViewTimeFavorite);
                ImageView image = v.findViewById(R.id.imageViewArticleFavorite);
                ImageButton delete = v.findViewById(R.id.imageButtonDelete);

                final DatabaseHelper databaseHelper = (DatabaseHelper) model;
                title.setText(databaseHelper.getTitle().toString());
                description.setText(databaseHelper.getDescription().toString());
                author.setText(databaseHelper.getAuthor().toString());
                date.setText(databaseHelper.getDate().toString());

                RequestOptions requestOptions = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round);

                Glide.with(Favorite.this).load(databaseHelper.getURL_Image().toString()).apply(requestOptions).into(image);

                url = databaseHelper.getURL().toString();

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        query.getRef().child(databaseHelper.getID_ARTICLE()).removeValue();
                        Toast.makeText(Favorite.this, "Artykuł został usunięty!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Favorite.this, ArticleActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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
