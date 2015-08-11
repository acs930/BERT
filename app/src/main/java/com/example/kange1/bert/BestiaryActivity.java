package com.example.kange1.bert;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestiaryActivity extends Activity {

    Bitmap americanRobin, blueJay, canadaGoose, commonGrackle, downyWoodpecker, mallard, mourningDove, pigeon, easternNewEnglandCottontail, graySquirrel, americanCrow, americanGoldfinch, americanToad, blackCappedChickadee, cardinal, cow, whiteTailedDeer, easternChipmunk, easternPaintedTurtle, redFox, ringBilledGull, mouse, pig, raccoon, rat, redWingedBlackbird, sheep, siameseCat;

    String urlServer = "http://52.3.50.112/dbConnect.php";
    HttpURLConnection con = null;

    String responseFromServer;
    int aniNumber;

    String jsonResult = "";
    String ani_name = "", sci_name = "", typ_name = "", siz_name="", wei_name="", lif_name="", die_name="", hab_name="", des_name="";

    private static final String TAG = BestiaryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary);

        americanRobin = BitmapFactory.decodeResource(getResources(), R.drawable.american_robin);
        blueJay = BitmapFactory.decodeResource(getResources(), R.drawable.blue_jay);
        canadaGoose = BitmapFactory.decodeResource(getResources(), R.drawable.canada_goose);
        commonGrackle = BitmapFactory.decodeResource(getResources(), R.drawable.common_grackle);
        downyWoodpecker = BitmapFactory.decodeResource(getResources(), R.drawable.downy_woodpecker);
        mallard = BitmapFactory.decodeResource(getResources(), R.drawable.mallard);
        mourningDove = BitmapFactory.decodeResource(getResources(), R.drawable.mourning_dove);
        pigeon = BitmapFactory.decodeResource(getResources(), R.drawable.pigeon);
        easternNewEnglandCottontail = BitmapFactory.decodeResource(getResources(), R.drawable.eastern_new_england_cottontail);
        graySquirrel = BitmapFactory.decodeResource(getResources(), R.drawable.gray_squirrel);

        americanCrow = BitmapFactory.decodeResource(getResources(), R.drawable.american_crow);
        americanGoldfinch = BitmapFactory.decodeResource(getResources(), R.drawable.american_goldfinch);
        americanToad = BitmapFactory.decodeResource(getResources(), R.drawable.american_toad);
        blackCappedChickadee = BitmapFactory.decodeResource(getResources(), R.drawable.black_capped_chickadee);
        cardinal = BitmapFactory.decodeResource(getResources(), R.drawable.cardinal);
        cow = BitmapFactory.decodeResource(getResources(), R.drawable.cow);
        whiteTailedDeer = BitmapFactory.decodeResource(getResources(), R.drawable.white_tailed_deer);
        easternChipmunk = BitmapFactory.decodeResource(getResources(), R.drawable.eastern_chipmunk);
        easternPaintedTurtle = BitmapFactory.decodeResource(getResources(), R.drawable.eastern_painted_turtle);
        redFox = BitmapFactory.decodeResource(getResources(), R.drawable.red_fox);

        ringBilledGull = BitmapFactory.decodeResource(getResources(), R.drawable.ring_billed_gull);
        mouse = BitmapFactory.decodeResource(getResources(), R.drawable.mouse);
        pig = BitmapFactory.decodeResource(getResources(), R.drawable.pig);
        raccoon = BitmapFactory.decodeResource(getResources(), R.drawable.raccoon);
        rat = BitmapFactory.decodeResource(getResources(), R.drawable.rat);
        redWingedBlackbird = BitmapFactory.decodeResource(getResources(), R.drawable.red_winged_blackbird);
        sheep = BitmapFactory.decodeResource(getResources(), R.drawable.sheep);
        siameseCat = BitmapFactory.decodeResource(getResources(), R.drawable.siamse_cat);

        List<ListItem> list = new ArrayList<ListItem>();

        ListItem item1 = new ListItem();
        item1.image = americanRobin;
        item1.name = "American Robin";
        list.add(item1);

        ListItem item2 = new ListItem();
        item2.image = blueJay;
        item2.name = "Blue Jay";
        list.add(item2);

        ListItem item3 = new ListItem();
        item3.image = canadaGoose;
        item3.name = "Canada Goose";
        list.add(item3);

        ListItem item4 = new ListItem();
        item4.image = commonGrackle;
        item4.name = "Common Grackles";
        list.add(item4);

        ListItem item5 = new ListItem();
        item5.image = downyWoodpecker;
        item5.name = "Downy Woodpecker";
        list.add(item5);

        ListItem item6 = new ListItem();
        item6.image = mallard;
        item6.name = "Mallard";
        list.add(item6);

        ListItem item7 = new ListItem();
        item7.image = mourningDove;
        item7.name = "Mourning Doves";
        list.add(item7);

        ListItem item8 = new ListItem();
        item8.image = pigeon;
        item8.name = "Rock Dove";
        list.add(item8);

        ListItem item9 = new ListItem();
        item9.image = graySquirrel;
        item9.name = "Gray Squrrel";
        list.add(item9);

        ListItem item10 = new ListItem();
        item10.image = easternNewEnglandCottontail;
        item10.name = "Eastern New England Cottontail";
        list.add(item10);

        ListItem item11 = new ListItem();
        item11.image = americanCrow;
        item11.name = "American Crow";
        list.add(item11);

        ListItem item12 = new ListItem();
        item12.image = americanGoldfinch;
        item12.name = "American Goldfinch";
        list.add(item12);

        ListItem item13 = new ListItem();
        item13.image = americanToad;
        item13.name = "American Toad";
        list.add(item13);

        ListItem item14 = new ListItem();
        item14.image = blackCappedChickadee;
        item14.name = "Black-Capped Chickadee";
        list.add(item14);

        ListItem item15 = new ListItem();
        item15.image = cardinal;
        item15.name = "Cardinal";
        list.add(item15);

        ListItem item16 = new ListItem();
        item16.image = cow;
        item16.name = "Cow";
        list.add(item16);

        ListItem item17 = new ListItem();
        item17.image = whiteTailedDeer;
        item17.name = "White-Tailed Deer";
        list.add(item17);

        ListItem item18 = new ListItem();
        item18.image = easternChipmunk;
        item18.name = "Eastern Chipmunk";
        list.add(item18);

        ListItem item19 = new ListItem();
        item19.image = easternPaintedTurtle;
        item19.name = "Eastern Painted Turtle";
        list.add(item19);

        ListItem item20 = new ListItem();
        item20.image = redFox;
        item20.name = "Red Fox";
        list.add(item20);

        ListItem item21 = new ListItem();
        item21.image = ringBilledGull;
        item21.name = "Ring-Billed Gull";
        list.add(item21);

        ListItem item22 = new ListItem();
        item22.image = mouse;
        item22.name = "Mouse";
        list.add(item22);

        ListItem item23 = new ListItem();
        item23.image = pig;
        item23.name = "Pig";
        list.add(item23);

        ListItem item24 = new ListItem();
        item24.image = raccoon;
        item24.name = "Raccoon";
        list.add(item24);

        ListItem item25 = new ListItem();
        item25.image = rat;
        item25.name = "Rat";
        list.add(item25);

        ListItem item26 = new ListItem();
        item26.image = redWingedBlackbird;
        item26.name = "Red-Winged Blackbird";
        list.add(item26);

        ListItem item27 = new ListItem();
        item27.image = sheep;
        item27.name = "Sheep";
        list.add(item27);

        ListItem item28 = new ListItem();
        item28.image = siameseCat;
        item28.name = "Siamese Cat";
        list.add(item28);

        ListItemAdapter adapter;
        adapter = new ListItemAdapter(this, 0, list);

        ListView listView = (ListView) findViewById(R.id.ListView01);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                for (int i = 0; i < 28; i++) {
                    if (position == i) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Looper.prepare();
                                aniNumber = position+1;
                                Log.d(TAG, "" +aniNumber);
                                uploadToServer(aniNumber);
                                Log.d(TAG, "my thread works");
                            }
                        });
                        t.start();
                        Log.d(TAG, "thread get started");
                    }
                }
            }
        });
    }

    public void testReceiveData(String jsonResult, int aniNumber) {
        try {
            Log.d(TAG, "here"+jsonResult);

            JSONObject jsonObj = new JSONObject(jsonResult);
            ani_name = jsonObj.getString("name");
            sci_name = jsonObj.getString("sci_name");
            typ_name = jsonObj.getString("type");
            siz_name = jsonObj.getString("size");
            wei_name = jsonObj.getString("weight");
            lif_name = jsonObj.getString("lifespan");
            die_name = jsonObj.getString("diet");
            hab_name = jsonObj.getString("habitat");
            des_name = jsonObj.getString("description");

            Log.d(TAG, "It gets JSONObject");
            Log.d(TAG, ani_name);
            Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("aniString", ani_name);
            bundle.putString("sciString", sci_name);
            bundle.putString("typString", typ_name);
            bundle.putString("sizString", siz_name);
            bundle.putString("weiString", wei_name);
            bundle.putString("lifString", lif_name);
            bundle.putString("dieString", die_name);
            bundle.putString("habString", hab_name);
            bundle.putString("desString", des_name);
            bundle.putString("aniInt", String.valueOf(aniNumber));

            intent.putExtras(bundle);
            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public boolean uploadToServer(int aniNumber) {
        try {
            String responseString = null;
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlServer);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("aniName", Integer.toString(aniNumber)));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);
            Log.d(TAG, "amHere");

            HttpEntity result = response.getEntity();
            if (result != null) {
                InputStream input = result.getContent();
                jsonResult = convertStreamToString(input);
                Log.d(TAG, jsonResult);
                Log.d(TAG, "jesonResult works");

                testReceiveData(jsonResult, aniNumber); //It said position needs to be declared final above
                Log.d(TAG, "maybe this one works too");
            } else {
                Log.d(TAG, "broke in repsonse");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
}