package com.nerdbugger.triptips.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by oli on 9/20/17.
 */

public class GPSTracker implements LocationListener {

    private static final long MIN_DIST_CHANGE_FOR_UPDATE = 1;
    private static final long MIN_TIME_CHANGE_FOR_UPDATE = 1;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;

    Location currentLocation = null;
    double latitude;
    double longitude;
    LocationManager locationManager;
    private Context context;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;

    public GPSTracker(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public Location getCurrentLocation() {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.e("isGPSEnabled", isGPSEnabled + "");
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.e("isNetworkEnabled", isNetworkEnabled + "");

        if (isGPSEnabled || isNetworkEnabled) {
            this.canGetLocation = true;

            if (isGPSEnabled) {
                if (currentLocation == null) {

                    Log.e("gps", "location null");
                    int permissionCheck = ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

                        Log.e("gps", "permission granted");
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                MIN_TIME_CHANGE_FOR_UPDATE, MIN_DIST_CHANGE_FOR_UPDATE, this);
                        if(locationManager!=null){
                            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(currentLocation!=null){
                                latitude = currentLocation.getLatitude();
                                longitude = currentLocation.getLongitude();
                            }
                        }

                    } else {
                        //permission not granted;
                        Log.e("gps", "permission not granted");
                    }
                }
            }
        }

        return currentLocation;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
