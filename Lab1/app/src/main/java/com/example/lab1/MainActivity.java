package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<String> groupList;
    private List<String> childList;
    private Map<String, List<String>> mobileCollection;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private EditText input;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        input = findViewById(R.id.input);
        input.setText("/");

        expandableListView = findViewById(R.id.Lab1);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, mobileCollection);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selected = expandableListAdapter.getChild(groupPosition, childPosition).toString();
                Toast.makeText(getApplicationContext(), "Selected " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        input.addTextChangedListener(getTextWatcher()); //listener

    }

    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            //TODO: börja tracka vilka som senast öppnats, collapsa alla när dom inte används. Hantera fel
            @Override
            public void afterTextChanged(Editable c) {
                String selected = c.toString().trim();
                if (selected.isEmpty()) {
                    input.setText("/");
                } else {
                    if(selected.endsWith("/")){
                        String[] selectedPartsTemp = selected.split("/");
                        String[] selectedParts = Arrays.stream(selectedPartsTemp).filter(s -> !s.isEmpty()).toArray(String[]::new); // Denna tar ba bort alla null-objekt i arrayen, tydligen funkade ej replaceAll så de får bli såhär
                        Log.d("MESSAGE", selectedParts[0]);
                        int temp = 0;
                        for(int i = 0; i<groupList.size(); i++){
                            if(selectedParts[0].equals(groupList.get(i).toString())){
                                expandableListView.expandGroup(i);
                                i = temp;
                            }
                        }if(selectedParts.length>1){

                            List<String> tempList = mobileCollection.get(groupList.get(temp)); //detta blir då alltså childList för vår specifika key.
                            for(int j = 0; j<tempList.size(); j++){
                                if(selectedParts[1].equals(tempList.get(j)));
                                    colorChildGreen(groupList.get(temp), mobileCollection.get(groupList.get(temp)).get(j));
                            }
                    }

                    }
                }
            };
        };
    }

    public void colorChildGreen(String parent, String child) {

    }

    public void deColorChild(String parent, String child){

    }


    private void createCollection() {
        String[] data = {"green", "yellow", "red", "blue"};
        mobileCollection = new HashMap<String, List<String>>();
        for(String group : groupList) {
            loadChild(data);
            mobileCollection.put(group, childList);
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