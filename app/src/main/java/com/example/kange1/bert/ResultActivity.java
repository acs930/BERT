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
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ResultActivity extends Activity {

    ImageView iv;
    Button b1, b2, b3;
    Bitmap robin, jay, goose, grackles, woodpecker, mallard, doves, pigeons, squrrel, cottontail, crow, goldfinch, toad, chickadee, cardinal, cow, deer, chipmunk, turtle, fox, gull, mouse, pig, raccoon, rat, blackbird, sheep, cat, testDefault;
    Bitmap question;
    int picValue;
    String passName;
    String passTitle = "Congratulation!!";
    String passSub = "You take a picture of";

    int gradeA = 1, gradeB = 2, gradeC = 3;

    String urlServerTwo = "http://52.3.50.112/dbConnect.php";
    String jsonResult = "";

    int json_res;
    String json_res_s = "";
    int testPicValue;

    private static final String  TAG = ResultActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        /*try {
            HttpClient httpclientTwo = new DefaultHttpClient();

            HttpPost httpPostTwo = new HttpPost(urlServerTwo);

            HttpResponse responseTwo = httpclientTwo.execute(httpPostTwo);
            HttpEntity result = responseTwo.getEntity();
            if (result != null) {
                InputStream input = result.getContent();
                jsonResult = convertStreamToString(input);
                Log.d(TAG, jsonResult);
                Log.d(TAG, "jesonResult works");

                testReceiveData(jsonResult); //It said position needs to be declared final above
                Log.d(TAG, "maybe this one works too");
            } else {
                Log.d(TAG, "broke in repsonse");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        iv = (ImageView)findViewById(R.id.imageView5);
        b1 = (Button)findViewById(R.id.button3);
        b2 = (Button)findViewById(R.id.button4);
        b3 = (Button)findViewById(R.id.button8);

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
        question = BitmapFactory.decodeResource(getResources(), R.drawable.no_id_bird_s);

        //picValue = Integer.valueOf(getIntent().getStringExtra("ansId"));
        testPicValue = Integer.valueOf(json_res_s);

        switch (testPicValue) {
            case 0:
                iv.setImageBitmap(question);
                break;
            case 1:
                iv.setImageBitmap(robin);
                passName = "American Robin";
                break;
            case 2:
                iv.setImageBitmap(jay);
                passName = "Blue Jay";
                break;
            case 3:
                iv.setImageBitmap(goose);
                passName = "Canada Goose";
                break;
            case 4:
                iv.setImageBitmap(grackles);
                passName = "Common Grackles";
                break;
            case 5:
                iv.setImageBitmap(woodpecker);
                passName = "Downy Woodpecker";
                break;
            case 6:
                iv.setImageBitmap(mallard);
                passName = "Mallard";
                break;
            case 7:
                iv.setImageBitmap(doves);
                passName = "Mourning Doves";
                break;
            case 8:
                iv.setImageBitmap(pigeons);
                passName = "Rock Dove";
                break;
            case 9:
                iv.setImageBitmap(squrrel);
                passName = "Gray Squrrel";
                break;
            case 10:
                iv.setImageBitmap(cottontail);
                passName = "Eastern New England Cottontail";
                break;
            case 11:
                iv.setImageBitmap(crow);
                passName = "American Crow";
                break;
            case 12:
                iv.setImageBitmap(goldfinch);
                passName = "American Goldfinch";
                break;
            case 13:
                iv.setImageBitmap(toad);
                passName = "American Toad";
                break;
            case 14:
                iv.setImageBitmap(chickadee);
                passName = "Black-Capped Chickadee";
                break;
            case 15:
                iv.setImageBitmap(cardinal);
                passName = "Cardinal";
                break;
            case 16:
                iv.setImageBitmap(cow);
                passName = "Cow";
                break;
            case 17:
                iv.setImageBitmap(deer);
                passName = "White-Tailed Deer";
                break;
            case 18:
                iv.setImageBitmap(chipmunk);
                passName = "Eastern Chipmunk";
                break;
            case 19:
                iv.setImageBitmap(turtle);
                passName = "Eastern Painted Turtle";
                break;
            case 20:
                iv.setImageBitmap(fox);
                passName = "Red Fox";
                break;
            case 21:
                iv.setImageBitmap(gull);
                passName = "Ring-Billed Gull";
                break;
            case 22:
                iv.setImageBitmap(mouse);
                passName = "MOuse";
                break;
            case 23:
                iv.setImageBitmap(pig);
                passName = "Pig";
                break;
            case 24:
                iv.setImageBitmap(raccoon);
                passName = "Raccoon";
                break;
            case 25:
                iv.setImageBitmap(rat);
                passName = "Rat";
                break;
            case 26:
                iv.setImageBitmap(blackbird);
                passName = "Red-Winged Blackbird";
                break;
            case 27:
                iv.setImageBitmap(sheep);
                passName = "Sheep";
                break;
            case 28:
                iv.setImageBitmap(cat);
                passName = "Siamese Cat";
                break;
            default:
                iv.setImageBitmap(testDefault);
        }

        if (picValue == 0) {
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "picValue is 0");
                    Intent i = new Intent(ResultActivity.this, SecondResultActivity.class);
                    startActivity(i);
                }
            });
        } else {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "almost there");
                    Intent i = new Intent(ResultActivity.this, FinalActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("disAni", passName);
                    bundle.putString("disNum", String.valueOf(picValue));
                    bundle.putString("disTit", passTitle);
                    bundle.putString("disSub", passSub);
                    bundle.putString("gradVal", String.valueOf(gradeA));

                    i.putExtras(bundle);
                    startActivity(i);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "almost there");
                    Intent i = new Intent(ResultActivity.this, SecondResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("disAni", passName);
                    bundle.putString("disNum", String.valueOf(picValue));
                    bundle.putString("disTit", passTitle);
                    bundle.putString("disSub", passSub);
                    bundle.putString("gradVal", String.valueOf(gradeA));

                    i.putExtras(bundle);
                    startActivity(i);
                }
            });
        }
    }

    private String convertStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Stream Exception", Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }

    public void testReceiveData(String jsonResult) {
        try {
            Log.d(TAG, "here"+jsonResult);

            JSONObject jsonObj = new JSONObject(jsonResult);
            /*
            ani_name = jsonObj.getString("name");
            sci_name = jsonObj.getString("sci_name");
            typ_name = jsonObj.getString("type");
            siz_name = jsonObj.getString("size");
            wei_name = jsonObj.getString("weight");
            lif_name = jsonObj.getString("lifespan");
            die_name = jsonObj.getString("diet");
            hab_name = jsonObj.getString("habitat");
            des_name = jsonObj.getString("description");
            */

            //json_res = jsonObj.getInt("animal_id");
            json_res_s = jsonObj.getString("animal_id");


            Log.d(TAG, "It gets JSONObject");
            Log.d(TAG, String.valueOf(json_res));
            //Intent intent = new Intent(UploadToServerActivity.this, ResultActivity.class);
            //Bundle bundle = new Bundle();
            /*
            bundle.putString("aniString", ani_name);
            bundle.putString("sciString", sci_name);
            bundle.putString("typString", typ_name);
            bundle.putString("sizString", siz_name);
            bundle.putString("weiString", wei_name);
            bundle.putString("lifString", lif_name);
            bundle.putString("dieString", die_name);
            bundle.putString("habString", hab_name);
            bundle.putString("desString", des_name);
            */
            //bundle.putString("ansId", String.valueOf(testNumbertest));
            //bundle.putString("ansId", String.valueOf(json_res));
            //bundle.putString("ansId", json_res_s);

            //intent.putExtras(bundle);
            //startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
