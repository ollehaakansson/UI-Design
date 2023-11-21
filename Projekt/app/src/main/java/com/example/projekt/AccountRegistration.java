package com.example.projekt;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountRegistration extends LinearLayout {
    private Row row;
    private Row_Type row_type;
    private ArrayList<Row_Type> selectedRowTypes;

    public AccountRegistration(Context context) {
        super(context);
        init();
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        selectedRowTypes = new ArrayList<Row_Type>();
        //row.setRow_type(Row_Type.PASSWORD);

    }

    //TODO: Lägg till alla andra cases
    public Row_Type getFieldTypeFromName(String feildName){
        switch (feildName){
            case "password":
                return Row_Type.PASSWORD;
            default:
                return null;
        }
    }

    //Vi kallar på addfield från main och skickar in en string på vad utvecklaren vill ha för fields.
    public void addField(String fieldName) {
        Row_Type temp = getFieldTypeFromName(fieldName);
        if(temp !=null) {
            selectedRowTypes.add(temp);
            drawFields(temp);
        }else {
            Log.d("MESSAGE", fieldName);
        }
    }

    public void removeField(String fieldName) {
        Row_Type temp = getFieldTypeFromName(fieldName);
        if(temp != null && selectedRowTypes.contains(temp)) {
                selectedRowTypes.remove(temp);
                drawFields(temp);
        }else {
            Log.d("MESSAGE", fieldName);
        }
    }

    public void drawFields(Row_Type row_type){
        row.setRow_type(row_type);
    }
}
