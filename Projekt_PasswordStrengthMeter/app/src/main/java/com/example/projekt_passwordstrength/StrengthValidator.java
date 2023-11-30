package com.example.projekt_passwordstrength;

public interface StrengthValidator {
    public int decideProgress();
    public int decideColor(int progress);
}
