package com.example.projekt_passwordstrength;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private PasswordStrengthMeter passwordStrengthMeter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordStrengthMeter = findViewById(R.id.PasswordStrengthMeter);

        passwordStrengthMeter.testMethod();
    }
}