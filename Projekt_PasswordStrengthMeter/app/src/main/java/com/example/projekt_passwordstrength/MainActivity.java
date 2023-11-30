package com.example.projekt_passwordstrength;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private PasswordStrengthMeter passwordStrengthMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordStrengthMeter = findViewById(R.id.PasswordStrengthMeter);

        //Creates the EditText and the ProgressBar
        passwordStrengthMeter.startTheSHow();

        // Set custom colors here
        passwordStrengthMeter.setPasswordStrengthMeterColors(Color.WHITE, Color.RED, Color.rgb(255, 165, 0), Color.GREEN);

        //Insert custom logic for progress and when to switch colors.
        passwordStrengthMeter.setStrengthValidator(new StrengthValidator() {
            @Override
            public int decideProgress() {
                return 0;
            }

            @Override
            public int decideColor(int progress) {
                return 0;
            }
        });
    }
}