package com.example.projekt_new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.AccountRegistration);

        // TODO: Password strength meter

        // Add all Input Fields here:
        accountRegistration.addNewInputField("First name", RowType.FIRSTNAME);
        accountRegistration.addNewInputField("Last name", RowType.LASTNAME);
        accountRegistration.addNewInputField("Password", RowType.PASSWORD);

        // Add all obligatory fields here:
        accountRegistration.makeObligatory("First name");


        //Custom field here:
        //accountRegistration.addNewInputField("custom", RowType.CUSTOM);
        //accountRegistration.addCustomInputField("test", InputType.TYPE_CLASS_TEXT);

        //Insert custom logic for what fields are obligatory etc
        accountRegistration.setCreateAccount(new CreateAccount() {
            @Override
            public boolean obligatoryFieldsFilled() {
                //Insert custom logic
                return true;
            }
        });

    }
}