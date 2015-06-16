package com.example.kange1.bert;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    Button b1;

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
                TextView text = (TextView)findViewById(R.id.textView5);
                text.setText(responseFromServer);
            }
        });

    }

    /*private String delimiter = "--";
    private String boundary =  "SwA"+Long.toString(System.currentTimeMillis())+"SwA";
    OutputStream os;

    public void connectForMultipart() throws Exception {

        con = (HttpURLConnection) ( new URL(urlServer)).openConnection();
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        con.connect();
        os = con.getOutputStream();
    }

    public void addFormPart(String paramName, String value) throws Exception {
        writeParamData(paramName, value);
    }

    private void writeParamData(String paramName, String value) throws Exception {
        os.write( (delimiter + boundary + "\r\n").getBytes());
        os.write( "Content-Type: text/plain\r\n".getBytes());
        os.write( ("Content-Disposition: form-data; name=\"" + paramName + "\"\r\n").getBytes());;
        os.write( ("\r\n" + value + "\r\n").getBytes());

    }

    public void addFilePart(String paramName, String fileName, byte[] data) throws Exception {
        os.write((delimiter + boundary + "\r\n").getBytes());
        os.write(("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"\r\n").getBytes());
        os.write(("Content-Type: application/octet-stream\r\n").getBytes());
        os.write(("Content-Transfer-Encoding: binary\r\n").getBytes());
        os.write("\r\n".getBytes());

        os.write(data);

        os.write("\r\n".getBytes());
    }

    private class supLoadToServer extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }

    /*@SuppressWarnings("deprecation")
    private class uploadFileToServer extends AsyncTask<Void, Integer, String>{


        @Override
        protected String doInBackground(Void... params) {
            return uploadAction();
        }

        private String uploadAction(){
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(urlServer);
            try {
                multipartEntity = MultipartEntityBuilder.create();
        }
    }*/

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

            //ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //String response = httpclient.execute(httpPost, responseHandler);

            //String reverseString = response;


            HttpResponse response = httpclient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
            responseFromServer = responseBody;


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
