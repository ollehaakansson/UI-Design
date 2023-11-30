package com.example.projekt_passwordstrength;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;

public class PassWordField extends androidx.appcompat.widget.AppCompatEditText {

    private View passWordFieldView;

    private String input;
    public PassWordField(Context context) {
        super(context);
        init();
    }

    public PassWordField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PassWordField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PassWordField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
        init();
    }

    public void init(){
        setHint("Password");
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        this.passWordFieldView = this;
    }

    //Setters and Getters

    public void setColor(int color) {
        setTextColor(color);
    }
    public View getPassWordFieldView() {
        return passWordFieldView;
    }

    public void setPassWordFieldView(View passWordView) {
        this.passWordFieldView = passWordView;
    }
}