package com.example.kange1.bert;

import android.content.Intent;
import android.app.Activity;
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

public class MenuActivity extends Activity {
    ImageButton b1, b2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        b1 = (ImageButton)findViewById(R.id.imageButton);
        //iv = (ImageView)findViewById(R.id.imageView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
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
        Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
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
