package com.example.lab0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Stor
        LinearLayout stor = new LinearLayout(this);
        LinearLayout.LayoutParams storLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Bredd
                LinearLayout.LayoutParams.MATCH_PARENT // Höjd
        );
        stor.setLayoutParams(storLayoutParams);
        stor.setOrientation(LinearLayout.VERTICAL); // Sätt layouten till vertikal


        //Generell storlek för rader
        LinearLayout.LayoutParams radLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Bredd
                LinearLayout.LayoutParams.WRAP_CONTENT // Höjd
        );

        //Rad 1
        LinearLayout rad1 = new LinearLayout(this);
        rad1.setLayoutParams(radLayoutParams);

        TextView namnText = new TextView(this);
        namnText.setText("Namn");
        namnText.setTextSize(30);

        EditText namnInput = new EditText(this);
        namnInput.setLayoutParams(radLayoutParams);


        rad1.addView(namnText);
        rad1.addView(namnInput);


       //Rad 2
        LinearLayout rad2 = new LinearLayout(this);
        rad2.setLayoutParams(radLayoutParams);

        TextView losenText = new TextView(this);
        losenText.setText("Lösenord");
        losenText.setTextSize(30);

        EditText losenInput = new EditText(this);
        losenInput.setLayoutParams(radLayoutParams);
        losenInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        rad2.addView(losenText);
        rad2.addView(losenInput);

        //Rad 3
        LinearLayout rad3 = new LinearLayout(this);
        rad3.setLayoutParams(radLayoutParams);

        TextView epostText = new TextView(this);
        epostText.setText("Epost");
        epostText.setTextSize(30);

        EditText epostInput = new EditText(this);
        epostInput.setLayoutParams(radLayoutParams);


        rad3.addView(epostText);
        rad3.addView(epostInput);

        //Rad 4

        LinearLayout rad4 = new LinearLayout(this);
        rad4.setLayoutParams(radLayoutParams);

        TextView alderText = new TextView(this);
        alderText.setText("Ålder");
        alderText.setTextSize(30);

        SeekBar seekBar = new SeekBar(this);
        seekBar.setLayoutParams(radLayoutParams);
        seekBar.setMax(100);

        rad4.addView(alderText);
        rad4.addView(seekBar);


        // Addview:s
        stor.addView(rad1);
        stor.addView(rad2);
        stor.addView(rad3);
        stor.addView(rad4);
        setContentView(stor);
        //setContentView(R.layout.activity_main);
    }
}