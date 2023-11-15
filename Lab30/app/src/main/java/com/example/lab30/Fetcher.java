package com.example.lab30;

import com.google.gson.Gson;
import android.util.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Fetcher {
    private int id;
    private String input;
    public Fetcher (int id, String input) {
        this.id = id;
        this.input = input;
    }
    ArrayList <String> fetch (String input) {
        ArrayList<String> data = new ArrayList<>();
        try {
            URL url = new URL ("https://andla.pythonanywhere.com/getnames/" + id + "/" + input);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream responseStream = new BufferedInputStream(connection.getInputStream());
                InputStreamReader reader = new InputStreamReader(responseStream);
                Gson gson = new Gson();
                Search item = gson.fromJson(reader, Search.class);
                data.addAll(item.getResult());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getId() {
        return id;
    }

    public String getInput() {
        return input;
    }
    public ArrayList <String> getInputList (String input) {
        return fetch(input);
    }
}
