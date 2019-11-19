package com.example.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
            holder.title = (TextView) convertView.findViewById(R.id.newsTitle);
            holder.description = (TextView) convertView.findViewById(R.id.newsDescription);
            convertView.setTag(holder);
        } else {
            holder = (ListNewsViewHolder) convertView.getTag();
        }

        holder.title.setId(position);
        holder.description.setId(position);

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap = data.get(position);

        try {
            holder.title.setText(hashMap.get(MainActivity.TITLE));
            holder.description.setText(hashMap.get(MainActivity.DESCRIPTION));
        } catch (Exception e) {
        }
        return convertView;
    }
}

class ListNewsViewHolder {
    TextView title, description;
}
