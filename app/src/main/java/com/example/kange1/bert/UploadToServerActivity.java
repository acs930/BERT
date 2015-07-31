package com.example.kange1.bert;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Entity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class UploadToServerActivity extends Activity {

    ArrayList<Integer> xPoint, yPoint;
    byte[] img;
    String imagePath;
    String imageData;
    Button b1, b2;
    ImageView v1;
    Bitmap imageDrawOverlay;

    int answerId, accuracyNum;
    String jsonResult = "";
    String srcString = "";

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    String urlServer = "http://52.3.50.112/imageHandler.php";
    String urlServerTwo = "http://52.3.50.112/dbConnect.php";
    //HttpURLConnection connection = null;

    HttpURLConnection con = null;
    String responseFromServer;
    private static final String  TAG = UploadToServerActivity.class.getSimpleName();

    String ani_name = "", sci_name = "", typ_name = "", siz_name="", wei_name="", lif_name="", die_name="", hab_name="", des_name="";
    int testNumbertest = 2;
    int json_res;
    String json_res_s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_to_server);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        v1 = (ImageView)findViewById(R.id.imageView3);

        Intent intent = getIntent();

        xPoint = intent.getIntegerArrayListExtra("xData");
        yPoint = intent.getIntegerArrayListExtra("yData");
        imagePath = intent.getStringExtra("imagePath");

        Bitmap bp = null;
        if(getIntent().hasExtra("imagePath")) {

            File file = new File(getIntent().getStringExtra("imagePath"));

            try {
                bp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));

            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Error: " + e.toString());
            }
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        img = stream.toByteArray();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "hello");
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadToServer();
                        //Intent i = new Intent(UploadToServerActivity.this, ResultActivity.class);
                        //startActivity(i);
                    }
                });
                t.start();

                Toast.makeText(UploadToServerActivity.this,"Upload Successful", Toast.LENGTH_SHORT).show();
                //TextView text = (TextView)findViewById(R.id.textView5);
                //text.setText(responseFromServer);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadToServerActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        if (getIntent().hasExtra("imagePath")) {
            File file = new File(getIntent().getStringExtra("imagePath"));

            try {
                bp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));

            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Error: " + e.toString());
            }

            Log.d(TAG, "Width: " + bp.getWidth() + " Height: " + bp.getHeight());

            v1.setImageBitmap(bp);
            imageDrawOverlay = bp;
        }

        /*
        if(getIntent().hasExtra("imagePath")) {
            //ImageView previewThumbnail = new ImageView(this);
            bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("imagePath"),
                    0, getIntent().getByteArrayExtra("imagePath").length);
            //previewThumbnail.setImageBitmap(bp);
            v1.setImageBitmap(bp);
        }
        */
    }

    @SuppressWarnings("deprecation")
    public boolean uploadToServer() {
        try {
            String responseString = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpClient httpclientTwo = httpclient;

            HttpPost httpPost = new HttpPost(urlServer);
            HttpPost httpPostTwo = new HttpPost(urlServerTwo);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            imageData = Base64.encodeToString(img, Base64.DEFAULT);
            String xDat = xPoint.toString();
            String yDat = yPoint.toString();

            nameValuePairs.add(new BasicNameValuePair("imageData", imageData));
            nameValuePairs.add(new BasicNameValuePair("xData", xDat));
            nameValuePairs.add(new BasicNameValuePair("yData", yDat));
            Log.d(TAG, "xp: " + xPoint.toString());
            Log.d(TAG, "yp: " + yPoint.toString());
            Log.d(TAG, "im: " + imageData.length());

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
            Log.d(TAG, responseBody);
            responseFromServer = responseBody;

            Intent i = new Intent(UploadToServerActivity.this, ResultActivity.class);
            startActivity(i);
            /*
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
            */

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

    public void triggerImageUpload() {
        makeHTTPCall();
    }

    public void makeHTTPCall() {
    }

    public int doSomeTasks() {
        while (fileSize <= 1000000) {
            fileSize++;

            if (fileSize == 100000) {
                return 10;
            } else if (fileSize == 200000) {
                return 20;
            } else if (fileSize == 300000) {
                return 30;
            }
        }
        return 100;
    }

    /*
    public void testReceiveData(String jsonResult) {
        try {
            // you can consume Content only at once
            // http://stackoverflow.com/questions/4727114/illegalstateexception-content-has-been-consumed
            srcString = jsonResult;
            Log.d(TAG, "here"+srcString);

            JSONObject jsonObj = new JSONObject(jsonResult);
            answerId = jsonObj.getInt("animal_id");
            accuracyNum = jsonObj.getInt("accuracy");

            Log.d(TAG, "It gets JSONObject");
            Intent intent = new Intent(UploadToServerActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ansId", String.valueOf(answerId));
            bundle.putString("accNum", String.valueOf(accuracyNum));

            intent.putExtras(bundle);
            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    */

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
            Intent intent = new Intent(UploadToServerActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
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
            bundle.putString("ansId", json_res_s);

            intent.putExtras(bundle);
            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
