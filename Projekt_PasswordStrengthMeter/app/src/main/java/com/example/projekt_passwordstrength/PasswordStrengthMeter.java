package com.example.projekt_passwordstrength;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PasswordStrengthMeter extends LinearLayout {

    PassWordField passWordField;
    StrengthMeter strengthMeter;
    DefaultStrengthValidator defaultStrengthValidator;
    String passWord;
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
        defaultStrengthValidator = new DefaultStrengthValidator();
        passWordField.addTextChangedListener(getTextWatcher());
        addView(passWordField);
        addView(strengthMeter);
    }

    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Textwatcher", "Jag kom hit");
                int progress = defaultStrengthValidator.decideProgress(s.toString());
                strengthMeter.UpdateProgressBarProgress(progress);

                int color = defaultStrengthValidator.decideColor(progress);
                strengthMeter.setProgressBarColor(color);
            }
        };
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
