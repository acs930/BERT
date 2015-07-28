package com.example.kange1.bert;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class CameraActivity extends Activity {

    ImageButton b1;
    ImageView iv;
    public Location test;
    private static final String  TAG = CameraActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Log.d("cam", "In Cam Activity");

        b1 = (ImageButton)findViewById(R.id.imageButton6);
        iv = (ImageView)findViewById(R.id.imageView);
        final File tempFile = getTempFile(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, 0);
                Log.d("cam","In Cam Activity");
           }
        });

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
    }

    private File getTempFile(Context context)
    {

        final File tempPath = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if(!tempPath.exists())
            tempPath.mkdir();


        return new File(tempPath, "image.tmp");

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //super.onActivityResult(requestCode, resultCode, data);

        final File file = getTempFile(this);
        Intent i = new Intent(CameraActivity.this, FeatureSelectorActivity.class);
        i.putExtra("imagePath", file.getAbsolutePath());
        startActivity(i);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Lat = " + loc.getLatitude() + "|Long = " + loc.getLongitude();
            Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
            test = loc;
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS Disabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
}
