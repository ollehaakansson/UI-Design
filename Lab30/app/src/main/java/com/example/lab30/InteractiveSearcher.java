package com.example.lab30;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class InteractiveSearcher extends androidx.appcompat.widget.AppCompatEditText {

private Context context;
private MyListPopUpWindow myListPopUpWindow;
private MyAdapter myAdapter;
private ListPopupWindow listPopupWindow;
private int counter;
private ArrayList<String> theNames;
private int id = 0;
    public InteractiveSearcher(@NonNull Context context, InteractiveSearcher interactiveSearcher) {
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
        theNames = new ArrayList<>();
        counter = 0;
        listPopupWindow = new ListPopupWindow(context);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setText(theNames.get(position));
            }
        });
        this.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           if(s.toString().equals("")) {
               listPopupWindow.dismiss();
           }else {
               String ordSek = s.toString().trim();
               listPopupWindow.setAnchorView((InteractiveSearcher.this));

               performSearch(ordSek);
           }
        }
    });
    }
    private void performSearch(String input) {
        counter ++;
        String url = "https://andla.pythonanywhere.com/getnames/" + counter + "/" + input;

        Thread t = new Thread(new Runnable() { //skapar en ny tråd för att utföra en webbanrop samtidigt som annan kod körs
            @Override
            public void run() {
                Log.d("performSearch", "jag komm hit" + input);
                String data = getData(url);

                try { //bearbetar JSON-svaret från webben.
                    JSONObject json = new JSONObject(data);
                    JSONArray names = json.getJSONArray("result"); //Koveverterar till en JSONArray...
                    String id = json.getString("id");

                    if(counter == Integer.parseInt(id)) {
                        theNames.clear();
                        for (int x = 0; x < names.length(); x++) {
                            theNames.add(names.getString(x).trim()); //lägger till alla namn till listan
                        }
                        //interactiveSearcher.post(new Runnable() { //anropar post från iS.
                        post(new Runnable() { //anropar post från iS.
                            @Override
                            public void run() {
                                if (theNames.size() == 0){
                                    listPopupWindow.dismiss();
                                }else {
                                    myAdapter = new MyAdapter(context, theNames);
                                    listPopupWindow.setAdapter(myAdapter);
                                    listPopupWindow.show();
                                }
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

    public String getData(String newUrl){
        String result = "";
        try{
            URL url = new URL(newUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while((line = reader.readLine())!=null)
                result += line;
        }catch (IOException e){
            e.getStackTrace();
        }
        return result;
    }
}
