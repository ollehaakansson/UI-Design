package com.example.lab30;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private InteractiveSearcher interactiveSearcherTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        interactiveSearcherTemp = findViewById(R.id.interactiveSearcher);
        InteractiveSearcher interactiveSearcher = new InteractiveSearcher(this, interactiveSearcherTemp);
    }
}

