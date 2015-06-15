package com.example.kange1.bert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class UploadToServerActivity extends Activity {

    ArrayList<Integer> xPoint, yPoint;
    byte[] img;
    String imageData;
    Button b1;

    String urlServer = "http://52.7.19.214/imageHandler.php";
    HttpURLConnection connection = null;

    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_to_server);

        b1 = (Button)findViewById(R.id.button);

        Intent intent = getIntent();

        xPoint = intent.getIntegerArrayListExtra("xData");
        yPoint = intent.getIntegerArrayListExtra("yData");
        img = intent.getByteArrayExtra("byteArray");

        imageData = Base64.encodeToString(img, Base64.DEFAULT);

        nameValuePairs.add(new BasicNameValuePair("imageData", imageData));
        nameValuePairs.add(new BasicNameValuePair("xData", xPoint.toString()));
        nameValuePairs.add(new BasicNameValuePair("xData", yPoint.toString()));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadToServer();
                    }
                });
                t.start();
            }
        });

    }

    public boolean uploadToServer() {
        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(urlServer);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            Toast.makeText(this.getApplicationContext(), "UGHGHGHGHG", Toast.LENGTH_LONG).show();
            HttpResponse response = httpclient.execute(httpPost);
            Toast.makeText(this.getApplicationContext(), "WOh", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upload_to_server, menu);
        return true;
    }

    public void triggerImageUpload() {
        makeHTTPCall();
    }

    public void makeHTTPCall() {

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
