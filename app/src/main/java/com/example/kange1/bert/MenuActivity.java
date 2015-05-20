package com.example.kange1.bert;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
