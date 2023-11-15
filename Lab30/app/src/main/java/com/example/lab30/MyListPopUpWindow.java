package com.example.lab30;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyListPopUpWindow extends View {

    private Context context;
    private String result;
    private Paint paint = new Paint();
    public MyListPopUpWindow(Context context, String result) {
        super(context);
        this.context = context;
        this.result = result;
    }

    public MyListPopUpWindow(Context context) {
        super(context);
    }

    public MyListPopUpWindow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListPopUpWindow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyListPopUpWindow(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setResult(String result) {
        this.result = result;
        invalidate();
    }
    //TODO: ondraw & onmesure

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int w = MeasureSpec.getSize(width);
        setMeasuredDimension(w, 85);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setTextSize(60f);      //60f
        canvas.drawText(result, 5, paint.getTextSize(), paint);
}   }
