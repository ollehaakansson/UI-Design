package com.example.lab30;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<String> data;

    public MyAdapter(Context context, ArrayList<String> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return new MyListPopUpWindow(context, data.get(position));
    }

        /*
        MyListPopUpWindow listItemView;
        if (convertView == null) {
            listItemView = new MyListPopUpWindow(context, data.get(position));
        } else {
            listItemView = (MyListPopUpWindow) convertView;
            listItemView.setResult(data.get(position));
        }
        return listItemView;
    }*/

}
