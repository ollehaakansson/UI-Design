package com.example.projekt_new;

import static android.graphics.ColorSpace.Model.RGB;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
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

    private CreateAccount createAccount;

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
        createAccount = new DefaultCreateAccount();

    }

    // If you want to add a custom field we call this function which updates a row which we previously made.
    public void addCustomInputField(String inputFieldName, int inputType){
        if (getInputField(inputFieldName) != null) {
            getInputField(inputFieldName).setCustomVariables(inputFieldName, inputType);
        }
    }

    public void setFontSizeAndColor(int fontSize, int color) {
        Log.d("Accountregistration", String.valueOf(fontSize));

        for (String rowName : AllInputFields.keySet()) {
            AllInputFields.get(rowName).setFontColor(color);
            AllInputFields.get(rowName).setFontSize(fontSize);

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

    //Updates the input-fields view, removes all old fields and fills with new ones
    public void updateInputFields() {
        removeAllViews();
        for (String rowName : AllInputFields.keySet()) {
            Row row = AllInputFields.get(rowName);
            EditText editText = row.createRow(rowName, (AllInputFields.get(rowName)).getRowType());
            row.setRowView(editText);
            addView(editText);
        }
        updateCreateAccountButton();
    }

    //Updates the buttons view
    public void updateCreateAccountButton(){
        createAccountButton.setText("Create Account");
        createAccountButton.setOnClickListener(v -> {
            createAccount.makeButtonDoStuff();
            if(createAccount.obligatoryFieldsFilled()){
                for (Row row : AllInputFields.values()) { //Empties all the fields.
                    EditText editText = row.getEditText();
                    if (editText != null) {
                        editText.setText("");
                    }
                }
            }
        });
        addView(createAccountButton);
    }


    /*
    Functions to handle creating accounts
     */

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

    public boolean obligatoryFieldsFilled(){
        for (String rowName : obligatoryFieldNames) {
            if (!row.hasText(AllInputFields.get(rowName).getEditText())) {
                return false;
            }
        }
        return true;
    }
    private Row getInputField(String fieldName) {
        return AllInputFields.get(fieldName);
    }

    public void setCreateAccount(CreateAccount createAccountTemp){
        this.createAccount = createAccountTemp;
    }

    public View getRowView(String rowName) {
        return AllInputFields.get(rowName).getRowView();

    }
}
