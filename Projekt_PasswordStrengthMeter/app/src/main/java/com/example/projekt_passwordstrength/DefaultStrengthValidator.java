package com.example.projekt_passwordstrength;

public class DefaultStrengthValidator implements StrengthValidator{
    @Override
    public int decideProgress() {
        return 0;
    }

    @Override
    public int decideColor(int progress) {
        return 0;
    }
}
