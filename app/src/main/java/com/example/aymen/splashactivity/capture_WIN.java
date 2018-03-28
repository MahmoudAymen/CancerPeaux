package com.example.aymen.splashactivity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;


public class capture_WIN extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener,View.OnTouchListener{
    private static final String TAG = "Capture_Win";
    private Bitmap BitmapResult1;
    private Scalar CONTOUR_COLOR = new Scalar(255.0D, 255.0D, 255.0D, 255.0D);
    private List<Mat> HSLlsit;
    private double M00;
    private double M01;
    private double M10;
    private Button but1;
    public Camera camera;
    private Mat capture;
   // private Scalar colorB = new Scalar(0.0D, 0.0D, 0.0D, 0.0D);
    //private Scalar colorC = new Scalar(100.0D, 50.0D, 50.0D, 2.0D);
    //private Scalar colorR = new Scalar(0.0D, 0.0D, 100.0D, 100.0D);
    private Scalar colorW = new Scalar(255.0D, 255.0D, 255.0D, 255.0D);
    private Mat contourMat;
    private List<MatOfPoint> contours;
    private lesionDetector lesionDetector;
   private Scalar mBlobColorHsv;
    private Camera mCamera;
    private Mat mHierarchy;
    private CameraBridgeViewBase mOpenCvCameraView;
    private Camera.Parameters mParameters;
    private Mat mRgba;
    private Scalar meanColor;
    private Moments moments;
    private double mu02;
    private double mu11;
    private double mu20;
    private double posX;
    private double posY;
    private Mat resizeM;
    private Mat resultsMat;
    private Mat submat;
    private Bitmap txt1;
    private Mat txt1Mat;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)
    {
        public void onManagerConnected(int paramInt)
        {
            switch (paramInt)
            {
                default:
                    super.onManagerConnected(paramInt);
                    return;
                case 0:


            }
            Log.d("OCVSample::Activity", "OpenCV loaded successfully");
           capture_WIN.this.mOpenCvCameraView.enableView();
            capture_WIN.this.mOpenCvCameraView.setOnTouchListener(capture_WIN.this);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(1);
       // getWindow().addFlags(128);
        setContentView(R.layout.activity_capture__win);
        this.mOpenCvCameraView = (CameraBridgeViewBase)findViewById(R.id.surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        this.mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.mOpenCvCameraView != null) {
            this.mOpenCvCameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mOpenCvCameraView != null) {
            this.mOpenCvCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug())
        {
            Log.d(TAG, "opencv not Loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11,this,mLoaderCallback);

        }
        else
        {
            Log.d(TAG, "opencv Loaded");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onCameraViewStarted(int paramInt1, int paramInt2) {
        Log.d(TAG, "op1");
        this.mRgba = new Mat(paramInt2, paramInt1, CvType.CV_8UC4);
        Log.d(TAG, "op2");
        this.mHierarchy = new Mat();
        Log.d(TAG, "op3");
        this.mBlobColorHsv = new Scalar(255.0D);
        Log.d(TAG, "op4");
        this.capture = new Mat();
        Log.d(TAG, "op5");
        this.txt1 = BitmapFactory.decodeResource(getResources(), R.drawable.txt1);
        Log.d(TAG, "op6");
        this.txt1Mat = new Mat();
        Log.d(TAG, "op7");
        Utils.bitmapToMat(this.txt1, this.txt1Mat);
        Log.d(TAG, "op8");
    }

    @Override
    public void onCameraViewStopped() {
        this.mRgba.release();
    }

    @Override
    public Mat onCameraFrame(Mat paramMat) {
        this.mRgba = paramMat;
        this.resultsMat = null;
        Mat localMat1 = new Mat();
        new Mat();
        new Scalar(0.0D, 0.0D, 0.0D, 0.0D);
        new Mat();
        Rect localRect1 = new Rect(paramMat.width() / 2 - 100, paramMat.height() / 2 - 100, 200, 200);
        Mat localMat2 = this.mRgba.submat(localRect1);
        Imgproc.cvtColor(localMat2, localMat1, 1);
        Imgproc.cvtColor(localMat1, localMat1, 7);
        Imgproc.adaptiveThreshold(localMat1, localMat1, 255.0D, 0, 0, 13, 4.0D);
        Core.bitwise_not(localMat1, localMat1);
        this.contours = new ArrayList();
        Imgproc.findContours(localMat1, this.contours, this.mHierarchy, 1, 1);
        int i = 0;
        Rect localRect2 = new Rect();
        new Rect();
        int j = 0;
        double d1 = 0.0D;
        double d2 = 0.0D;
        this.contourMat = new Mat(localMat1.size(), CvType.CV_8UC3, new Scalar(0.0D, 0.0D, 0.0D));
        for (int k = 0; ; k++)
        {
            int m = this.contours.size();
            if (k >= m)
            {
                if (j != 0)
                {
                    Core.mean(localMat2.submat(localRect2).clone());
                    Core.rectangle(localMat2, localRect2.tl(), localRect2.br(), this.colorW);
                    Core.circle(localMat2, new Point(d1, d2), 1, this.colorW);
                }
                Core.rectangle(this.mRgba, new Point(-100 + paramMat.width() / 2, -100 + paramMat.height() / 2), new Point(100 + paramMat.width() / 2, 100 + paramMat.height() / 2), this.colorW);
                Core.circle(this.mRgba, new Point(paramMat.width() / 2, paramMat.height() / 2), 40, this.colorW, 1);
                int i6 = this.mRgba.height() / 2 - this.txt1Mat.height() / 2;
                Point localPoint1 = new Point(70, i6);
                Rect localRect4 = new Rect(localPoint1, new Size(this.txt1Mat.width(), this.txt1Mat.height()));
                this.txt1Mat.copyTo(this.mRgba.submat(localRect4));
                if (this.resultsMat != null)
                {
                    Imgproc.resize(this.resultsMat, this.resultsMat, new Size(150.0D, 150.0D));
                    this.lesionDetector = new lesionDetector();
                    int i7 = 70 + '';
                    int i8 = i6 + 30;
                    Point localPoint2 = new Point(i7, i8);
                    Point localPoint3 = new Point(365, i8 + 150);
                    Rect localRect5 = new Rect(localPoint2, localPoint3);
                    this.resultsMat.copyTo(this.mRgba.submat(localRect5));
                    Core.rectangle(this.mRgba, localPoint2, localPoint3, this.colorW);
                    new Point(45, i8 + 160);
                    new Point(105, 60 + (i8 + 160));
                }
                return this.mRgba;
            }
            double d3 = Imgproc.contourArea((Mat)this.contours.get(k));
            this.moments = Imgproc.moments((Mat)this.contours.get(k));
            this.M00 = this.moments.get_m00();
            this.M10 = this.moments.get_m10();
            this.M01 = this.moments.get_m01();
            this.posX = (int)(this.M10 / this.M00);
            this.posY = (int)(this.M01 / this.M00);
            int n = (int)Math.sqrt(Math.pow(this.posX - 100.0D, 2.0D) + Math.pow(this.posY - 100.0D, 2.0D));
            if ((d3 <= i) || (n >= 20) || (d3 <= 70.0D))
                continue;
            i = (int)d3;
            int i1 = k;
            localRect2 = Imgproc.boundingRect((MatOfPoint)this.contours.get(i1));
            int i2 = (int)Math.max(localRect2.tl().x - 20, 1.0D);
            int i3 = (int)Math.max(localRect2.tl().y - 20, 1.0D);
            int i4 = (int)Math.min(localRect2.br().x + 20, -1 + localMat2.width());
            int i5 = (int)Math.min(localRect2.br().y + 20, -1 + localMat2.height());
            Rect localRect3 = new Rect(new Point(i2, i3), new Point(i4, i5));
            this.resultsMat = localMat2.submat(localRect3).clone();
            j = 1;
            d1 = this.posX;
            d2 = this.posY;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG,"rani bdit n5adem");
        if (this.resultsMat != null)
        {
            Log.d(TAG,"rani bdit n5adem1");
            Intent localIntent = new Intent(getBaseContext(), MED_APP.class);
            Log.d(TAG,"rani bdit n5adem2");
            localIntent.setFlags(67108864);
            Log.d(TAG, "rani bdit n5adem3");
            Mat localMat = new Mat(new Size(300.0D, 300.0D), this.resultsMat.type());
            Log.d(TAG,"rani bdit n5adem4");
            Imgproc.resize(this.resultsMat, localMat, localMat.size());
            Log.d(TAG, "rani bdit n5adem5");
            this.BitmapResult1 = Bitmap.createBitmap(localMat.width(), localMat.height(), Bitmap.Config.ARGB_8888);
            Log.d(TAG,"rani bdit n5adem6");
            Utils.matToBitmap(localMat, this.BitmapResult1);
            Log.d(TAG, "rani bdit n5adem7");
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            Log.d(TAG,"rani bdit n5adem8");
            this.BitmapResult1.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream);
            Log.d(TAG, "rani bdit n5adem9");
            localIntent.putExtra("image1", localByteArrayOutputStream.toByteArray());
            Log.d(TAG, "rani bdit n5adem10");
            startActivity(localIntent);
            Log.d(TAG, "rani bdit n5adem11");
        }

        return false;
    }
}
