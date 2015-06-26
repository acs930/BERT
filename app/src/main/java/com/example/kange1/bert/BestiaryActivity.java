package com.example.kange1.bert;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BestiaryActivity extends Activity {

    String outName, outPicture;
    Bitmap testPicture, defaultImage;
    String aniList[] = {"American Robin", "Blue Jay", "Canada Goose", "Common Grackles"};

    private final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary);

        //Bitmap defaultImage;
        defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.american_robin);

        List<ListItem> list = new ArrayList<ListItem>();

        ListItem item1 = new ListItem();
        item1.image = defaultImage;
        item1.name = "American Robin";
        list.add(item1);

        ListItem item2 = new ListItem();
        item2.image = defaultImage;
        item2.name = "Blue Jay";
        list.add(item2);

        ListItem item3 = new ListItem();
        item3.image = defaultImage;
        item3.name = "Canada Goose";
        list.add(item3);

        ListItem item4 = new ListItem();
        item4.image = defaultImage;
        item4.name = "Common Grackles";
        list.add(item4);

        ListItem item5 = new ListItem();
        item5.image = defaultImage;
        item5.name = "Downy Woodpecker";
        list.add(item5);

        ListItem item6 = new ListItem();
        item6.image = defaultImage;
        item6.name = "Mallard";
        list.add(item6);

        ListItem item7 = new ListItem();
        item7.image = defaultImage;
        item7.name = "Mourning Doves";
        list.add(item7);

        ListItem item8 = new ListItem();
        item8.image = defaultImage;
        item8.name = "Pigeons";
        list.add(item8);

        ListItem item9 = new ListItem();
        item9.image = defaultImage;
        item9.name = "Gray Squrrel";
        list.add(item9);

        /*
        if (list == item1) {
            outName = "AmericanRobin";
            outPicture = defaultImage.toString();
            testPicture = defaultImage;
        }
        */

        ListItemAdapter adapter;
        adapter = new ListItemAdapter(this, 0, list);

        ListView listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(adapter);

        /*
        Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("aniName", outName);
        bundle.putString("aniPic", outPicture);
        bundle.putParcelable("testPic", testPicture);

        intent.putExtras(bundle);
        startActivity(intent);
        */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    outName = "American Robin";
                    Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("aniName", outName);

                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (position == 1) {
                    outName = "Blue Jay";
                    Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("aniName", outName);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bestiary, menu);
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
