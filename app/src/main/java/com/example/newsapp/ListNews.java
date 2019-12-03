package com.example.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ListNews extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

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

    public View getView(int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder = null;

        if (convertView == null) {
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.list_news, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textViewTitle);
            holder.description = (TextView) convertView.findViewById(R.id.textViewTitleDescription);
            holder.author = (TextView) convertView.findViewById(R.id.textViewAuthor);
            holder.date = (TextView) convertView.findViewById(R.id.textViewTime);
            holder.image = (ImageView) convertView.findViewById(R.id.imageViewArticle);
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

        try {
            holder.title.setText(hashMap.get(MainActivity.TITLE));
            holder.description.setText(hashMap.get(MainActivity.DESCRIPTION));
            holder.author.setText(hashMap.get(MainActivity.AUTHOR));
            holder.date.setText(hashMap.get(MainActivity.DATE));

            if (hashMap.get(MainActivity.URL_IMAGE).toString().length() < 5) {
                holder.image.setVisibility(View.GONE);
            } else {
                Picasso.get()
                        .load(hashMap.get(MainActivity.URL_IMAGE))
                        .resize(300, 200)
                        .centerCrop()
                        .into(holder.image);
            }
        } catch (Exception e) {}
        return convertView;
    }
}

class ListNewsViewHolder {
    TextView title;
    TextView description;
    TextView author;
    TextView date;
    ImageView image;
}
