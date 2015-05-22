package com.example.kange1.bert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.maps.model.CameraPosition;

/**
 * Created by kange1 on 5/22/2015.
 */
public class Preview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "Preview";

    private SurfaceHolder mHolder;
    public Camera camera;

    public Preview(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);

            camera.setPreviewCallback(new PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    Bitmap bitmapPicture = BitmapFactory.decodeByteArray(data, 0, data.length);
                    File imageDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "surbeyImg");
                    imageDirectory.mkdirs();
                    File f = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "surbeyImg" + File.separator + "a.jpg");

                    try {
                        f.createNewFile();
                        FileOutputStream fo = new FileOutputStream(f);
                        fo.write(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                    }
                        Preview.this.invalidate();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(width, height);
        camera.setParameters(parameters);
        camera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera = null;
    }

}
