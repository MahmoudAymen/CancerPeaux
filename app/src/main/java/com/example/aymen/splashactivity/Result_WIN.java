package com.example.aymen.splashactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result_WIN extends AppCompatActivity {
    protected static final String TAG = "Results";
    private Bitmap BitmapResults1;
    private Bitmap BitmapResults2;
    private Bitmap BitmapResults3;
    private Bitmap BitmapResults4;
    private Bitmap BitmapResults5;
    private Bitmap BitmapResults6;
    private ImageView ColorV;
    private Button btn;
    private double result;
    private double[] value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__win);
        btn= (Button) findViewById(R.id.closeAnalysis);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("Results", "Start Thread");
                ImageView localImageView1 = (ImageView)findViewById(R.id.i);
                ImageView localImageView2 = (ImageView)findViewById(R.id.imageView2);
                ImageView localImageView3 = (ImageView)findViewById(R.id.imageView3);
                ImageView localImageView4 = (ImageView)findViewById(R.id.imageView4);
                ImageView localImageView5 = (ImageView)findViewById(R.id.imageView5);
                ImageView localImageView6 = (ImageView)findViewById(R.id.imageView6);
                ImageView localImageView7 = (ImageView)findViewById(R.id.imageView10);
                TextView localTextView1 = (TextView)findViewById(R.id.textView7);
                TextView localTextView2 = (TextView)findViewById(R.id.textView9);
                byte[] arrayOfByte1 = Result_WIN.this.getIntent().getByteArrayExtra("image1");
                Result_WIN.this.BitmapResults1 = BitmapFactory.decodeByteArray(arrayOfByte1, 0, arrayOfByte1.length);
               byte[] arrayOfByte2 = Result_WIN.this.getIntent().getByteArrayExtra("image2");
                Result_WIN.this.BitmapResults2 = BitmapFactory.decodeByteArray(arrayOfByte2, 0, arrayOfByte2.length);
                byte[] arrayOfByte3 = Result_WIN.this.getIntent().getByteArrayExtra("image3");
                Result_WIN.this.BitmapResults3 = BitmapFactory.decodeByteArray(arrayOfByte3, 0, arrayOfByte3.length);
                byte[] arrayOfByte4 = Result_WIN.this.getIntent().getByteArrayExtra("image4");
                Result_WIN.this.BitmapResults4 = BitmapFactory.decodeByteArray(arrayOfByte4, 0, arrayOfByte4.length);
                byte[] arrayOfByte5 = Result_WIN.this.getIntent().getByteArrayExtra("image5");
                Result_WIN.this.BitmapResults5 = BitmapFactory.decodeByteArray(arrayOfByte5, 0, arrayOfByte5.length);
                byte[] arrayOfByte6 = Result_WIN.this.getIntent().getByteArrayExtra("image6");
                Result_WIN.this.BitmapResults6 = BitmapFactory.decodeByteArray(arrayOfByte6, 0, arrayOfByte6.length);
                Bundle localBundle = Result_WIN.this.getIntent().getExtras();
                if (localBundle != null)
                    Result_WIN.this.value = ((double[])localBundle.getSerializable("value"));
                double d = Result_WIN.this.getIntent().getDoubleExtra("result", 0.0D);
                Log.i("Reults Value  ", "valeur de est:"+d);
                if (d == 0.0D)
                {
                    localTextView1.setText("Melanome");
                    localTextView2.setText("The results showed strong indications of Melanoma, We recommended to ask your doctor for more details, ask your doctor to get more accurate results`");
                }
                Log.d("Result_WIN","rani t3adit à laymen");
                while (true)
                {
                    Log.d("Result_WIN","rani t3adit à laymen1");

                    localImageView1.setImageBitmap(Result_WIN.this.BitmapResults1);
                    Log.d("Result_WIN", "rani t3adit à laymen2");
                    localImageView2.setImageBitmap(Result_WIN.this.BitmapResults2);
                    Log.d("Result_WIN", "rani t3adit à laymen3");
                    localImageView3.setImageBitmap(Result_WIN.this.BitmapResults3);
                    Log.d("Result_WIN", "rani t3adit à laymen4");
                    localImageView4.setImageBitmap(Result_WIN.this.BitmapResults4);
                    Log.d("Result_WIN", "rani t3adit à laymen5");
                    localImageView5.setImageBitmap(Result_WIN.this.BitmapResults5);
                    Log.d("Result_WIN", "rani t3adit à laymen6");
                    localImageView6.setImageBitmap(Result_WIN.this.BitmapResults6);
                    Log.d("Result_WIN", "rani t3adit à laymen6");
                    localImageView7.setImageBitmap(Result_WIN.this.BitmapResults1);
                    Log.d("Result_WIN", "rani t3adit à laymen7");
                    Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
                    Log.d("Result_WIN","rani t3adit à laymen8");
                    Bitmap localBitmap1 = Bitmap.createBitmap(300, 70, localConfig);
                    Log.d("Result_WIN","rani t3adit à laymen9");
                    Result_WIN.this.ColorV = ((ImageView)Result_WIN.this.findViewById(R.id.imageView7));
                    Log.d("Result_WIN","rani t3adit à laymen10");
                    Result_WIN.this.ColorV.setImageBitmap(drawS.setRGBcolors(20, 20, localBitmap1, 200, 100, 250));
                    Log.d("Result_WIN", "rani t3adit à laymen11");
                    Bitmap localBitmap2 = Bitmap.createBitmap(500, 120, localConfig);
                    Log.d("Result_WIN","rani t3adit à laymen12");
                    Result_WIN.this.ColorV = ((ImageView)Result_WIN.this.findViewById(R.id.imageView8));
                    Log.d("Result_WIN","rani t3adit à laymen13");
                    Result_WIN.this.ColorV.setImageBitmap(drawS.setScolor(20, 20, localBitmap2, 200, 300));
                    Log.d("Result_WIN", "rani t3adit à laymen14");
                    Bitmap localBitmap3 = Bitmap.createBitmap(500, 120, localConfig);
                    Log.d("Result_WIN","rani t3adit à laymen15");
                    Result_WIN.this.ColorV = ((ImageView)Result_WIN.this.findViewById(R.id.imageView9));
                    Log.d("Result_WIN","rani t3adit à laymen16");
                    Result_WIN.this.ColorV.setImageBitmap(drawS.setScolor(20, 20, localBitmap3, 400, 300));
                    Log.d("Result_WIN", "rani t3adit à laymen17");
                    Date localDate = new Date();
                    Log.d("Result_WIN","rani t3adit à laymen18");
                    String str = new SimpleDateFormat("yyyy-MM-dd").format(localDate);
                    Log.d("Result_WIN","rani t3adit à laymen19");
                    ((TextView)Result_WIN.this.findViewById(R.id.textView10)).setText(str);
                    Log.d("Result_WIN", "rani t3adit à laymen20");
                    if (d == 1.0D) {
                        Log.d("Result_WIN","rani t3adit à laymen21");
                        continue;
                    }
                        localTextView1.setText("Benign");
                        localTextView2.setText("Results showed signs of a benign 'normal' lesion, and this recommendation does not replace your doctor, ask your doctor to get more accurate results");

                   // localTextView1.setText("Benign");
                   // localTextView2.setText("Results showed signs of a benign 'normal' lesion, and this recommendation does not replace your doctor, ask your doctor to get more accurate results");
                    break;
                }

            }
        });
    }
}
