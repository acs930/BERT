package com.example.kange1.bert;

import android.app.Activity;
import android.app.ProgressDialog;
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
import java.io.File;
import java.io.IOException;
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

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    String urlServer = "http://52.3.50.112/imageHandler.php";
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
            bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"),
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
            nameValuePairs.add(new BasicNameValuePair("yData", yDat));
            Log.d(TAG, "xp: " + xPoint.toString());
            Log.d(TAG, "yp: " + yPoint.toString());
            Log.d(TAG, "im: " + imageData.length());

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpclient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
            responseFromServer = responseBody;

            progressBar = new ProgressDialog(v1.getContext());
            progressBar.setCancelable(false);
            progressBar.setMessage("Loading ...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();

            //reset progress bar status, filesize
            progressBarStatus = 0;
            fileSize = 0;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progressBarStatus < 100) {
                        progressBarStatus = doSomeTasks();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressBarHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(progressBarStatus);
                            }
                        });
                    }

                    // file is downloaded
                    if (progressBarStatus >= 100) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressBar.dismiss();
                    }
                }
            }).start();

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

}
