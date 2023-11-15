package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

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
    private int parentCounter = 0;
    private int[] parentCounter1 = {0,0,0};
    private int lastChild;
    private int lastParent;
    private boolean hasClicked;
    private boolean hasTyped;

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

        expandableListView.setOnGroupClickListener(getGroupListener());
        expandableListView.setOnChildClickListener(getClickListener());
        input.addTextChangedListener(getTextWatcher()); //listener

    }

    private ExpandableListView.OnGroupClickListener getGroupListener() {
        return new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                hasClicked = true;
                parentCounter++;
                lastParent = currParent;
                currParent = groupPosition;
                parentCounter1[groupPosition] ++;
                Log.d("MESSAGE", String.valueOf(parentCounter1[groupPosition]));

                for(int i = 0; i < parentCounter1.length; i++){
                    if(parentCounter1[i] >= 2){
                        Log.d("MESSAGE", "Jag körs");
                        collaplseChosenGroup(groupPosition);
                        Log.d("MESSAGE", "Jag körs 2");
                        i = 0;
                        break;
                    }
                }

               if(lastParent != currParent){
                    expandableListView.collapseGroup(lastParent);
                    parentCounter = 0;
                }

                input.setText("/" + expandableListAdapter.getGroup(groupPosition).toString() + "/");
                deColorInput();

                return true;
            }
        };
    }

    private void collaplseChosenGroup(int groupPosition) {
        expandableListView.collapseGroup(groupPosition);
    }

    private ExpandableListView.OnChildClickListener getClickListener() {
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                hasClicked = true;
                colorChildGreen(groupPosition, childPosition);
                deColorInput();
                input.setText("/" + expandableListAdapter.getGroup(groupPosition).toString() + "/" + expandableListAdapter.getChild(groupPosition, childPosition).toString() + "/");
                

                return true;
            }
        };
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
                if (selected.isEmpty()) {
                    input.setText("/");
                } else { if(selected.endsWith("/") && selected.length() > 1){
                            for(int i = 0; i<groupList.size(); i++) {
                                if (selectedParts[0].equals(groupList.get(i))) {
                                    deColorInput();
                                    expandableListView.expandGroup(i);
                                    lastParent = currParent;
                                    currParent = i;
                                    break;
                                    }
                                    }if(selectedParts.length>1){
                                        for(int j = 0; j < mobileCollection.get(groupList.get(currParent)).size(); j++){
                                            Log.d("MESSAGE", mobileCollection.get(groupList.get(currParent)).get(j));
                                            deColorInput();
                                            if(mobileCollection.get(groupList.get(currParent)).get(j).equals(selectedParts[1])) {
                                                hasClicked = false;
                                                colorChildGreen(currParent, j);
                                                deColorInput();
                                                lastChild = currChild;
                                                currChild = j;
                                                if(lastChild != -1) {
                                                    deColorChild(lastParent, lastChild);
                                                }
                                                }else if (!hasClicked && !mobileCollection.get(groupList.get(currParent)).get(j).equals(selectedParts[1])){
                                                Log.d("MESSAGE", "hej jag körs");
                                                colorInputRed();
                                            }
                                        }
                                    }
                    }else if(!selected.endsWith("/") && selectedParts.length<2){
                        for(int a = 0; a < groupList.size(); a++) { // Kollapsar alla grupper om ingen grupp ska expanderas
                            for(int b = 0; b < groupList.get(a).length(); b++){
                                expandableListView.collapseGroup(a);
                                deColorChild(a, b);
                                deColorInput();
                            }


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
        for(int i = 0; i < groupList.get(parentID).length(); i++) {
            deColorChild(parentID, i);
        }
        int childIndex = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentID, childID));
        expandableListView.getChildAt(childIndex).setBackgroundColor(Color.GREEN);
        deColorInput();
    }

    public void deColorChild(int parentID, int childID){
        int childIndex = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentID, childID));
        expandableListView.getChildAt(childIndex).setBackgroundColor(Color.WHITE);
        deColorInput();
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