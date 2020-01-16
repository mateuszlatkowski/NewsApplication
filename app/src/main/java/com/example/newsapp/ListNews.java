package com.example.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ListNews extends BaseAdapter{

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private DatabaseReference databaseReference;
    private String id_article;

    public ListNews(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static String randomID() {
        final String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < 10) {
            Random random = new Random();
            result.append(characters.charAt(random.nextInt(characters.length())));
            i++;
        }
        return result.toString();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder = null;

        if (convertView == null) {
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.list_news, parent, false);
            holder.title = convertView.findViewById(R.id.textViewTitle);
            holder.description = convertView.findViewById(R.id.textViewTitleDescription);
            holder.author = convertView.findViewById(R.id.textViewAuthor);
            holder.date = convertView.findViewById(R.id.textViewTime);
            holder.image = convertView.findViewById(R.id.imageViewArticle);
            holder.buttonFavorite = convertView.findViewById(R.id.imageButtonFavorite);

            convertView.setTag(holder);
        } else {
            holder = (ListNewsViewHolder) convertView.getTag();
        }

        holder.title.setId(position);
        holder.description.setId(position);
        holder.author.setId(position);
        holder.date.setId(position);
        holder.image.setId(position);

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap = data.get(position);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Favorites");

        try {
            holder.title.setText(hashMap.get(ArticleList.TITLE));
            holder.description.setText(hashMap.get(ArticleList.DESCRIPTION));
            holder.author.setText(hashMap.get(ArticleList.AUTHOR));
            holder.date.setText(hashMap.get(ArticleList.DATE));

            final HashMap<String, String> finalHashMap = hashMap;
            holder.buttonFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    id_article = randomID();

                    databaseReference.child(id_article).child("Title").setValue(finalHashMap.get(ArticleList.TITLE));
                    databaseReference.child(id_article).child("Description").setValue(finalHashMap.get(ArticleList.DESCRIPTION));
                    databaseReference.child(id_article).child("Date").setValue(finalHashMap.get(ArticleList.DATE));
                    databaseReference.child(id_article).child("URL_Image").setValue(finalHashMap.get(ArticleList.URL_IMAGE));
                    databaseReference.child(id_article).child("URL").setValue(finalHashMap.get(ArticleList.URL));
                    databaseReference.child(id_article).child("Author").setValue(finalHashMap.get(ArticleList.AUTHOR));
                    databaseReference.child(id_article).child("ID_ARTICLE").setValue(id_article);

                    Toast.makeText(activity, "Dodano do ulubionych!", Toast.LENGTH_SHORT).show();
                }
            });

            if (hashMap.get(ArticleList.URL_IMAGE).toString().length() < 5) {
                holder.image.setVisibility(View.GONE);
            } else {
                Picasso.get()
                        .load(hashMap.get(ArticleList.URL_IMAGE))
                        .resize(300, 200)
                        .centerCrop()
                        .into(holder.image);
            }
        } catch (Exception e) {
        }

        return convertView;
    }
}

class ListNewsViewHolder {
    TextView title;
    TextView description;
    TextView author;
    TextView date;
    ImageView image;
    ImageButton buttonFavorite;
}
