package com.example.projekt_new;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class Row extends LinearLayout {

    private RowType rowType;
    private EditText editText;
    private View rowView;
    private String rowName;

    /*
    All the cunstructors and Init
     */
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

    public void init() {
        this.rowView = this;
    }

    //Updates the custom field with the correct variables
    public void setCustomVariables(String inputFieldName, int inputType) {
        setHint(inputFieldName);
        setInputType(inputType);
    }

    public EditText createRow(String rowName, RowType rowType) {
        editText = new EditText(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        switch (rowType) { //Add more case:s if needed
            case FIRSTNAME:
            case LASTNAME:
                editText.setHint(rowName);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case EMAIL:
                editText.setHint(rowName);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case PASSWORD:
                editText.setHint(rowName);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case CUSTOM:
            default:
                break;
        }
        return editText;
    }

    public RowType getRowType() {
        return rowType;
    }

    public void setRowType(RowType rowType) {
        this.rowType = rowType;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public View getRowView() {
        return rowView;
    }

    public void setRowView(View rowView) {
        this.rowView = rowView;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public void setFontColor(int fontColor) {
        editText.setTextColor(fontColor);
    }

    public void setFontSize(int fontSize) {
        editText.setTextSize(fontSize);
    }

    public void setHint(String inputFieldName) {
        editText.setHint(inputFieldName);
    }

    public void setInputType(int inputType) {
        editText.setInputType(inputType);
    }

    public boolean hasText(EditText editText) { //returns true if it has text
        return !editText.getText().toString().isEmpty();
    }
}
