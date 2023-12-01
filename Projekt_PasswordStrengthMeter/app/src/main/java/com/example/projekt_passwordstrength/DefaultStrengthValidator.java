package com.example.projekt_passwordstrength;

import android.graphics.Color;

public class DefaultStrengthValidator implements StrengthValidator{

    private PasswordStrengthMeter passwordStrengthMeter;

    @Override
    public int decideProgress(String passWord) {
        int progress = 1;
        int progressCounter = 0;

        if (hasCapitalLetters(passWord)) {
            progressCounter++;
        }
        if ((hasSpecialCharacters(passWord))){
            progressCounter++;
        }
        if (isLongEnough(passWord)){
            progressCounter++;
        }
        progress = progress + progressCounter*33; //Since max progress is 100

        return progress;
    }

    @Override
    public int decideColor(int progress) {
        int color = Color.WHITE;
        if (progress == 1){
            color = Color.WHITE;
        }else if(progress == 34){
            color = Color.RED;
        }else if (progress == 67){
            color = Color.rgb(255, 165, 0); //Orange
        } else if (progress == 100) {
            color = Color.GREEN;
        }
        return color;
    }

    @Override
    public Boolean hasCapitalLetters(String passWord) {
        for (int i = 0; i < passWord.length(); i++) {
            if (Character.isUpperCase(passWord.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean hasSpecialCharacters(String passWord) {
        for (int i = 0; i < passWord.length(); i++) {
            if (!Character.isLetterOrDigit(passWord.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isLongEnough(String passWord) {
        return (passWord.length() > 8);
    }
}
