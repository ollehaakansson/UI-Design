package com.example.lab1_new;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> parents;
    Map<String, List<String>> collection;
    private Adapter adapter;
    int expandedParent = -1;
    int checkedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchBar = findViewById(R.id.search_bar);
        searchBar.setText("/");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        loadData();

        adapter = new Adapter(this, parents, collection);
        expandableListView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0) {
                    searchBar.setText("/");
                    searchBar.setBackgroundColor(Color.TRANSPARENT);
                }

                checkTree(searchBar);

            }
        });

        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(expandedParent != -1) {
                    expandableListView.collapseGroup(expandedParent);
                    expandedParent = -1;
                    if(checkedItem != -1) {
                        adapter.checkItem(expandedParent, checkedItem);
                        checkedItem = -1;
                    }
                }
                String[] arrOfStr = searchBar.getText().toString().split("/");
                if(searchBar.getText().toString().equals("/") || !(((String) adapter.getGroup(groupPosition)).equals(arrOfStr[1])))
                    searchBar.setText("/" + (String) adapter.getGroup(groupPosition));

                else {
                    searchBar.setText("/");
                }
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(checkedItem != childPosition) {
                    searchBar.setText("/" + (String) adapter.getGroup(groupPosition) + "/" + (String) adapter.getChild(groupPosition, childPosition));
                }
                return true;
            }
        });

    }

    public void checkTree(EditText searchBar) {
        String[] arrOfStr = searchBar.getText().toString().split("/");

        if(arrOfStr.length <= 1) {
            return;
        }

        if(checkedItem != -1) {
            adapter.checkItem(expandedParent, checkedItem);
        }

        boolean isPossibleInTree = false;

        if(arrOfStr.length > 1) {
            if (collection.keySet().contains(arrOfStr[1])) {
                searchBar.setBackgroundColor(Color.TRANSPARENT);
                expandedParent = parents.indexOf(arrOfStr[1]);
                expandableListView.expandGroup(expandedParent);
                if (arrOfStr.length > 2) {
                    List<String> a = collection.get(arrOfStr[1]);
                    for(String s : a) {
                        if(s.equals(arrOfStr[2])) {
                            checkedItem = a.indexOf(s);
                            adapter.checkItem(expandedParent, checkedItem);
                            searchBar.setBackgroundColor(Color.TRANSPARENT);
                            return;
                        }
                        if(!isPossibleInTree) {
                            for (int i = 0; i < arrOfStr[2].length(); i++) {
                                if (arrOfStr[2].length() > s.length() || s.charAt(i) != arrOfStr[2].charAt(i)) {
                                    isPossibleInTree = false;
                                    break;
                                }
                                isPossibleInTree = true;
                            }
                        }
                    }
                    if(isPossibleInTree) searchBar.setBackgroundColor(Color.TRANSPARENT);
                    else searchBar.setBackgroundColor(Color.RED);
                    checkedItem = -1;
                }
            } else {
                if(expandedParent != -1) expandableListView.collapseGroup(expandedParent);
                for(String s : collection.keySet()) {
                    if(!isPossibleInTree) {
                        for (int i = 0; i < arrOfStr[1].length(); i++) {
                            if (arrOfStr[1].length() > s.length() || s.charAt(i) != arrOfStr[1].charAt(i)) {
                                isPossibleInTree = false;
                                break;
                            }
                            isPossibleInTree = true;
                        }
                        if(isPossibleInTree) break;
                    }
                }
                if(isPossibleInTree) searchBar.setBackgroundColor(Color.TRANSPARENT);
                else searchBar.setBackgroundColor(Color.RED);
                expandedParent = -1;
            }
        }
    }

    public void loadData() {
        parents = new ArrayList<>();
        collection = new HashMap<>();

        parents.add("light");
        parents.add("medium");
        parents.add("dark");

        List<String> light = new ArrayList<>();
        List<String> medium = new ArrayList<>();
        List<String> dark = new ArrayList<>();

        light.add("green");
        light.add("yellow");
        light.add("red");
        light.add("blue");

        medium.add("green");
        medium.add("yellow");
        medium.add("red");
        medium.add("blue");

        dark.add("green");
        dark.add("yellow");
        dark.add("red");
        dark.add("blue");

        collection.put(parents.get(0), light);
        collection.put(parents.get(1), medium);
        collection.put(parents.get(2), dark);

    }

}