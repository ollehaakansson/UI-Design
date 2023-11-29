package com.example.projekt_new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;

public class MainActivity extends AppCompatActivity {

    private AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.AccountRegistration);


        // Add all Input Fields here:
        accountRegistration.addNewInputField("First Name", RowType.FIRSTNAME);

        //Custom field here:
        accountRegistration.addNewInputField("custom", RowType.CUSTOM);
        accountRegistration.addCustomInputField("test", InputType.TYPE_CLASS_TEXT);

    }
}