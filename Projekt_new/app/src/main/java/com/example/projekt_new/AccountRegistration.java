package com.example.projekt_new;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class AccountRegistration extends LinearLayout {

    private RowType rowType;
    private Row row;
    private LinkedHashMap<String, Row> AllInputFields;
    private ArrayList<String> obligatoryFieldNames;
    private TextView textView;
    private Button createAccountButton;

    public AccountRegistration(Context context) {
        super(context);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        setOrientation(VERTICAL);
        AllInputFields = new LinkedHashMap<>();
        obligatoryFieldNames = new ArrayList<>();
        textView = new TextView(getContext());
        createAccountButton = new Button(getContext());

    }

    // Om man vill lägga till en custom så kallar vi på denna funktionen som uppdaterar en row som vi tidigare skapat som inte har några värden.
    public void addCustomInputField(String inputFieldName, int inputType){
        if (getInputField(inputFieldName) != null) {
            getInputField(inputFieldName).setCustomVariables(inputFieldName, inputType);
        }
    }

    public void addNewInputField(String rowName, RowType rowType) {
        if (rowType != null) {
            row = new Row(getContext());
            row.setRowName(rowName);
            row.setRowType(rowType);
            AllInputFields.put(rowName, row);
            updateInputFields();
        }
    }

    // Uppdaterar input-fältens view, tar bort alla pot input fält och fyller med nya
    public void updateInputFields() {
        removeAllViews();
        for (String rowName : AllInputFields.keySet()) {
            Row row = AllInputFields.get(rowName);
            EditText editText = row.createRow(rowName, (AllInputFields.get(rowName)).getRowType());
            row.setRowView(editText);
            addView(editText);
        }
        updateCreateAccountButton(); //Vi behöver ju enbart en create account knapp om det finns fält.
    }

    //Uppdaterar knappens view
    public void updateCreateAccountButton(){
        createAccountButton.setText("Create Account");
        addView(createAccountButton);
    }

    public void makeObligatory(String rowName){
        if(!rowName.isEmpty()) {
            if(AllInputFields.get(rowName) != null){
                obligatoryFieldNames.add(rowName);
            }
        }
    }

    public ArrayList<String> getAllFieldNames(){
        ArrayList<String> temp = new ArrayList<>();
        for (String rowName : AllInputFields.keySet()){
            temp.add(rowName);
        }
        return temp;
    }
    private Row getInputField(String fieldName) {
        return AllInputFields.get(fieldName);
    }

}
