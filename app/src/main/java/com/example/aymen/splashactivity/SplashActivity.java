package com.example.aymen.splashactivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent localIntent = new Intent(SplashActivity.this, MAIN_WIN.class);
                startActivity(localIntent);
                overridePendingTransition(17432576, 17432577);
                finish();

            }
        }
        ,2000L);

    }
    public boolean onCreateOptionsMenu(Menu  paramMen)
    {
        getMenuInflater().inflate(R.menu.web,paramMen);
        return true;
    }
}
