package com.example.lab_1_new;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Map<String, List<String>> collection;
    private List<String> groupList;

    public MyExpandableListAdapter(Context context, List<String> groupList, Map<String, List<String>> collection){
        this.context = context;
        this.groupList = groupList;
        this.collection = collection;
    }

    @Override
    public int getGroupCount() {
        return collection.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return collection.get(groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return collection.get(groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        String childName = getGroup(groupPosition).toString();
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_item, null);
        }
        TextView item = view.findViewById(R.id.group);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(childName);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String child = getChild(groupPosition, childPosition).toString();
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item, null);
        }
        TextView item = view.findViewById(R.id.child);
        item.setText(child);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}