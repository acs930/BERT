package com.example.kange1.bert;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class BestiaryDetailActivity extends Activity {

    ImageView iv;
    TextView tv;
    Bitmap testBit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary_detail);

        iv = (ImageView)findViewById(R.id.imageView4);
        tv = (TextView)findViewById(R.id.textView7);

        testBit = BitmapFactory.decodeResource(getResources(), R.drawable.test_robin);

        tv.setText(getIntent().getStringExtra("aniName"));
        iv.setImageBitmap(testBit);
        //iv.setImageBitmap(getIntent().getStringExtra("aniPic"));
        //iv.setImageBitmap(getIntent().getParcelableExtra("testPic"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bestiary_detail, menu);
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
}
