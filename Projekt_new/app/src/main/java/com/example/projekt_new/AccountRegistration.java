package com.example.projekt_new;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedHashMap;

public class AccountRegistration extends LinearLayout {

    private RowType rowType;
    private Row row;
    private LinkedHashMap<String, Row> AllInputFields;
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
        textView = new TextView(getContext());
        createAccountButton = new Button(getContext());

    }

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

    private Row getInputField(String fieldName) {
        return AllInputFields.get(fieldName);
    }
}
