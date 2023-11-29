package com.example.projekt_new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.AccountRegistration);

        // TODO: Make sure you cannot create account without obligatory logic working
        // Password strength meter

        // Add all Input Fields here:
        accountRegistration.addNewInputField("First Name", RowType.FIRSTNAME);
        accountRegistration.addNewInputField("Password", RowType.PASSWORD);


        //Custom field here:
        accountRegistration.addNewInputField("custom", RowType.CUSTOM);
        accountRegistration.addCustomInputField("test", InputType.TYPE_CLASS_TEXT);

        //Insert custom logic for what fields are obligatory etc
        CreateAccount createAccount = new CreateAccount() {
            @Override
            public boolean obligatoryFieldsFilled() {
                //Insert your logic here
                //example
                accountRegistration.makeObligatory("First Name");
                return accountRegistration.obligatoryFieldsFilled();
            }
        };
    }
}