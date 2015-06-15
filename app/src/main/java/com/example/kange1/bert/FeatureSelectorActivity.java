package com.example.kange1.bert;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appdatasearch.Feature;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class FeatureSelectorActivity extends Activity implements View.OnTouchListener{

    //I may just do the camera work with openCV as well for uniform image quality and manipulations
    private static final String  TAG = FeatureSelectorActivity.class.getSimpleName();
    ImageView iv;
    //public ArrayList <pointInfo> pointsOfInterest = new ArrayList<pointInfo>();
    public ArrayList<Integer> xPoints = new ArrayList<Integer>();
    public ArrayList<Integer> yPoints = new ArrayList<Integer>();
    Bitmap imageDrawOverlay;
    Canvas imageOverlay;
    Paint color = new Paint();
    public int layoutWidth, layoutHeight;
    public int touchCount = 0;

    /*public class pointInfo{
        public int xCoord;
        public int yCoord;

        public pointInfo()
        {
            xCoord = 0;
            yCoord = 0;
        }
        public pointInfo(int x, int y)
        {
            xCoord = x;
            yCoord = y;
        }

    }*/

    private BaseLoaderCallback mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.d(TAG, "OpenCV loaded successfully");
                    iv.setOnTouchListener(FeatureSelectorActivity.this);

                } break;
                default:
                {
                    Log.d(TAG,"Problem");
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_selector);

        iv = (ImageView)findViewById(R.id.selectView);
        Button nextButton = (Button)findViewById(R.id.nextClick);


       // Toast.makeText(this.getApplicationContext(),"Hello", Toast.LENGTH_LONG).show();
        if(getIntent().hasExtra("byteArray")) {
            ImageView previewThumbnail = new ImageView(this);
            Bitmap bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"),
                    0, getIntent().getByteArrayExtra("byteArray").length);
            previewThumbnail.setImageBitmap(bp);
            iv.setImageBitmap(bp);
        }
        //Sets up layer over the image to points on
        iv.post(new Runnable()
        {
            @Override
        public void run()
            {
                Log.d(TAG,"ivX: " + iv.getWidth() + " ivY: " + iv.getHeight());
                layoutWidth = iv.getWidth();
                layoutHeight = iv.getHeight();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FeatureSelectorActivity.this, UploadToServerActivity.class);

                Bundle bundle = new Bundle();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bundle.putIntegerArrayList("xData", xPoints);
                bundle.putIntegerArrayList("yData", yPoints);

                //intent.putExtras(bundle);
                intent.putExtra("byteArray", getIntent().hasExtra("byteArray"));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });




        if (!OpenCVLoader.initDebug()) {
            //Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mOpenCVCallBack);
        } else {
            //Log.d(TAG, "OpenCV library found inside package. Using it!");
            mOpenCVCallBack.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

        Toast.makeText(this.getApplicationContext(), "Tap Eyes", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feature_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int xCoord, yCoord;

        xCoord = (int)event.getX();
        yCoord = (int)event.getY();

        switch(touchCount) {
            case 0:
                break;
            case 1:
                Toast.makeText(this.getApplicationContext(), "Tap Tail", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this.getApplicationContext(), "Tap Feet", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this.getApplicationContext(), "Please press next", Toast.LENGTH_SHORT).show();
                return true;

        }
        //String toastMessage = "Hello, you clicked" + xCoord + "," + yCoord;

        //imageDrawOverlay = Bitmap.createBitmap(layoutWidth, layoutHeight, Bitmap.Config.ARGB_8888);
        //imageOverlay = new Canvas(imageDrawOverlay);
        //color.setColor(0);
        //imageOverlay.drawCircle((float)xCoord, (float)yCoord, 2, color);

        xPoints.add(xCoord);
        yPoints.add(yCoord);
        ++touchCount;
        //Toast.makeText(this.getApplicationContext(),toastMessage, Toast.LENGTH_SHORT).show();
        //Log.d(TAG, "x: " + xCoord + " y: " + yCoord);
        return true;
    }


}
