package com.example.projekt_passwordstrength;

public interface StrengthValidator {
    public int decideProgress(String passWord);
    public int decideColor(int progress);

    public Boolean hasCapitalLetters(String passWord);

    public Boolean hasSpecialCharacters(String passWord);

    public Boolean isLongEnough(String passWord);
}
