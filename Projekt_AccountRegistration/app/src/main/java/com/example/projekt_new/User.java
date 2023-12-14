package com.example.projekt_new;

import java.util.ArrayList;

public class User {

    private ArrayList<String> userInfo;

    public void createUser(ArrayList<String> newUserInfo){
        userInfo = newUserInfo;
    }

    public ArrayList<String> getUserInfo(){
        return userInfo;
    }

}
