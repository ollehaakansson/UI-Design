package com.example.projekt_passwordstrength;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PasswordStrengthMeter extends LinearLayout {

    PassWordField passWordField;
    StrengthMeter strengthMeter;
    private StrengthValidator strengthValidator;
    public PasswordStrengthMeter(Context context) {
        super(context);
        init();
    }

    public PasswordStrengthMeter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordStrengthMeter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PasswordStrengthMeter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        setOrientation(VERTICAL);
    }

    public void startTheSHow(){
        passWordField = new PassWordField(getContext());
        strengthMeter = new StrengthMeter(getContext());
        addView(passWordField);
        addView(strengthMeter);
    }



    //setter and getters
    public void updateStrengthMeterProgress(int progress){
        strengthMeter.UpdateProgressBarProgress(progress);
    }
    public void updateStrengthMeterColor(int color) {
        strengthMeter.setProgressBarColor(color);
    }

    public void setPasswordStrengthMeterColors(int startColor , int weakColor, int mediumColor, int strongColor) {
        strengthMeter.setProgressBarColors(startColor, weakColor, mediumColor, strongColor);
    }

    public void setStrengthValidator(StrengthValidator strengthValidatorTemp) {
        this.strengthValidator = strengthValidatorTemp;
    }

}
