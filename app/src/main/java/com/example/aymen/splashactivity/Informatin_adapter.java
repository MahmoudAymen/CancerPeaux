package com.example.aymen.splashactivity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aymen on 20/03/2017.
 */
public class Informatin_adapter extends ArrayAdapter<ImageItem>{
    private Context context;
    private ArrayList<ImageItem> data = new ArrayList();
    int layoutResourceId;
    public Informatin_adapter(Context paramContext, int paramInt, ArrayList<ImageItem> paramArrayList)
    {
        super(paramContext, paramInt, paramArrayList);
        this.layoutResourceId=paramInt;
        this.context = paramContext;
        this.data = paramArrayList;


    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        View localView = paramView;
        ViewHolder localViewHolder;
        if (localView == null)
        {
            localView = ((Activity)this.context).getLayoutInflater().inflate(layoutResourceId,paramViewGroup,false);

            localViewHolder = new ViewHolder();
            localViewHolder.imageTitle = ((TextView)localView.findViewById(R.id.text1));
            localViewHolder.image = ((ImageView)localView.findViewById(R.id.image16));
            localView.setTag(localViewHolder);

        }
       /* ImageItem imgItem=data.get(paramInt);
        ImageView imageView=(ImageView)localView.findViewById(R.id.image16);
        imageView.setImageBitmap(imgItem.getImage());
        TextView textView=(TextView)localView.findViewById(R.id.text1);
        textView.setText(imgItem.getTitle());
        return localView;*/


        while (true)
        {
            ImageItem localImageItem = (ImageItem)this.data.get(paramInt);
            localViewHolder=(ViewHolder)localView.getTag();
            localViewHolder.imageTitle.setText(localImageItem.getTitle());
            localViewHolder.image.setImageBitmap(localImageItem.getImage());
            return localView;

        }


    }

  static class ViewHolder
    {
        ImageView image;
        TextView imageTitle;
    }

}

