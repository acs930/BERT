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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class BestiaryActivity extends Activity {

    String outName, outPicture;
    Bitmap americanRobin, blueJay, canadaGoose, commonGrackle, downyWoodpecker, mallard, mourningDove, pigeon, easternNewEnglandCottontail, graySquirrel;
    String[] aniList = {"American Robin", "Blue Jay", "Canada Goose", "Common Grackles", "DownyWoodpecker", "Mallard", "Pigeon",
            "Eastern New England Cottontail", "Gray Squirrel"};
    Bitmap[] bitmapList = {americanRobin, blueJay, canadaGoose, commonGrackle, downyWoodpecker, mallard, mourningDove, pigeon,
            easternNewEnglandCottontail, graySquirrel};

    String urlServer = "http://52.3.50.112/dbConnect.php";
    HttpURLConnection con = null;

    String responseFromServer;
    int aniNumber;

    private static final String  TAG = BestiaryActivity.class.getSimpleName();
    //private final String TAG = "myApp";

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

        List<ListItem> list = new ArrayList<ListItem>();

        //int sum = 10;
        //ListItem[] item = new ListItem[sum];
        /*
        for (int i=0; i<sum; i++) {
            ListItem[] item = new ListItem[sum];
            item[i].image = bitmapList[i];
            item[i].name = aniList[i];
            list.add(item[i]);
        }
        */

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
        item8.name = "Pigeons";
        list.add(item8);

        ListItem item9 = new ListItem();
        item9.image = graySquirrel;
        item9.name = "Gray Squrrel";
        list.add(item9);

        ListItem item10 = new ListItem();
        item10.image = easternNewEnglandCottontail;
        item10.name = "Eastern New England Cottontail";
        list.add(item10);

        ListItemAdapter adapter;
        adapter = new ListItemAdapter(this, 0, list);

        ListView listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i=0; i<10; i++) {
                    if (position == i) {

                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                uploadToServer();
                            }
                        });
                        t.start();

                        Toast.makeText(BestiaryActivity.this,"Clicked", Toast.LENGTH_SHORT).show();

                        //Replace with URL
                        /*
                        String s = "URL";
                        s += URLEncoder.encode(addr, "UTF-8");
                        URL url = new URL(s);

                        Scanner scan = new Scanner(url.openStream());
                        String displayOutput = new String();
                        while (scan.hasNext())
                            displayOutput += scan.nextLine();
                        scan.close();
                        */

                        String displayOutput = "{\"Animal\" :[{\"Name\":\"American Robin\", \"Type\":\"Bird\" }";

                        try {
                            JSONObject reader = new JSONObject(displayOutput);
                            JSONArray displayArray = reader.optJSONArray("Animal");

                            JSONObject displayItem = displayArray.getJSONObject(position);

                            String animalName = displayItem.optString("Name").toString();
                            String animalType = displayItem.optString("Type").toString();

                            Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("aniString", animalName);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        outName = aniList[i];
                        Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("aniString", outName);
                        startActivity(intent);

                        /*
                        outName = aniList[i];
                        Intent intent = new Intent(BestiaryActivity.this, BestiaryDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("aniString", outName);
                        //bundle.putString("aniString", animalName);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        */
                    }
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public boolean uploadToServer() {
        try {
            String responseString = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlServer);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("aniName", Integer.toString(aniNumber)));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
            Log.d(TAG, "animal Name: " + Integer.toString(aniNumber));

            responseFromServer = responseBody;
            Log.d(TAG, ""+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        /*
        try {
            JSONObject jResult = new JSONObject(displayOutput);
            JSONArray jArray = jResult.getJSONArray("example");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }

}
