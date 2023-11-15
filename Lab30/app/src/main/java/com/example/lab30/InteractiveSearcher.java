package com.example.lab30;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InteractiveSearcher extends androidx.appcompat.widget.AppCompatEditText {

private Context context;
private MyListPopUpWindow myListPopUpWindow;
private MyAdapter myAdapter;
private Fetcher fetcher;
private int id = 0;
    public InteractiveSearcher(@NonNull Context context) {
        super(context);
        this.context = context;
        input();
    }

    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        input();
    }

    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        input();
    }

    private void input() {
    addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String ordSek = s.toString().trim();



        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    }
    private void performSearch(String input, InteractiveSearcher interactiveSearcher) {
        Thread t = new Thread(new Runnable() { //skapar en ny tråd för att utföra en webbanrop samtidigt som annan kod körs
            @Override
            public void run() {
                ArrayList<String> data = fetcher.getInputList(input);
                try { //bearbetar JSON-svaret från webben.
                    for(String var: data) {
                        JSONObject json = new JSONObject(var);
                        JSONArray names = json.getJSONArray("result"); //Koveverterar till en JSONArray...
                        ArrayList<String> theNames = new ArrayList<>(); //...och skapar en lista av dessa namn
                        for (int x = 0; x < names.length(); x++) {
                            theNames.add(names.getString(x)); //lägger till alla namn till listan
                        }
                        post(new Runnable() { //anropar post från iS.
                            @Override
                            public void run() {
                                fetcher.setId(id);
                                fetcher.setInput(input);
                            }
                        });
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }
}
