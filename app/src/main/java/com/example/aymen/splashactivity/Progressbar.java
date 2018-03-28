package com.example.aymen.splashactivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by aymen on 20/03/2017.
 */
public class Progressbar extends Dialog {
    private ImageView iv;

    public Progressbar(Context paramContext, int paramInt) {
        super(paramContext,R.style.TransparentProgressDialog);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.gravity = 1;
        getWindow().setAttributes(localLayoutParams);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        LinearLayout localLinearLayout = new LinearLayout(paramContext);
        localLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        this.iv = new ImageView(paramContext);
        this.iv.setImageResource(paramInt);
        localLinearLayout.addView(this.iv, localLayoutParams1);
        addContentView(localLinearLayout, localLayoutParams1);

    }
    public void show() {
        super.show();
        RotateAnimation localRotateAnimation = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
        localRotateAnimation.setInterpolator(new LinearInterpolator());
        localRotateAnimation.setRepeatCount(-1);
        localRotateAnimation.setDuration(3000L);
        this.iv.setAnimation(localRotateAnimation);
        this.iv.startAnimation(localRotateAnimation);
    }
}