package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
       AccountRegistration ska extend:a layout , vi ska ha en myRow som ska ärva från AccountRegistration
       Man ska kunna komma åt datan som fylls i i formuläret
       Kolla att fält är ifyllda, är ett fält obligatoriskt eller ej?
       Man ska kunna komma åt en funktion för att lägga till/ta bort en rad som kan va en knapp, kryssfält, textfält eller obligatoriskt.
       */

public class MainActivity extends AppCompatActivity {

    private AccountRegistration accountRegistration;
    private Row row;
    private Row_Type row_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration.addField("password"); //ex

    }
}