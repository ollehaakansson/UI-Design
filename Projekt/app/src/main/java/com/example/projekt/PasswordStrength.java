package com.example.projekt;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class PasswordStrength extends View{

    private View passwordStrengthView; // Lägg till en TextView för att visa styrkan
    private Strength_Type strength_type;

    public PasswordStrength(Context context) {
        super(context);
    }

    public PasswordStrength(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordStrength(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PasswordStrength(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setUpPasswordStrengthListener(EditText passwordField) {
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Inget behov av att implementera detta
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Inget behov av att implementera detta
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateViewBasedOnStrength(s.toString());
            }
        });
    }

    private void updateViewBasedOnStrength(String password) {
        Strength_Type strength = calculateStrength(password);
        switch (strength) {
            case WEAK:
                setBackgroundColor(Color.RED);
                break;
            case MEDIUM:
                setBackgroundColor(Color.YELLOW);
                break;
            case STRONG:
                setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void makeBox() {
        passwordStrengthView = new View(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        passwordStrengthView.setBackgroundColor(Color.WHITE);
    }

    public static Strength_Type calculateStrength(String password) {
        int strength = 0;

        if (containsUppercase(password)) strength++;
        if (containsLowercase(password)) strength++;
        if (containsSpecialCharacter(password)) strength++;
        if (containsDigit(password)) strength++;

        if (strength <= 2) {
            return Strength_Type.WEAK;
        } else if (strength == 4) {
            return Strength_Type.STRONG;
        } else {
            return Strength_Type.MEDIUM;
        }
    }

    private static boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private static boolean containsLowercase(String password) {
        return password.matches(".*[a-z].*");
    }

    private static boolean containsSpecialCharacter(String password) {
        return password.matches(".*[-+<>'¨!.,?].*");
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*[1234567890].*");
    }
}
