package com.example.projekt;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Row extends LinearLayout {

    private Row_Type row_type;
    private Strength_Type strength_Type;
    private EditText editText;
    private ArrayList<Row_Type> selectedRowTypes;
    private PasswordStrength passwordStrength;

    public Row(Context context) {
        super(context);
        init();
    }

    public Row(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Row(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        selectedRowTypes = new ArrayList<>();
        TextView PasswordStrengthTextView = new TextView(getContext()); // Initialisera TextView
        addView(PasswordStrengthTextView); // Lägg till TextView i layout

    }

    public EditText makeRow(Row_Type row_type) {
        this.row_type = row_type;
        editText = new EditText(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(layoutParams);

        switch (row_type) {
            case PASSWORD:
                editText.setHint("Password");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                Log.d("MESSAGE", "hje hej kommer till lösenord");
                break;
            case EMAIL:
                editText.setHint("Email");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case FIRSTNAME:
                editText.setHint("First Name");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case LASTNAME:
                editText.setHint("Last Name");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            default:
                // Här kan du hantera andra typer om nödvändigt
                Log.d("MESSAGE", "404, type not found");
        }
        return editText;
    }

    public Row_Type getRow_type() {
        return row_type;
    }

    public void setRow_type(Row_Type row_type) {
        this.row_type = row_type;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }


}

