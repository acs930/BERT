package com.example.kange1.bert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class FeatureSelectorActivity extends Activity implements View.OnTouchListener{

    //I may just do the camera work with openCV as well for uniform image quality and manipulations
    private static final String  TAG = FeatureSelectorActivity.class.getSimpleName();
    ImageView iv;
    //public ArrayList <pointInfo> pointsOfInterest = new ArrayList<pointInfo>();
    public ArrayList<Integer> xPoints = new ArrayList<Integer>();
    public ArrayList<Integer> yPoints = new ArrayList<Integer>();

    RelativeLayout layout;

    Bitmap imageDrawOverlay;
    Bitmap bp = null;
    Canvas imageOverlay;
    Paint color = new Paint();

    public int layoutWidth, layoutHeight;
    public int touchCount = 0;

    //intialize openCV (may not use)
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
        //layout = (RelativeLayout) findViewById(R.id.layout);
        //layout.addView(new DrawView(this));


        iv = (ImageView) findViewById(R.id.selectView);
        color.setColor(Color.RED);
        Button nextButton = (Button) findViewById(R.id.nextClick);

        /*
        // Toast.makeText(this.getApplicationContext(),"Hello", Toast.LENGTH_LONG).show();
        if (getIntent().hasExtra("imagePath")) {
            //ImageView previewThumbnail = new ImageView(this);
            Bitmap bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("imagePath"),
                    0, getIntent().getByteArrayExtra("imagePath").length);
            //previewThumbnail.setImageBitmap(bp);
            */


            //Gets image and displays it
            if (getIntent().hasExtra("imagePath")) {
                File file = new File(getIntent().getStringExtra("imagePath"));

                try {
                    bp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "Error: " + e.toString());
                }

                Log.d(TAG, "Width: " + bp.getWidth() + " Height: " + bp.getHeight());

                iv.setImageBitmap(bp);
                imageDrawOverlay = bp;
            }


            //Sets up layer over the image to points on
            iv.post(new Runnable() {
                @Override
                public void run() {
                    layoutWidth = iv.getWidth();
                    layoutHeight = iv.getHeight();
                    //SetUpCanvas();
                }
            });


            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(FeatureSelectorActivity.this, UploadToServerActivity.class);

                    Log.d(TAG, "x: " + xPoints.toString() + " y: " + yPoints.toString());
                    Bundle bundle = new Bundle();
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    bundle.putIntegerArrayList("xData", xPoints);
                    bundle.putIntegerArrayList("yData", yPoints);

                    //intent.putExtras(bundle);
                    intent.putExtra("imagePath", getIntent().getStringExtra("imagePath"));
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


    public void SetUpCanvas()
    {
        imageDrawOverlay =  Bitmap.createBitmap(layoutWidth, layoutHeight,  Bitmap.Config.RGB_565);
    }


/* bigger pain in the ass cause it's over an image view
public class DrawView extends SurfaceView
{
    private final SurfaceHolder surfaceHolder;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DrawView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if (surfaceHolder.getSurface().isValid() && touchCount < 3) {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawCircle(event.getX(), event.getY(), 50, paint);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return false;
    }
}*/

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


    //Records point when user removes finger
    if(event.getAction() == MotionEvent.ACTION_UP) {
            xCoord = (int) event.getX();
            yCoord = (int) event.getY();

            switch (touchCount) {
                case 0:
                    break;
                case 1:
                    Toast.makeText(this.getApplicationContext(), "Tap Tail", Toast.LENGTH_SHORT).show();
                    
                    break;
                case 2:
                    Toast.makeText(this.getApplicationContext(), "Tap Feet", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this.getApplicationContext(), "Please press next", Toast.LENGTH_SHORT).show();
                    break;

            }


        //problem, image might be resizing to fit screen and cause fuckups in point placing



                Bitmap tempBitmap = Bitmap.createBitmap(bp.getWidth(), bp.getHeight(), Bitmap.Config.RGB_565);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(bp, 0, 0, null);
                tempCanvas.drawCircle((float) xCoord+40, (float) yCoord+40, 50, color);

                tempCanvas.drawCircle(100, 100, 50, color);
                tempCanvas.drawCircle(bp.getWidth(), bp.getHeight(), 50, color);
                tempCanvas.drawCircle(1400, 2000, 50, color);


                iv.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            if (touchCount < 3) {
                xPoints.add(xCoord);
                yPoints.add(yCoord);
            }
            ++touchCount;
            //Toast.makeText(this.getApplicationContext(),toastMessage, Toast.LENGTH_SHORT).show();
            //Log.d(TAG, "x: " + xCoord + " y: " + yCoord);
            return true;
        }
    else
        return true;

    }

}
