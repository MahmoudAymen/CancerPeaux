package com.example.aymen.splashactivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by aymen on 26/03/2017.
 */
public class drawS {
    public static Bitmap setRGBcolors(int paramInt1, int paramInt2, Bitmap paramBitmap, int paramInt3, int paramInt4, int paramInt5)
    {
        Canvas localCanvas = new Canvas(paramBitmap);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        localPaint.setFilterBitmap(true);
        localPaint.setDither(true);
        localPaint.setStrokeWidth(1.0F);
        localPaint.setColor(65536);
        localCanvas.drawRect(paramInt2, paramInt1, paramInt2 + 40, paramInt1 + 40, localPaint);
        localPaint.setColor(65536);
        localCanvas.drawRect(paramInt2 + 50, paramInt1, paramInt2 + 60, paramInt1 + 10, localPaint);
        localCanvas.drawRect(paramInt2 + 70, paramInt1, paramInt3 + (paramInt2 + 70), paramInt1 + 10, localPaint);
        localPaint.setColor(16711936);
        localCanvas.drawRect(paramInt2 + 50, paramInt1 + 15, paramInt2 + 60, paramInt1 + 25, localPaint);
        localCanvas.drawRect(paramInt2 + 70, paramInt1 + 15, paramInt4 + (paramInt2 + 70), paramInt1 + 25, localPaint);
        localPaint.setColor(16711936);
        localCanvas.drawRect(paramInt2 + 50, paramInt1 + 30, paramInt2 + 60, paramInt1 + 40, localPaint);
        localCanvas.drawRect(paramInt2 + 70, paramInt1 + 30, paramInt5 + (paramInt2 + 70), paramInt1 + 40, localPaint);
        return paramBitmap;
    }
    public static Bitmap setScolor(int paramInt1, int paramInt2, Bitmap paramBitmap, int paramInt3, int paramInt4)
    {
        Canvas localCanvas = new Canvas(paramBitmap);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        localPaint.setFilterBitmap(true);
        localPaint.setDither(true);
        localPaint.setStrokeWidth(1.0F);
        localPaint.setColor(7829368);
        localCanvas.drawRect(paramInt2, paramInt1, paramInt2 + paramInt3, paramInt1 + 30, localPaint);
        localCanvas.drawRect(paramInt2, paramInt1 + 50, paramInt2 + paramInt4, paramInt1 + 80, localPaint);
        return paramBitmap;
    }

}
