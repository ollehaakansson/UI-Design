package com.example.projekt;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountRegistration extends LinearLayout {
    private Row row;
    private Row_Type row_type;
    private ArrayList<Row_Type> selectedRowTypes;
    private HashMap<String, Row_Type> allRows;


    public AccountRegistration(Context context) {
        super(context);
        init();
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    public void init() {
        setOrientation(VERTICAL);
        selectedRowTypes = new ArrayList<>();
    }

    //Vi kallar på addfield från main och skickar in en string på vad utvecklaren vill ha för fields.
    public void addField(Row_Type row_type) {
        if(row_type !=null) {
            Log.d("IF", row_type.toString());
            selectedRowTypes.add(row_type);
            updateFields();
        }else {
            Log.d("MESSAGE", row_type.toString());
        }
    }

    private void updateFields() {
        removeAllViews();
        row = new Row(getContext());
        for (Row_Type temp : selectedRowTypes) {
            EditText tempView = row.makeRow(temp);
            addView(tempView);
            Log.d("Idk bro", "Vi kom till updatefields");

        }
    }

    public void removeField(Row_Type row_type) {
        if(row_type != null && selectedRowTypes.contains(row_type)) {
                selectedRowTypes.remove(row_type);
                updateFields();
        }else {
            Log.d("MESSAGE", row_type.toString());
        }
    }
}
