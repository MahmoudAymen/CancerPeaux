package com.example.aymen.splashactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MAIN_WIN extends AppCompatActivity {
    protected static final String TAG="";
    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__win);
        btn1= (Button) findViewById(R.id.Button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"ok");
                Intent i=new Intent(getBaseContext(), MED_APP.class);
                startActivity(i);

            }
        });
        btn2= (Button) findViewById(R.id.Button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(getBaseContext(),capture_WIN.class);
                startActivity(i);

            }
        });
        btn3= (Button) findViewById(R.id.Button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), StrActivity.class);
                startActivity(i);
            }
        });
    }
}
