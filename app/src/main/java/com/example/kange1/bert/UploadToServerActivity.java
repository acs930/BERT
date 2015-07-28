package com.example.kange1.bert;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class UploadToServerActivity extends Activity {

    ArrayList<Integer> xPoint, yPoint;
    byte[] img;
    String imageData;
    Button b1, b2;
    ImageView v1;

    String urlServer = "http://52.7.19.214/imageHandler.php";
    //HttpURLConnection connection = null;

    HttpURLConnection con = null;
    String responseFromServer;
    private static final String  TAG = UploadToServerActivity.class.getSimpleName();

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
        img = intent.getByteArrayExtra("byteArray");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "hello");
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadToServer();
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

        if(getIntent().hasExtra("byteArray")) {
            //ImageView previewThumbnail = new ImageView(this);
            Bitmap bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"),
                    0, getIntent().getByteArrayExtra("byteArray").length);
            //previewThumbnail.setImageBitmap(bp);
            v1.setImageBitmap(bp);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean uploadToServer() {
        try {
            String responseString = null;
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(urlServer);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            imageData = Base64.encodeToString(img, Base64.DEFAULT);
            String xDat = xPoint.toString();
            String yDat = yPoint.toString();

            nameValuePairs.add(new BasicNameValuePair("imageData", imageData));
            nameValuePairs.add(new BasicNameValuePair("xData", xDat));
            nameValuePairs.add(new BasicNameValuePair("xData", yDat));
            Log.d(TAG, "xp: " + xPoint.toString());
            Log.d(TAG, "yp: " + yPoint.toString());
            Log.d(TAG, "im: " + imageData.length());

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpclient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
            responseFromServer = responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void triggerImageUpload() {
        makeHTTPCall();
    }

    public void makeHTTPCall() {
    }
}
