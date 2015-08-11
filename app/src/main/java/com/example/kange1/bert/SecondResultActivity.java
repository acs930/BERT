package com.example.kange1.bert;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;


public class SecondResultActivity extends Activity {


    Bitmap robin, jay, goose, grackles, woodpecker, mallard, doves, pigeons, squrrel, cottontail, crow, goldfinch, toad, chickadee, cardinal, cow, deer, chipmunk, turtle, fox, gull, mouse, pig, raccoon, rat, blackbird, sheep, cat, testDefault;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_result);

        ImageButton im1 = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton im2 = (ImageButton) findViewById(R.id.imageButton9);
        ImageButton im3 = (ImageButton) findViewById(R.id.imageButton10);
        b1 = (Button) findViewById(R.id.button8);
        ImageButton[] buttons  = {im1, im2, im3};

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


        Random random = new Random();
        String passName;
        int[] choices = {random.nextInt(28 - 1 + 1) + 1,random.nextInt(28 - 1 + 1) + 1,random.nextInt(28 - 1 + 1) + 1};
        final String[] passNames = new String[3];
        final int[] passNums = new int[3];
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle numBundle = new Bundle();

                if(findViewById(R.id.imageButton5) == v)
                {
                    //Log.d(TAG, lView.getItemAtPosition(0).toString());
                    Intent intent = new Intent();
                    numBundle.putInt("disNum", passNums[0]);
                    numBundle.putString("disTit", passNames[0]);
                    intent.putExtras(numBundle);
                    intent.setClass(SecondResultActivity.this, FinalActivity.class);

                    startActivity(intent);
                }
                else if(findViewById(R.id.imageButton9) == v)
                {
                    Intent intent = new Intent();
                    numBundle.putInt("disNum", passNums[1]);
                    numBundle.putString("disTit", passNames[1]);
                    intent.putExtras(numBundle);
                    intent.setClass(SecondResultActivity.this, FinalActivity.class);
                    startActivity(intent);

                }
                else if(findViewById(R.id.imageButton10) == v)
                {
                    Intent intent = new Intent();
                    numBundle.putInt("disNum", passNums[2]);
                    numBundle.putString("disTit", passNames[2]);
                    intent.putExtras(numBundle);
                    intent.setClass(SecondResultActivity.this, FinalActivity.class);
                    startActivity(intent);
                }
                else if(findViewById(R.id.button8) == v)
                {
                    Intent intent = new Intent();
                    numBundle.putInt("disNum", 0);
                    numBundle.putString("disTit", "No Classification Found");
                    intent.putExtras(numBundle);
                    intent.setClass(SecondResultActivity.this, FinalActivity.class);
                    startActivity(intent);
                }
            }
        };

        for(int i = 0; i < 3; i ++)
        {
            switch (choices[i]) {
                case 1:
                    buttons[i].setImageBitmap(robin);
                    passNames[i] = "American Robin";
                    passNums[i] = choices[i];
                    break;
                case 2:
                    buttons[i].setImageBitmap(jay);
                    passNames[i] = "Blue Jay";
                    passNums[i] = choices[i];
                    break;
                case 3:
                    buttons[i].setImageBitmap(goose);
                    passNames[i] = "Canada Goose";
                    passNums[i] = choices[i];
                    break;
                case 4:
                    buttons[i].setImageBitmap(grackles);
                    passNames[i] = "Common Grackles";
                    passNums[i] = choices[i];
                    break;
                case 5:
                    buttons[i].setImageBitmap(woodpecker);
                    passNames[i] = "Downy Woodpecker";
                    passNums[i] = choices[i];
                    break;
                case 6:
                    buttons[i].setImageBitmap(mallard);
                    passNames[i] = "Mallard";
                    passNums[i] = choices[i];
                    break;
                case 7:
                    buttons[i].setImageBitmap(doves);
                    passNames[i] = "Mourning Doves";
                    passNums[i] = choices[i];
                    break;
                case 8:
                    buttons[i].setImageBitmap(pigeons);
                    passNames[i] = "Rock Dove";
                    passNums[i] = choices[i];
                    break;
                case 9:
                    buttons[i].setImageBitmap(squrrel);
                    passNames[i] = "Gray Squrrel";
                    passNums[i] = choices[i];
                    break;
                case 10:
                    buttons[i].setImageBitmap(cottontail);
                    passNames[i] = "Eastern New England Cottontail";
                    passNums[i] = choices[i];
                    break;
                case 11:
                    buttons[i].setImageBitmap(crow);
                    passNames[i] = "American Crow";
                    passNums[i] = choices[i];
                    break;
                case 12:
                    buttons[i].setImageBitmap(goldfinch);
                    passNames[i] = "American Goldfinch";
                    passNums[i] = choices[i];
                    break;
                case 13:
                    buttons[i].setImageBitmap(toad);
                    passNames[i] = "American Toad";
                    passNums[i] = choices[i];
                    break;
                case 14:
                    buttons[i].setImageBitmap(chickadee);
                    passNames[i] = "Black-Capped Chickadee";
                    passNums[i] = choices[i];
                    break;
                case 15:
                    buttons[i].setImageBitmap(cardinal);
                    passNames[i] = "Cardinal";
                    passNums[i] = choices[i];
                    break;
                case 16:
                    buttons[i].setImageBitmap(cow);
                    passNames[i] = "Cow";
                    passNums[i] = choices[i];
                    break;
                case 17:
                    buttons[i].setImageBitmap(deer);
                    passNames[i] = "White-Tailed Deer";
                    passNums[i] = choices[i];
                    break;
                case 18:
                    buttons[i].setImageBitmap(chipmunk);
                    passNames[i] = "Eastern Chipmunk";
                    passNums[i] = choices[i];
                    break;
                case 19:
                    buttons[i].setImageBitmap(turtle);
                    passNames[i] = "Eastern Painted Turtle";
                    passNums[i] = choices[i];
                    break;
                case 20:
                    buttons[i].setImageBitmap(fox);
                    passNames[i] = "Red Fox";
                    passNums[i] = choices[i];
                    break;
                case 21:
                    buttons[i].setImageBitmap(gull);
                    passNames[i] = "Ring-Billed Gull";
                    passNums[i] = choices[i];
                    break;
                case 22:
                    buttons[i].setImageBitmap(mouse);
                    passNames[i] = "Mouse";
                    passNums[i] = choices[i];
                    break;
                case 23:
                    buttons[i].setImageBitmap(pig);
                    passNames[i] = "Pig";
                    passNums[i] = choices[i];
                    break;
                case 24:
                    buttons[i].setImageBitmap(raccoon);
                    passNames[i] = "Raccoon";
                    passNums[i] = choices[i];
                    break;
                case 25:
                    buttons[i].setImageBitmap(rat);
                    passNames[i] = "Rat";
                    passNums[i] = choices[i];
                    break;
                case 26:
                    buttons[i].setImageBitmap(blackbird);
                    passNames[i] = "Red-Winged Blackbird";
                    passNums[i] = choices[i];
                    break;
                case 27:
                    buttons[i].setImageBitmap(sheep);
                    passNames[i] = "Sheep";
                    passNums[i] = choices[i];
                    break;
                case 28:
                    buttons[i].setImageBitmap(cat);
                    passNames[i] = "Siamese Cat";
                    passNums[i] = choices[i];
                    break;
                default:
                    buttons[i].setImageBitmap(testDefault);
            }

        }

        im1.setOnClickListener(listener);
        im2.setOnClickListener(listener);
        im3.setOnClickListener(listener);
        b1.setOnClickListener(listener);



    }
}
