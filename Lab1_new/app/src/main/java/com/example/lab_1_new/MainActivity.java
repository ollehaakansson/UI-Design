package com.example.lab_1_new;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<String> groupList;
    private List<String> childList;
    private Map<String, List<String>> collection;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        input = findViewById(R.id.Input);
        input.setText("/");

        expandableListView = findViewById(R.id.ExpandableListView);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, collection);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(getGroupListener());
        expandableListView.setOnChildClickListener(getClickListener());
        input.addTextChangedListener(getTextWatcher()); //listener

    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                updateListViewBasedOnInput(s.toString());
            }
        };
    }

    private ExpandableListView.OnChildClickListener getClickListener() {
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        };
    }

    private ExpandableListView.OnGroupClickListener getGroupListener() {
        return new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        };
    }

    private void updateListViewBasedOnInput(String input) {
        boolean matchFound = false;
        if (input.equals("/")) {
            expandableListView.clearChoices();
            expandableListView.requestLayout();
            colorInputWhite();
            return;
        }

        String[] path = input.split("/");
        if (path.length > 1) {
            String group = path[1];
            if (groupList.contains(group)) {
                int groupIndex = groupList.indexOf(group);
                if (path.length == 3 && collection.get(group).contains(path[2])) {
                    int childIndex = collection.get(group).indexOf(path[2]);
                    expandableListView.setSelectedChild(groupIndex, childIndex, true);
                    expandableListView.expandGroup(groupIndex);
                    if (input.endsWith("/")) {
                        colorChildGreen(groupIndex, childIndex);
                    }
                    matchFound = true;
                } else {
                    expandableListView.setSelectedGroup(groupIndex);
                    expandableListView.expandGroup(groupIndex);
                    matchFound = true;
                }
            }
        }

        if (matchFound) {
            colorInputWhite();
        } else {
            expandableListView.clearChoices();
            expandableListView.requestLayout();
            colorInputRed();
        }
    }

    private void colorInputWhite() {
        input.setBackgroundColor(Color.WHITE);
    }
    private void colorInputRed() {
        input.setBackgroundColor(Color.RED);
    }

    public void colorChildGreen(int parentID, int childID) {
        for(int i = 0; i < groupList.get(parentID).length(); i++) {
            deColorChild(parentID, i);
        }
        int childIndex = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentID, childID));
        expandableListView.getChildAt(childIndex).setBackgroundColor(Color.GREEN);
        colorInputWhite();
    }

    public void deColorChild(int parentID, int childID){
        int childIndex = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentID, childID));
        expandableListView.getChildAt(childIndex).setBackgroundColor(Color.WHITE);
        colorInputWhite();
    }

    private void createCollection() {
        String[] data = {"green", "yellow", "red", "blue"};
        collection = new HashMap<String, List<String>>();
        for(String group : groupList) {
            loadChild(data);
            collection.put(group, childList);
        }

    }

    private void loadChild(String[] data){
        childList = new ArrayList<>();
        for(String input: data){
            childList.add(input);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("light");
        groupList.add("medium");
        groupList.add("dark");
    }
}