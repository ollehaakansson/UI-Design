package com.example.projekt;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/* Varje row har en typ & en edittext
    typen tas från enum Row_Type & edittexten bestäms därifrån också
    När setRow_Type kallas på så kallar den i sin tur på makeRow som skapar en rad i vår layout med passande edittext & typ.
 */
public class Row extends LinearLayout {

    private Row_Type row_type;
    private EditText editText;

    public Row(Context context) {
        super(context);
    }

    public Row(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Row(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void makeRow() {
        editText = new EditText(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(layoutParams);

        switch (row_type) {
            case PASSWORD:
                editText.setHint("Password");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
                Log.d("MESSAGE", "404, type not found");
        }
        addView(editText);
    }

    public Row_Type getRow_type() {
        return row_type;
    }

    public void setRow_type(Row_Type row_type) {
        this.row_type = row_type;
        makeRow();
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

}
