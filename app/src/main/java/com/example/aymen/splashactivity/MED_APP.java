package com.example.aymen.splashactivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.LocaleDisplayNames;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MED_APP extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;
    private static final String TAG = "program started..";
    private Bitmap BitmapResult1;
    private Bitmap BitmapResult2;
    private Bitmap BitmapResult3;
    private Bitmap BitmapResult4;
    private Bitmap BitmapResult5;
    private Bitmap BitmapResult6;
    private Button btn1,btn2,btn3;
    private Informatin_adapter customGridAdapter;
    private Progressbar dialog;
    private Mat endMat;
    private GridView gridView;
    private Bitmap imageBmp;
    private String []imagepath=new  String[4];
    private lesionDetector lesionDetector;
   private BaseLoaderCallback mLoaderCallback =new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                default:
                super.onManagerConnected(status);
                    return;
                case 0:
                    break;

            }
            Log.i(TAG, "OpenCV loaded successfully");
           intIP();

        }
    };
    private ImageView mainFrame;
    private Mat maskMat;
    private Mat maskRGB;
    private double results;
    private double[] s;
    private String selectedImagePath;
    private Bitmap startImage;
    private Mat startMat;
    private Bitmap tempMap;
    private Mat xImage;
    private Mat yImage;
    private void detectLesion()
    {
        Log.d("MED_APP","cv");
        startMat = new Mat();
        Log.d("MED_APP","cv1");
        maskMat = new Mat();
        Log.d("MED_APP","cv2");
        endMat = new Mat();
        maskRGB = new Mat();
        Mat localMat1 = new Mat();
        new Mat();
        Log.i(TAG, "OpenCV 1 ");
        yImage = new Mat();
        this.imageBmp=this.startImage;

            Log.d(TAG, "aymen");
            Utils.bitmapToMat(imageBmp, startMat);
            Log.d(TAG, "aymenmahmoud");
        lesionDetector = new lesionDetector();
        lesionDetector.Detect(startMat);//valide
        Log.i(TAG, "OpenCV 2 ");
        endMat = lesionDetector.getRGB();//valide
        Log.d("MED_APP","rani 93ad n5adem");
        Mat localMat2 = lesionDetector.getCropedMat(endMat);//valide mais un erreur mochlkelkwl
        Log.d("MED_APP","rani 93ad n5adem1");
        BitmapResult1 = Bitmap.createBitmap(localMat2.width(), localMat2.height(), Bitmap.Config.ARGB_8888);
        Log.d("MED_APP", "rani 93ad n5adem2");
        Utils.matToBitmap(localMat2, BitmapResult1);
        Log.d("MED_APP", "rani 93ad n5adem4");
        endMat = lesionDetector.getSegmentationMat();
        Log.d("MED_APP","rani 93ad n5adem5");
        Log.d("MED_APP", "rani 93ad n5adem6");
        lesionDetector.DrawCenter(endMat);
        Log.d("MED_APP", "rani 93ad n5adem8");
        lesionDetector.DrawAxis(endMat);
        Log.d("MED_APP", "rani 93ad n5adem9");
        lesionDetector.DrawContour(endMat);
        Log.d("MED_APP", "rani 93ad n5adem10");
        Mat localMat3 = lesionDetector.getCropedMat(endMat);
        Log.d("MED_APP","rani 93ad n5adem11");
        BitmapResult2 = Bitmap.createBitmap(localMat3.width(), localMat3.height(), Bitmap.Config.ARGB_8888);
        Log.d("MED_APP", "rani 93ad n5adem12");
        Utils.matToBitmap(localMat3, BitmapResult2);
        Log.d("MED_APP", "rani 93ad n5adem13");
        maskMat = lesionDetector.getMaskMat();
        Log.d("MED_APP","rani 93ad n5adem14");
       // maskRGB = lesionDetector.Mask(localMat1, maskMat);
       // Log.d("MED_APP", "rani 93ad n5adem15");
        Core.bitwise_not(lesionDetector.getThresholdMat(), localMat1);
        //paramMat2. convertTo(paramMat2, CvType.CV_8U);
        Log.d("MED_APP", "rani 93ad n5adem15");
        maskRGB = lesionDetector.Mask(localMat1, maskMat);
        Log.d("MED_APP","rani 93ad n5adem16");
        xImage = lesionDetector.rotateMat(maskRGB, 1);
        Log.d("MED_APP","rani 93ad n5adem17");
        yImage = lesionDetector.rotateMat(maskRGB, 2);
        Log.d("MED_APP","rani 93ad n5adem18");
        lesionDetector.GetMeanAndSD(endMat, maskMat);//erreurrrrrrrrrrrrrrrrrrrrrrrrrrr
        Log.d("MED_APP", "rani 93ad n5adem19");
        BitmapResult3 = Bitmap.createBitmap(xImage.width(), xImage.height(), Bitmap.Config.ARGB_8888);
        Log.d("MED_APP","rani 93ad n5adem20");
        Utils.matToBitmap(xImage, BitmapResult3);
        Log.d("MED_APP", "rani 93ad n5adem21");
        BitmapResult4 = Bitmap.createBitmap(yImage.width(), yImage.height(), Bitmap.Config.ARGB_8888);
        Log.d("MED_APP","rani 93ad n5adem22");
        Utils.matToBitmap(this.yImage, this.BitmapResult4);
        Log.d("MED_APP", "rani 93ad n5adem23");
        lesionDetector.findDiameter();
        Log.d("MED_APP", "rani 93ad n5adem24");
        lesionDetector.borderirregularity();
        Log.d("MED_APP", "rani 93ad n5adem25");
        endMat = lesionDetector.getshape2();
        Log.d("MED_APP","rani 93ad n5adem26");
        Mat localMat4 = lesionDetector.getCropedMat(endMat);
        Log.d("MED_APP","rani 93ad n5adem27");
        BitmapResult5 = Bitmap.createBitmap(localMat4.width(), localMat4.height(), Bitmap.Config.ARGB_8888);
        Log.d("MED_APP","rani 93ad n5adem28");
        Utils.matToBitmap(localMat4, BitmapResult5);
        Log.d("MED_APP", "rani 93ad n5adem29");
        endMat = lesionDetector.getshape1();
        Log.d("MED_APP","rani 93ad n5adem30");
        Mat localMat5 = lesionDetector.getCropedMatC(endMat);
        Log.d("MED_APP","rani 93ad n5adem31");
        BitmapResult6 = Bitmap.createBitmap(localMat5.width(), localMat5.height(), Bitmap.Config.ARGB_8888);
        Log.d("MED_APP","rani 93ad n5adem32");
        Utils.matToBitmap(localMat5, BitmapResult6);
        Log.d("MED_APP", "rani 93ad n5adem33");
        s = lesionDetector.getData();
        Log.d("MED_APP","rani 93ad n5adem34");
       results = new classification(getApplicationContext(), s).results();
        Log.d("MED_APP","rani 93ad n5adem35");
        Log.i("clasification result ", "le resulat est:"+results);
        Log.d("MED_APP", "rani 93ad n5adem36");
    }
   private ArrayList<ImageItem> getData()
    {
        ArrayList<ImageItem> localArrayList = new ArrayList<ImageItem>();
        AssetManager localAssetManager = getResources().getAssets();
        try
        {
            String[] arrayOfString = localAssetManager.list("dataset/lesion");
            for (int i = 0; ; i++)
            {
                if (i >= arrayOfString.length)
                    return localArrayList;
                localArrayList.add(new ImageItem(BitmapFactory.decodeStream(localAssetManager.open("dataset/lesion" + "/" + arrayOfString[i])), "Image#" + i));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return localArrayList;
    }
    private void  loadImage()
    {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(i,"select Picture"),1);
    }
    public String getPath(Uri uri)
    {
        Cursor c=managedQuery(uri,new String[]{"_data"},null,null,null);
        int i=c.getColumnIndexOrThrow("_data");
        c.moveToFirst();
        return c.getString(i);
    }
    public void onActivityResult(int p1,int p2,Intent data)
    {
        if((p2==-1)&&(p1==1))
        {
            Uri uri=data.getData();
            selectedImagePath=getPath(uri);
            mainFrame.setImageURI(uri);
            startImage=((BitmapDrawable)mainFrame.getDrawable()).getBitmap();
        }
    }

   private void intIP()
    {
        btn1 = (Button)findViewById(R.id.b1);
        btn2 = ((Button)findViewById(R.id.load));
        btn3 = ((Button)findViewById(R.id.closeAnalysis));
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
              processImage v=new processImage(MED_APP.this);
                v.execute(new Void[0]);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                loadImage();
            }
        });
     btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                MED_APP.this.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med__app);
        mainFrame = ((ImageView)findViewById(R.id.imageView1));
       if (getIntent().hasExtra("image1"))
        {
            byte[] arrayOfByte = getIntent().getByteArrayExtra("image1");
             startImage =BitmapFactory.decodeByteArray(arrayOfByte,0,arrayOfByte.length);
                mainFrame.setImageBitmap(startImage);
                new processImage(MED_APP.this).execute(new Void[0]);

        }
        intIP();

        this.customGridAdapter = new Informatin_adapter(this, R.layout.row_grid, getData());
        runOnUiThread(new Runnable() {
            public void run() {
                MED_APP.this.gridView = ((GridView) MED_APP.this.findViewById(R.id.gridView));
                MED_APP.this.gridView.setAdapter(MED_APP.this.customGridAdapter);
                MED_APP.this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {

                        MED_APP.this.tempMap = (customGridAdapter.getItem(paramInt)).getImage();
                        MED_APP.this.startImage = MED_APP.this.tempMap.copy(MED_APP.this.tempMap.getConfig(), true);
                        MED_APP.this.mainFrame.setImageBitmap(MED_APP.this.tempMap);
                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug()) {
            Log.d("MED_APP", "opencv not Loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, this, this.mLoaderCallback);
        }
        else
        {
            Log.d("MED_APP", "opencv Loaded");

            //mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

    }

    private class processImage extends AsyncTask<Void, Void, String> {
        Context mContext;

        public processImage(Context ctx) {
            this.mContext = ctx;
        }

        protected String doInBackground(Void[] paramArrayOfVoid) {
            try {
                Log.d("MED_APP","doIN1");
                detectLesion();
                Log.d("MED_APP", "doIN2");
            } catch (Exception e) {

                e.printStackTrace();

            }
            return "Bonjour Ms Aymen";
        }


        protected void onPostExecute(String paramString) {
            MED_APP.this.dialog.dismiss();
            Intent localIntent = new Intent(getBaseContext(), Result_WIN.class);
            ByteArrayOutputStream localByteArrayOutputStream1 = new ByteArrayOutputStream();
            BitmapResult1.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream1);
            localIntent.putExtra("image1", localByteArrayOutputStream1.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream2 = new ByteArrayOutputStream();
            BitmapResult2.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream2);
            localIntent.putExtra("image2", localByteArrayOutputStream2.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream3 = new ByteArrayOutputStream();
            BitmapResult3.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream3);
            localIntent.putExtra("image3", localByteArrayOutputStream3.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream4 = new ByteArrayOutputStream();
            BitmapResult4.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream4);
            localIntent.putExtra("image4", localByteArrayOutputStream4.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream5 = new ByteArrayOutputStream();
            BitmapResult5.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream5);
            localIntent.putExtra("image5", localByteArrayOutputStream5.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream6 = new ByteArrayOutputStream();
            MED_APP.this.BitmapResult6.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream6);
            localIntent.putExtra("image6", localByteArrayOutputStream6.toByteArray());
            Log.i("clasification result ", paramString);
            localIntent.putExtra("result", MED_APP.this.results);
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("values", MED_APP.this.s);
            localIntent.putExtra("values", localBundle);
            startActivity(localIntent);
            Toast.makeText(getBaseContext(), paramString, Toast.LENGTH_LONG).show();
        }

        protected void onPreExecute() {
            super.onPreExecute();
            MED_APP.this.dialog = new Progressbar(this.mContext, R.drawable.bar);
            MED_APP.this.dialog.setCancelable(false);
            MED_APP.this.dialog.show();
        }

    }

}
