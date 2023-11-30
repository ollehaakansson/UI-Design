package com.example.projekt_passwordstrength;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

public class StrengthMeter extends ProgressBar {

    private View progressBarView;
    private int startColor;
    private int weakColor;
    private int mediumColor;
    private int strongColor;

    public StrengthMeter(Context context) {
        super(context, null, android.R.attr.progressBarStyleHorizontal); //android.R.attr.progressBarStyleHorizontal makes it horizontal
        init();
    }

    public StrengthMeter(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal); //android.R.attr.progressBarStyleHorizontal makes it horizontal
        init();
    }

    public StrengthMeter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, android.R.attr.progressBarStyleHorizontal); //android.R.attr.progressBarStyleHorizontal makes it horizontal
        init();
    }

    public StrengthMeter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, android.R.attr.progressBarStyleHorizontal); //android.R.attr.progressBarStyleHorizontal makes it horizontal
        init();
    }

    public void init(){
        this.setMax(100);
        this.setProgress(0);
        startColor = Color.WHITE;
        weakColor = Color.RED;
        mediumColor = Color.rgb(255, 165, 0); //Orange
        strongColor = Color.GREEN;
        setProgressBarColor(startColor);
        this.progressBarView = this;
    }

    public void setProgressBarColor(int color){
        if (getProgressDrawable() != null) {
            getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }
    public void UpdateProgressBarProgress(int progress) {
        setProgress(progress);
    }
    public void setProgressBarColors(int startColor, int weakColor, int mediumColor, int strongColor) {
        this.startColor = startColor;
        this.weakColor = weakColor;
        this.mediumColor = mediumColor;
        this.strongColor = strongColor;
    }
}
