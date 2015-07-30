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
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    Bitmap robin, jay, goose, grackles, woodpecker, mallard, doves, pigeons, squrrel, cottontail, crow, goldfinch, toad, chickadee, cardinal, cow, deer, chipmunk, turtle, fox, gull, mouse, pig, raccoon, rat, blackbird, sheep, cat, testDefault;
    int picValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary_detail);

        iv = (ImageView)findViewById(R.id.imageView4);
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView3);
        tv3 = (TextView)findViewById(R.id.textView5);
        tv4 = (TextView)findViewById(R.id.textView7);
        tv5 = (TextView)findViewById(R.id.textView9);
        tv6 = (TextView)findViewById(R.id.textView11);
        tv7 = (TextView)findViewById(R.id.textView13);
        tv8 = (TextView)findViewById(R.id.textView15);
        tv9 = (TextView)findViewById(R.id.textView17);

        testDefault = BitmapFactory.decodeResource(getResources(), R.drawable.question_mark);
        robin = BitmapFactory.decodeResource(getResources(), R.drawable.american_robin_d);
        jay = BitmapFactory.decodeResource(getResources(), R.drawable.blue_jay_d);
        goose = BitmapFactory.decodeResource(getResources(), R.drawable.canada_goose_d);
        grackles = BitmapFactory.decodeResource(getResources(), R.drawable.common_grackles_d);
        woodpecker = BitmapFactory.decodeResource(getResources(), R.drawable.downy_woodpecker_d);
        mallard = BitmapFactory.decodeResource(getResources(), R.drawable.mallard_d);
        doves = BitmapFactory.decodeResource(getResources(), R.drawable.mourning_dove_d);
        pigeons = BitmapFactory.decodeResource(getResources(), R.drawable.pigeon_d);
        squrrel = BitmapFactory.decodeResource(getResources(), R.drawable.gray_squrrel_d);
        cottontail = BitmapFactory.decodeResource(getResources(), R.drawable.eastern_new_england_cottontail_d);

        crow = BitmapFactory.decodeResource(getResources(), R.drawable.american_crow_d);
        goldfinch = BitmapFactory.decodeResource(getResources(), R.drawable.american_goldfinch_d);
        toad = BitmapFactory.decodeResource(getResources(), R.drawable.american_toad_d);
        chickadee = BitmapFactory.decodeResource(getResources(), R.drawable.black_capped_chickadee_d);
        cardinal = BitmapFactory.decodeResource(getResources(), R.drawable.cardinal_d);
        cow = BitmapFactory.decodeResource(getResources(), R.drawable.cow_d);
        deer = BitmapFactory.decodeResource(getResources(), R.drawable.white_tailed_deer_d);
        chipmunk = BitmapFactory.decodeResource(getResources(), R.drawable.eastern_chipmunk_d);
        turtle = BitmapFactory.decodeResource(getResources(), R.drawable.eastern_painted_turtle_d);
        fox = BitmapFactory.decodeResource(getResources(), R.drawable.red_fox_d);

        gull = BitmapFactory.decodeResource(getResources(), R.drawable.ring_billed_gull_d);
        mouse = BitmapFactory.decodeResource(getResources(), R.drawable.mouse_d);
        pig = BitmapFactory.decodeResource(getResources(), R.drawable.pig_d);
        raccoon = BitmapFactory.decodeResource(getResources(), R.drawable.raccoon_d);
        rat = BitmapFactory.decodeResource(getResources(), R.drawable.rat_d);
        blackbird = BitmapFactory.decodeResource(getResources(), R.drawable.red_winged_blackbird_d);
        sheep = BitmapFactory.decodeResource(getResources(), R.drawable.sheep_d);
        cat = BitmapFactory.decodeResource(getResources(), R.drawable.siamese_cat_d);

        tv1.setText(getIntent().getStringExtra("aniString"));
        tv2.setText(getIntent().getStringExtra("sciString"));
        tv3.setText(getIntent().getStringExtra("typString"));
        tv4.setText(getIntent().getStringExtra("sizString"));
        tv5.setText(getIntent().getStringExtra("weiString"));
        tv6.setText(getIntent().getStringExtra("lifString"));
        tv7.setText(getIntent().getStringExtra("dieString"));
        tv8.setText(getIntent().getStringExtra("habString"));
        tv9.setText(getIntent().getStringExtra("desString"));

        picValue = Integer.valueOf(getIntent().getStringExtra("aniInt"));

        switch (picValue) {
            case 1:
                iv.setImageBitmap(robin);
                break;
            case 2:
                iv.setImageBitmap(jay);
                break;
            case 3:
                iv.setImageBitmap(goose);
                break;
            case 4:
                iv.setImageBitmap(grackles);
                break;
            case 5:
                iv.setImageBitmap(woodpecker);
                break;
            case 6:
                iv.setImageBitmap(mallard);
                break;
            case 7:
                iv.setImageBitmap(doves);
                break;
            case 8:
                iv.setImageBitmap(pigeons);
                break;
            case 9:
                iv.setImageBitmap(squrrel);
                break;
            case 10:
                iv.setImageBitmap(cottontail);
                break;
            case 11:
                iv.setImageBitmap(crow);
                break;
            case 12:
                iv.setImageBitmap(goldfinch);
                break;
            case 13:
                iv.setImageBitmap(toad);
                break;
            case 14:
                iv.setImageBitmap(chickadee);
                break;
            case 15:
                iv.setImageBitmap(cardinal);
                break;
            case 16:
                iv.setImageBitmap(cow);
                break;
            case 17:
                iv.setImageBitmap(deer);
                break;
            case 18:
                iv.setImageBitmap(chipmunk);
                break;
            case 19:
                iv.setImageBitmap(turtle);
                break;
            case 20:
                iv.setImageBitmap(fox);
                break;
            case 21:
                iv.setImageBitmap(gull);
                break;
            case 22:
                iv.setImageBitmap(mouse);
                break;
            case 23:
                iv.setImageBitmap(pig);
                break;
            case 24:
                iv.setImageBitmap(raccoon);
                break;
            case 25:
                iv.setImageBitmap(rat);
                break;
            case 26:
                iv.setImageBitmap(blackbird);
                break;
            case 27:
                iv.setImageBitmap(sheep);
                break;
            case 28:
                iv.setImageBitmap(cat);
                break;
            default:
                iv.setImageBitmap(testDefault);
        }
    }
}
