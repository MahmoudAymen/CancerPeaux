package com.example.aymen.splashactivity;

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aymen on 20/03/2017.
 */
public class lesionDetector {
    private static final String TAG = "lesion";
    private Scalar CONTOUR_COLOR;
    private double Eccentricity;
    private List<Mat> HSLlsit;
    private double M00;
    private double M01;
    private double M10;
    private Mat MSegm;
    private Mat Mask;
    private Mat Mclose;
    private Scalar MeanColor;
    private Mat Mthreshold;
    private double ROIx1;
    private double ROIx2;
    private double ROIy1;
    private double ROIy2;
    private double Roundness;
    private Scalar axisColor;
    private Scalar centerColor;
    private Mat contourMat;
    private double contourarea;
    private List<MatOfPoint> contours;
    private Mat cropedContour;
    private Mat kernel;
    private Mat mBlur;
    private Mat mGray;
    private Mat mHSL;
    private Mat mHierarchy;
    private Mat mRgba;
    private Mat mSatu;
    private int maxArea;
    private int maxAreaIdx;
    private int maxAreaIdx1 = 0;
    private int maxAreaIdx2 = 0;
    private Moments moments;
    private double mu02;
    private double mu11;
    private double mu20;
    private double perimeter;
    private double posX;
    private double posY;
    private Mat resizeM;
    private double[] s = new double[12];
    private Mat shape1;
    private Mat shape2;
    private double thetaD;
    private double thetaR;
    public lesionDetector()
    {

        inti();
    }
    private Rect ROI()
    {
        Rect localRect = Imgproc.boundingRect( this.contours.get(this.maxAreaIdx));
        this.ROIx1 = (int)Math.max(localRect.tl().x - 20, 2.0D);
        this.ROIy1 = (int)Math.max(localRect.tl().y - 20, 2.0D);
        this.ROIx2 = (int)Math.min(localRect.br().x + 20, -2 + this.mRgba.width());
        this.ROIy2 = (int)Math.min(localRect.br().y + 20, -2 + this.mRgba.height());
        return new Rect(new Point(this.ROIx1, this.ROIy1), new Point(this.ROIx2, this.ROIy2));
    }
    private void findConvexHull(List<MatOfPoint> paramList, int paramInt)
    {
        MatOfPoint localMatOfPoint1 = (MatOfPoint)paramList.get(paramInt);
        MatOfInt localMatOfInt = new MatOfInt();
        Imgproc.convexHull(localMatOfPoint1, localMatOfInt, false);
        MatOfPoint localMatOfPoint2 = new MatOfPoint();
        localMatOfPoint2.create((int)localMatOfInt.size().height, 1, CvType.CV_32SC2);
        for (int i = 0; ; i++)
        {
            if (i >= localMatOfInt.size().height)
            {
                ArrayList localArrayList = new ArrayList();
                localArrayList.add(localMatOfPoint2);
                Imgproc.drawContours(this.shape2, localArrayList, 0, new Scalar(255.0D, 220.0D, 255.0D, 255.0D), 2);
                double d1 = Imgproc.arcLength(new MatOfPoint2f(((MatOfPoint)this.contours.get(this.maxAreaIdx)).toArray()), true);
                double d2 = Imgproc.arcLength(new MatOfPoint2f(localMatOfPoint2.toArray()), true);
                this.Roundness = (12.566370614359172D * this.M00 / (d1 * d1));
                Log.i("lesion", "HCR=" + d2 / d1);
                this.s[8] = (d2 / d1);
                Log.i("lesion", "Roundness=" + this.Roundness);
                this.s[9] = this.Roundness;
                MatOfPoint2f localMatOfPoint2f = new MatOfPoint2f((this.contours.get(this.maxAreaIdx)).toArray());
                float[] arrayOfFloat = new float[1];
                Imgproc.minEnclosingCircle(localMatOfPoint2f, new Point(), arrayOfFloat);
                double d3 = d1 / (2.0F * arrayOfFloat[0]);
                this.s[11] = d3;
                Log.i("lesion", "irregularity=" + d3);
                return;
            }
            int j = (int)localMatOfInt.get(i, 0)[0];
            double[] arrayOfDouble = new double[2];
            arrayOfDouble[0] = localMatOfPoint1.get(j, 0)[0];
            arrayOfDouble[1] = localMatOfPoint1.get(j, 0)[1];
            localMatOfPoint2.put(i, 0, arrayOfDouble);
        }
    }
    private Mat getXSemetry(Mat paramMat) {
        Log.d("lesion", "rani t3adit l X");
        double d1 = 0.0D;
        double d2 = 0.0D;
        Log.d("lesion", "rani t3adit 2 X");
        Imgproc.cvtColor(paramMat, paramMat, Imgproc.COLOR_BGRA2GRAY);
        Log.d("lesion", "rani t3adit 3 X");
        Mat localMat = new Mat(paramMat.size(), paramMat.type(), new Scalar(255.0D, 255.0D, 255.0D, 255.0D));
        Log.d("lesion", "rani t3adit 4 X");
        double[] arrayOfDouble1 = {125.0D, 82.0D, 55.0D};
        Log.d("lesion", "rani t3adit 5 X");
        double[] arrayOfDouble2 = {238.0D, 217.0D, 201.0D};
        Log.d("lesion", "rani t3adit 6 X");
        int k;
        int i;
        Log.d("lesion", "rani t3adit 7 X");
        for (i = 0; ; i++) {
            Log.d("lesion", "rani t3adit 8 X");
            int j = paramMat.height();
            Log.d("lesion", "rani t3adit 9 X");
            if (i >= j) {
                Log.d("lesion", "rani t3adit 10 X");
                Log.i("lesion", "X Semetry percent =" + d2 / d1);
                Log.d("lesion", "rani t3adit ll X");
                this.s[6] = (d2 / d1);
                Log.d("lesion", "rani t3adit 12 X");
                return localMat;
            }
            k = 0;
            int m = paramMat.width();
            Log.d("lesion", "rani t3adit 13 X");
            if (k < m)
                break;
        }
        double[] arrayOfDouble3 = paramMat.get(i, k);
        Log.d("lesion", "rani t3adit 14 X");
        double[] arrayOfDouble4 = paramMat.get(-1 + (paramMat.height() - i), k);
        Log.d("lesion", "rani t3adit l5 X");
        if ((arrayOfDouble3[0] == arrayOfDouble4[0]) && (arrayOfDouble3[0] == 255.0D)) {
            Log.d("lesion", "rani t3adit l6 X");
            localMat.put(i, k, arrayOfDouble2);
            Log.d("lesion", "rani t3adit l7 X");
            d1 += 1.0D;
            Log.d("lesion", "rani t3adit l8 X");
        }
        Log.d("lesion", "rani t3adit l9 X");
      while(true)
      {

          Log.d("lesion", "rani t3adit 20 X");
          k++;
          if (arrayOfDouble3[0] != arrayOfDouble4[0]);
          Log.d("lesion", "rani t3adit 21 X");
          localMat.put(i, k, arrayOfDouble1);
          Log.d("lesion", "rani t3adit 22 X");
          d2 += 1.0D;
          break;

      }
        return localMat;
    }
    private  Mat getYSemetry(Mat paramMat)
    {
        Log.d("lesion","rani d5alet");
        double d1 = 0.0D;
        Log.d("lesion","rani d5alet1");
        double d2 = 0.0D;
        Log.d("lesion","rani d5alet2");
        Imgproc.cvtColor(paramMat, paramMat, Imgproc.COLOR_BGRA2GRAY);
        Log.d("lesion", "rani d5alet3");
        Mat localMat = new Mat(paramMat.size(), paramMat.type(), new Scalar(255.0D, 255.0D, 255.0D, 255.0D));
        Log.d("lesion","rani d5alet4");
        double[] arrayOfDouble1 = { 125.0D, 82.0D, 55.0D };
        Log.d("lesion","rani d5alet5");
        double[] arrayOfDouble2 = { 238.0D, 217.0D, 201.0D };
        Log.d("lesion","rani d5alet6");
        int k;
        int i;
        Log.d("lesion","rani d5alet7");
        for ( i = 0; ; i++)
        {
            Log.d("lesion","rani d5alet8");
            int j = paramMat.height();
            Log.d("lesion","rani d5alet9");
            if (i >= j)
            {
                Log.d("lesion", "rani d5alet10");
                Log.i("lesion", "Y Semetry percent =" + d2 / d1);
                Log.d("lesion", "rani d5alet11");
                this.s[7] = (d2 / d1);
                Log.d("lesion","rani d5alet12");
                return localMat;

            }
            Log.d("lesion","rani d5alet13");
            k = 0;
            Log.d("lesion","rani d5alet14");
            int m = paramMat.width();
            Log.d("lesion","rani d5alet15");
            if (k < m)
                break;
        }
        Log.d("lesion","rani d5alet16");
        double[] arrayOfDouble3 = paramMat.get(i, k);
        Log.d("lesion","rani d5alet17");
        int n = -1 + (paramMat.width() - k);
        Log.d("lesion","rani d5alet18");
        double[] arrayOfDouble4 = paramMat.get(i, n);
        Log.d("lesion","rani d5alet19");
        if ((arrayOfDouble3[0] == arrayOfDouble4[0]) && (arrayOfDouble3[0] == 255.0D))
        {
            Log.d("lesion","rani d5alet20");
            localMat.put(i, k, arrayOfDouble2);
            Log.d("lesion", "rani d5alet21");
            d1 += 1.0D;
        }
        /*try {
            Log.d("lesion", "rani d5alet23");
            k++;
            if (arrayOfDouble3[0] == arrayOfDouble4[0])
                Log.d("lesion", "rani d5alet24");
            localMat.put(i, k, arrayOfDouble1);
            Log.d("lesion", "rani d5alet25");
            d2 += 1.0D;
            Log.d("lesion", "rani d5alet26");
        }
        catch(Exception e)
        {
            Log.d("lesion", " mes est:"+e.getMessage()+"cause est:"+e.getCause());
        }
        return localMat;*/
        Log.d("lesion", "rani d5alet22");
        while (true)
        {
            Log.d("lesion", "rani d5alet23");
            k++;
            if (arrayOfDouble3[0] != arrayOfDouble4[0]);
            Log.d("lesion", "rani d5alet24");
            localMat.put(i, k, arrayOfDouble1);
            Log.d("lesion", "rani d5alet25");
            d2 += 1.0D;
            break;

        }
       return localMat;
    }




    private void inti()
    {
        this.CONTOUR_COLOR = new Scalar(255.0D, 255.0D, 255.0D);
        this.centerColor = new Scalar(255.0D, 235.0D, 0.0D);
        this.axisColor = new Scalar(255.0D, 235.0D, 255.0D);
        this.mGray = new Mat();
        this.mHSL = new Mat();
        this.Mask = new Mat();
        this.resizeM = new Mat();
        this.mHierarchy = new Mat();
        this.HSLlsit = new ArrayList();
        this.MSegm = new Mat();
        this.mSatu = new Mat();
        this.mBlur = new Mat();
        this.Mthreshold = new Mat();
        this.Mclose = new Mat();
        this.moments = new Moments();
        this.shape1 = new Mat();
        this.shape2 = new Mat();
    }
    private Boolean symmetryx(double paramDouble1, double paramDouble2, Mat paramMat, Point paramPoint)
    {
        int i = (int)paramPoint.x;
        int j = (int)paramPoint.y;
        double[] arrayOfDouble1 = paramMat.get(j, i);
        double[] arrayOfDouble2 = paramMat.get(j, -1 + (paramMat.width() - i));
        if (arrayOfDouble1[0] == arrayOfDouble2[0])
             return true;
             return false;
    }
    public void Detect(Mat paramMat)
    {
        Log.i("lesion", "start IP 1");
        this.mRgba = paramMat;
        this.resizeM = new Mat(this.mRgba.width(), this.mRgba.height(), this.mRgba.type());
        Imgproc.resize(this.mRgba, this.resizeM, this.resizeM.size(), 0.0D, 0.0D, 2);
        this.mRgba.copyTo(this.shape1);
        this.mRgba.copyTo(this.shape2);
        Imgproc.cvtColor(this.resizeM, this.resizeM, 1);
        Imgproc.medianBlur(this.resizeM, this.mBlur, 5);
        Imgproc.pyrMeanShiftFiltering(this.mBlur, this.MSegm, 20.0D, 30.0D);
        Imgproc.cvtColor(this.MSegm, this.mHSL, 3);
        Core.split(this.mHSL, this.HSLlsit);
        this.HSLlsit.get(1).convertTo(this.mGray, CvType.CV_8UC1);
        Imgproc.threshold(this.mGray, this.Mthreshold, 0.0D, 255.0D, 8);
        this.kernel = Imgproc.getStructuringElement(2, new Size(5.0D, 5.0D));
        Imgproc.morphologyEx(this.Mthreshold, this.Mclose, 3, this.kernel, new Point(1.0D, 1.0D), 1);
        Core.bitwise_not(this.Mclose, this.Mclose);
        this.contours = new ArrayList<MatOfPoint>();
        this.contourMat = new Mat(new Size(1 + this.Mclose.width(), 1 + this.Mclose.height()), CvType.CV_8UC3, new Scalar(0.0D, 0.0D, 0.0D));
        Imgproc.findContours(this.Mclose, this.contours, this.mHierarchy, 1, 1);
        Log.i("lesion", "start IP 2");
        this.maxArea = 0;
        this.maxAreaIdx1 = 0;
        this.maxAreaIdx2 = 0;
        int i = 0;
        int j=0;
        if (i >= this.contours.size())
        {
            j = 2 * (contourMat.width() * contourMat.height()) / 3;
            if ((this.maxArea < j) && (this.maxArea >= 10))
                return;

        }
         for (maxAreaIdx = maxAreaIdx2; ; maxAreaIdx = maxAreaIdx1)
        {
            if (this.maxAreaIdx >= j)
                Log.i("lesion", "Failed ......");
            Log.i("lesion", "start IP 3");
            Imgproc.drawContours(this.contourMat, this.contours, this.maxAreaIdx, this.CONTOUR_COLOR, 1);
            Imgproc.resize(this.contourMat, this.contourMat, this.mRgba.size());
            Mat localMat = new Mat(2 + this.contourMat.rows(), 2 + this.contourMat.cols(), 0, new Scalar(0.0D, 0.0D, 0.0D));
            this.Mask = this.contourMat.clone();
            Imgproc.floodFill(this.Mask, localMat, new Point(0.0D, 0.0D), new Scalar(255.0D, 255.0D, 255.0D));
            Core.bitwise_not(this.Mask, this.Mask);
            Imgproc.cvtColor(this.Mask, this.Mask, 3);
            Log.i("lesion", "start IP 4");
            this.moments = Imgproc.moments((Mat) this.contours.get(this.maxAreaIdx));
            this.M00 = this.moments.get_m00();
            this.M10 = this.moments.get_m10();
            this.M01 = this.moments.get_m01();
            this.posX = (int)(this.M10 / this.M00);
            this.posY = (int)(this.M01 / this.M00);
            this.mu20 = this.moments.get_mu20();
            this.mu02 = this.moments.get_mu02();
            this.mu11 = this.moments.get_mu11();
            this.thetaR = (0.5D * Math.atan(2.0D * this.mu11 / (this.mu20 - this.mu02)));
            this.thetaD = Math.toDegrees(this.thetaR);
            Log.i("lesion", "start IP 5");
            this.contourarea = Imgproc.contourArea((Mat)this.contours.get(i));
            if (this.contourarea > this.maxArea)
            {
                this.maxArea = (int)this.contourarea;
                this.maxAreaIdx2 = this.maxAreaIdx1;
                this.maxAreaIdx1 = i;
            }
            i++;
            break;
        }
    }
    public void DrawAxis(Mat paramMat)
    {
        Core.line(paramMat, new Point(this.posX, this.posY), new Point(this.posX + 130.0D * Math.cos(this.thetaR), this.posY + 130.0D * Math.sin(this.thetaR)), this.axisColor, 1);
        Core.line(paramMat, new Point(this.posX, this.posY), new Point(this.posX - 130.0D * Math.cos(this.thetaR), this.posY - 130.0D * Math.sin(this.thetaR)), this.axisColor, 1);
        this.thetaR = Math.toRadians(90.0D + this.thetaD);
        Core.line(paramMat, new Point(this.posX, this.posY), new Point(this.posX - 130.0D * Math.cos(this.thetaR), this.posY - 130.0D * Math.sin(this.thetaR)), this.axisColor, 1);
        this.thetaR = Math.toRadians(this.thetaD - 90.0D);
        Core.line(paramMat, new Point(this.posX, this.posY), new Point(this.posX - 130.0D * Math.cos(this.thetaR), this.posY - 130.0D * Math.sin(this.thetaR)), this.axisColor, 1);
    }
    public void DrawCenter(Mat paramMat)
    {
        Core.circle(paramMat, new Point(this.posX, this.posY), 2, this.centerColor, 2, 16, 0);
    }
    public void DrawContour(Mat paramMat)
    {
        Imgproc.drawContours(paramMat, this.contours, this.maxAreaIdx, new Scalar(255.0D, 255.0D, 255.0D, 255.0D), 2);
    }
    public void GetMeanAndSD(Mat paramMat1, Mat paramMat2)
    {
        Log.d("lesion","mataym1");
       paramMat2. convertTo(paramMat2, CvType.CV_8U);
        Log.d("lesion", "mataym2");
       this.MeanColor = Core.mean( paramMat1);
        Log.d("lesion","mataym3");
        MatOfDouble localMatOfDouble1 = new MatOfDouble();
        Log.d("lesion","mataym4");
        MatOfDouble localMatOfDouble2 = new MatOfDouble();
        Log.d("lesion","mataym5");
        Core.meanStdDev(this.resizeM, localMatOfDouble2, localMatOfDouble1);
        Log.d("lesion", "mataym6");
        int i = 0;
        Log.d("lesion","mataym7");
        if (i >= localMatOfDouble1.width());
        Log.d("lesion","mataym8");
        int n=0;
        int m;

        for ( m = 0; ; m++)
        {
            Log.d("lesion","mataym9");
            if (m >= localMatOfDouble2.width())
            {
                Log.d("lesion","mataym10");

                int j=0;
                if ( j >= localMatOfDouble1.height())
                {
                    Log.d("lesion","mataym11");
                    i++;
                    break;

                }
                Log.d("lesion","mataym12");
                double[] arrayOfDouble1 = localMatOfDouble1.get(j, i);
                Log.d("lesion","mataym13");
                for (int k = 0; ; k++)
                {
                    Log.d("lesion","mataym14");
                    if (k >= arrayOfDouble1.length)
                    {
                        Log.d("lesion","mataym15");
                        j++;
                        break;
                    }
                    Log.d("lesion","mataym16");
                    this.s[(j + 3)] = arrayOfDouble1[k];
                    Log.d("lesion","mataym17");
                    Log.i("lesion", "stddev " + k + "= " + arrayOfDouble1[k]);
                    Log.d("lesion", "mataym18");
                }
            }
            if (n < localMatOfDouble2.height())
                break ;
        }
        Log.d("lesion","mataym19");
         double[] arrayOfDouble2 = localMatOfDouble2.get(n, m);
        Log.d("lesion","mataym20");
        for (int i1 = 0; ; i1++)
        {
            Log.d("lesion","mataym21");
            if (i1 >= arrayOfDouble2.length)
            {
                n++;
                break;
            }
            Log.i("lesion", "mean " + i1 + "= " + arrayOfDouble2[i1]);
            this.s[n] = arrayOfDouble2[i1];
        }
    }

    public Mat Mask(Mat paramMat1, Mat paramMat2)
    {
        Log.d("lesion","asba1");
        Imgproc.resize(paramMat2, paramMat2,paramMat1.size());
        Log.d("lesion", "asba2");
        double[] arrayOfDouble = { 0.0D, 0.0D, 0.0D, 0.0D };
        Log.d("lesion","asba3");
        int i = 0;
        Log.d("lesion","asba4");
        if (i >= paramMat1.rows()) {
            Log.d("lesion","asba5");
            return paramMat1;
        }

        for (int j = 0; ; j++)
        {
            Log.d("lesion","asba6");
            if (j >= paramMat1.cols())
            {
                Log.d("lesion","asba7");
                i++;
                break;
            }
            if (paramMat2.get(i, j)[0] == 0.0D) {
                Log.d("lesion","asba8");
               // continue;
            }
            Log.d("lesion","asba9");
            paramMat1.put(i, j, arrayOfDouble);
            Log.d("lesion", "asba10");
        }
        Log.d("lesion","asba11");
        return paramMat2;
    }

    public Mat SubMat(Mat paramMat)
    {
        Rect localRect = ROI();
        this.ROIx1 = (int)Math.max(localRect.tl().x, 2.0D);
        this.ROIy1 = (int)Math.max(localRect.tl().y, 2.0D);
        this.ROIx2 = (int)Math.min(localRect.br().x, -2 + paramMat.width());
        this.ROIy2 = (int)Math.min(localRect.br().y, -2 + paramMat.height());
        return paramMat.submat(new Rect(new Point(this.ROIx1, this.ROIy1), new Point(this.ROIx2, this.ROIy2))).clone();
    }
    public void borderirregularity()
    {
        findConvexHull(this.contours, this.maxAreaIdx);
    }
    public List<MatOfPoint> contour()
    {
        return this.contours;
    }
    public void findDiameter()
    {
        //double d1 = Imgproc.arcLength(new MatOfPoint2f(((MatOfPoint)this.contours.get(this.maxAreaIdx)).toArray()), true);
      //  double d2 = this.M00;
        MatOfPoint2f localMatOfPoint2f = new MatOfPoint2f(((MatOfPoint)this.contours.get(this.maxAreaIdx)).toArray());
        float[] arrayOfFloat = new float[1];
        Imgproc.minEnclosingCircle(localMatOfPoint2f, new Point(), arrayOfFloat);
        double d3 = 2.0F * arrayOfFloat[0];
        Rect localRect = Imgproc.boundingRect((MatOfPoint)this.contours.get(this.maxAreaIdx));
        double d4 = -2 + localRect.width;
        double d5 = -2 + localRect.height;
        Mat localMat1 = this.shape1;
        Point localPoint1 = new Point(this.posX - d4 / 2.0D, this.posY - d5 / 2.0D);
        Point localPoint2 = new Point(this.posX + d4 / 2.0D, this.posY + d5 / 2.0D);
        Core.rectangle(localMat1, localPoint1, localPoint2, new Scalar(255.0D, 255.0D, 255.0D, 255.0D), 2);
        Mat localMat2 = this.shape1;
        Point localPoint3 = new Point(this.posX, this.posY);
        Core.circle(localMat2, localPoint3, (int)arrayOfFloat[0], new Scalar(255.0D, 255.0D, 255.0D, 255.0D), 2);
      /*  Log.i("lesion", "Perimeter=" + d1);
        Log.i("lesion", "Area=" + d2);
        Log.i("lesion", "radius=" + arrayOfFloat[0]);
        Log.i("lesion", "Diameter=" + d3);
        Log.i("lesion", "width=" + d4);
        Log.i("lesion", "height=" + d5);
        Log.i("lesion", "minEnclosingCircle Area=" + 3.141592653589793D * arrayOfFloat[0] * arrayOfFloat[0]);
        Log.i("lesion", "close to min Area percent =" + (3.141592653589793D * arrayOfFloat[0] * arrayOfFloat[0] - d2) / (3.141592653589793D * arrayOfFloat[0] * arrayOfFloat[0]));
        this.s[10] = ((3.141592653589793D * arrayOfFloat[0] * arrayOfFloat[0] - d2) / (3.141592653589793D * arrayOfFloat[0] * arrayOfFloat[0]));*/
    }

    public double getArea()
    {

        return this.M00;
    }

    public Mat getBlurMat()
    {

        return this.mBlur;
    }

    public Point getCenter()
    {

        return new Point(this.posX, this.posY);
    }

    public Mat getCloseMat()
    {

        return this.Mclose;
    }

    public Mat getContourMat()
    {

        return this.contourMat;
    }

    public Mat getCropedMat(Mat paramMat)
    {
        Rect localRect = Imgproc.boundingRect(contours.get(maxAreaIdx));
        this.ROIx1 = (int)Math.max(localRect.tl().x - 30, 2.0D);
        this.ROIy1 = (int)Math.max(localRect.tl().y - 30, 2.0D);
        this.ROIx2 = (int)Math.min(localRect.br().x + 30, -2 + paramMat.width());
        this.ROIy2 = (int)Math.min(localRect.br().y + 30, -2 + paramMat.height());
        Mat localMat1 = paramMat.submat(new Rect(new Point(this.ROIx1, this.ROIy1), new Point(this.ROIx2, this.ROIy2))).clone();
        Log.i("lesion", "ok croped 1 ....");
        Mat localMat2 = new Mat(150, 150, this.mRgba.type());
        Log.i("lesion", "ok croped 2 ....");
        Imgproc.resize(localMat1, localMat2, localMat2.size(), 0.0D, 0.0D, 2);
        Log.i("lesion", "ok croped 3 ....");
        return localMat2;
    }

    public Mat getCropedMatC(Mat paramMat)
    {
        Rect localRect = Imgproc.boundingRect((MatOfPoint)this.contours.get(this.maxAreaIdx));
        this.ROIx1 = (int)Math.max(localRect.tl().x - 50, 1.0D);
        this.ROIy1 = (int)Math.max(localRect.tl().y - 50 - 20.0D, 1.0D);
        this.ROIx2 = (int)Math.min(localRect.br().x + 50, -1 + this.mRgba.width());
        this.ROIy2 = (int)Math.min(20.0D + (localRect.br().y + 50), -1 + this.mRgba.height());
        Mat localMat1 = paramMat.submat(new Rect(new Point(this.ROIx1, this.ROIy1), new Point(this.ROIx2, this.ROIy2))).clone();
        Mat localMat2 = new Mat(150, 150, this.mRgba.type());
        Imgproc.resize(localMat1, localMat2, localMat2.size(), 0.0D, 0.0D, 2);
        return localMat2;
    }

    public double[] getData()
    {
        for (int i = 0; ; i++)
        {
            if (i >= this.s.length)
                return this.s;
            Log.i("From the function:", "s " + i + " = " + this.s[i]);
        }
    }
    public double getEccentricity()
    {
        return this.Eccentricity;
    }
    public Mat getGaryMat()
    {
        return this.mGray;
    }
    public Mat getHslMat()
    {
        return this.mHSL;
    }
    public Mat getMaskMat()
    {
        return this.Mask;
    }
    public Scalar getMeanColor()
    {
        return this.MeanColor;
    }
    public double getPerimeter()
    {
        return this.perimeter;
    }
    public Mat getRGB()
    {

        return this.mRgba;
    }
    public void getROI(Mat paramMat)
    {
        Rect localRect = ROI();
        Core.rectangle(paramMat, localRect.tl(), localRect.br(), this.centerColor);
    }
    public Mat getResizeMat()
    {
        return this.resizeM;
    }
    public double getSD()
    {
        return 0.0D;
    }
    public Mat getSatutatedMat()
    {
        return this.mSatu;
    }
    public Mat getSegmentationMat()
    {
        return this.MSegm;
    }
    public double getThetaD()
    {
        return this.thetaD;
    }
   public double getThetaR()
    {
        return this.thetaR;
    }
    public Mat getThresholdMat()
    {
        return this.Mthreshold;
    }
    public Mat getshape1()
    {
        return this.shape1;
    }
    public Mat getshape2()
    {
        DrawContour(this.shape2);
        return this.shape2;
    }
    public Mat rotateMat(Mat paramMat, int paramInt)
    {
        Log.i("lesion", "rotateMat 0");
        Mat localMat1 = SubMat(paramMat).clone();
        Log.i("lesion", "rotateMat 1");
        double d = Math.sqrt(localMat1.width() * localMat1.width() + (localMat1.height() + localMat1.height()));
        Log.i("lesion", "rotateMat 2");
        this.ROIx1 = (int)Math.max(this.posX - d / 2.0D, 2.0D);
        this.ROIy1 = (int)Math.max(this.posY - d / 2.0D, 2.0D);
        this.ROIx2 = (int)Math.min(this.posX + d / 2.0D, -2 + paramMat.width());
        this.ROIy2 = (int)Math.min(this.posY + d / 2.0D, -2 + paramMat.height());
        Log.i("lesion", "rotateMat 3");
        Mat localMat2 = paramMat.submat(new Rect(new Point(this.ROIx1, this.ROIy1), new Point(this.ROIx2, this.ROIy2))).clone();
        Log.i("lesion", "rotateMat 4");
        int i = (int)(d / 2.0D);
        int j = (int)(d / 2.0D);
        Mat localMat3 = Imgproc.getRotationMatrix2D(new Point(i, j), this.thetaD, 1.0D);
        Log.i("lesion", "rotateMat 5");
        Size localSize1 = localMat2.size();
        Scalar localScalar = new Scalar(0.0D, 0.0D, 0.0D, 0.0D);
        Imgproc.warpAffine(localMat2, localMat2, localMat3, localSize1, 1, 0, localScalar);
        Log.d("lesion","mrigll");
        if (paramInt == 1)
            localMat2 = getYSemetry(localMat2);
        Log.d("lesion","mrigll2");
        while (true) {

            Log.i("lesion", "rotateMat 6");
            Size localSize2 = this.mRgba.size();
            Imgproc.resize(localMat2, localMat2, localSize2);
            Log.i("lesion", "rotateMat 7");
            Log.d("lesion","la valeur est:"+paramInt);
            if (paramInt == 2) {
                Log.d("lesion","val est:"+paramInt);
                localMat2 = getXSemetry(localMat2);
                Log.d("lesion","val"+paramInt);
                Log.d("lesion","mechitttttttt le SX");

            }
            break;

        }
        return localMat2;
    }
}














































































