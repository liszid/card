package com.example.cardgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    private Paint paint;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.MEDIUMSPRINGGREEN);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.MEDIUMSPRINGGREEN);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(50, 50, getWidth() - 50, getHeight() - 50, paint);
    }
}
