package com.example.kange1.bert;

import android.content.Intent;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.BatteryManager;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import java.io.File;
import android.os.Environment;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;


public class MenuActivity extends Activity {
    ImageButton b1, b2;
    ImageView iv;

    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int CAPTURE_IMAGE_CODE = 200;
    private Uri fileUri;
    private static final String IMAGE_DIRECTORY_NAME = "OpenCV Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void sendMessageOne(View view) {
        Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
        startActivity(intent);
    }
    public void sendMessageTwo(View view) {
        Intent intent = new Intent(MenuActivity.this, FeatureSelectorActivity.class);
        startActivity(intent);
    }
    public void sendMessageThree(View view) {
        Intent intent = new Intent(MenuActivity.this, BestiaryActivity.class);
        startActivity(intent);
    }
    public void sendMessageFour(View view) {
        Intent intent = new Intent(MenuActivity.this, GalleryActivity.class);
        startActivity(intent);
    }
    public void sendMessageFive(View view) {
        Intent intent = new Intent(MenuActivity.this, CameraActivity.class);
        startActivity(intent);
    }




}
