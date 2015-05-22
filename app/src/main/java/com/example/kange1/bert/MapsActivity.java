package com.example.kange1.bert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import android.app.Fragment;
import android.util.Log;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


public class MapsActivity extends Activity {

    private static final LatLng Wentworth = new LatLng(42.3366, -71.0950);
    /** Local variables **/
    GoogleMap googleMap;
    /*CameraPosition cameraPosition = new CameraPosition.Builder()
            .target(Wentworth)
            .zoom(17)
            .bearing(90)
            .tilt(30)
            .build();*/

    private void createMapView() {
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();

                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(), "Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker() {
        if (null != googleMap) {
            googleMap.addMarker(new MarkerOptions().
                            position(new LatLng(42.3366, -71.0950)).
                            title("Marker").
                            draggable(true)
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        createMapView();
        addMarker();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Wentworth, 16));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
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
