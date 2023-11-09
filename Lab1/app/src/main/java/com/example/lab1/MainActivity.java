package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
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
    private int currChild = -1;
    private int currParent = -1;
    private int lastChild;
    private int lastParent;

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override   //TODO: börja tracka vilka som senast öppnats, färga/ofärga barn. Hantera fel
            public void afterTextChanged(Editable c) {
                deColorInput();
                String selected = c.toString().trim();
                String[] selectedPartsTemp = selected.split("/");
                String[] selectedParts = Arrays.stream(selectedPartsTemp).filter(s -> !s.isEmpty()).toArray(String[]::new); // Denna tar ba bort alla null-objekt i arrayen, tydligen funkade ej replaceAll så de får bli såhär
                Log.d("MESSAGE", selectedParts[0]);
                if (selected.isEmpty()) { //Kollar om input är empty
                    input.setText("/");
                } else { if(selected.endsWith("/") && selected.length() > 1){
                            for(int i = 0; i<groupList.size(); i++) {
                                if (selectedParts[0].equals(groupList.get(i))) {
                                    expandableListView.expandGroup(i);
                                    lastParent = currParent;
                                    currParent = i;
                                    break;
                                    }
                                    }if(selectedParts.length>1){
                                        for(int j = 0; j < mobileCollection.get(groupList.get(currParent)).size(); j++){
                                            Log.d("MESSAGE", mobileCollection.get(groupList.get(currParent)).get(j));
                                            if(mobileCollection.get(groupList.get(currParent)).get(j).equals(selectedParts[1])) {
                                                colorChildGreen(currParent, j);
                                                deColorInput();
                                                lastChild = currChild;
                                                currChild = j;
                                                if(lastChild != -1) {
                                                    deColorChild(lastParent, lastChild);
                                                }
                                                }else{
                                                colorInputRed();
                                            }
                                        }
                                    }
                    }else if(!selected.endsWith("/") && selectedParts.length<2){
                        for(int a = 0; a < groupList.size(); a++) { // Kollapsar alla grupper om ingen grupp ska expanderas
                            expandableListView.collapseGroup(a);
                        }
                    }
                }
            };
        };
    }

    private void deColorInput() {
        input.setBackgroundColor(Color.WHITE);
    }
    private void colorInputRed() {
        input.setBackgroundColor(Color.RED);
    }

    public void colorChildGreen(int parentID, int childID) {
        int childIndex = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentID, childID));
        expandableListView.getChildAt(childIndex).setBackgroundColor(Color.GREEN);
    }

    public void deColorChild(int parentID, int childID){
        int childIndex = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentID, childID));
        expandableListView.getChildAt(childIndex).setBackgroundColor(Color.WHITE);
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