package com.example.aymen.splashactivity;

import android.graphics.Bitmap;

/**
 * Created by aymen on 20/03/2017.
 */
public class ImageItem{
    private Bitmap image;
    private String title;

    public ImageItem(Bitmap paramBitmap, String paramString)
    {
        this.image = paramBitmap;
        this.title = paramString;
    }

    public Bitmap getImage()
    {

        return this.image;
    }

    public String getTitle()
    {

        return title;
    }

    public void setImage(Bitmap paramBitmap)
    {

        image = paramBitmap;
    }

    public void setTitle(String paramString)
    {
        this.title = paramString;
    }
}
