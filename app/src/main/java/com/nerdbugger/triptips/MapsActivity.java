package com.nerdbugger.triptips;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nerdbugger.triptips.utils.MapDirectionsParser;
import com.nerdbugger.triptips.utils.TripTipsController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    ImageView popup_iv;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Marker userMarker, locationMarker;
    LatLngBounds.Builder builder;
    LatLng locationLatlng;
    CameraUpdate cu;
    ArrayList<Marker> markers = new ArrayList<>();
    private GoogleMap mMap;
    private ArrayList<LatLng> traceOfMe = null;
    private Polyline mPolyline = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        popup_iv = findViewById(R.id.menu_iv);

        popup_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle extras = getIntent().getExtras();

        String locationName = extras.getString("locationName");
        double locationlatitude = extras.getDouble("latitude");
        double locationlongitude = extras.getDouble("longitude");

        locationLatlng = new LatLng(locationlatitude, locationlongitude);

        MarkerOptions options = new MarkerOptions().title(locationName).position(new LatLng(locationlatitude, locationlongitude));
        locationMarker = mMap.addMarker(options);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(locationlatitude, locationlongitude), 10);
        mMap.animateCamera(update);

        MapStyleOptions styleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json);
        googleMap.setMapStyle(styleOptions);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.map_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.normal:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case R.id.satelite:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case R.id.hybrid:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                }

                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "Can't get location", Toast.LENGTH_SHORT).show();
        } else {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            Log.e("Location", "Latitude: " + location.getLatitude());
            //Log.e("Location", "Longitude: "+location.getLongitude());
            setMarker("You", latLng);
            traceMe(latLng, locationLatlng);
            /*CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mMap.animateCamera(update);*/
        }
    }

    public void setMarker(String title, LatLng latLng) {

        if (userMarker != null) {
            userMarker.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .title(title)
                .position(latLng);
        userMarker = mMap.addMarker(options);
    }



   /* public void showMap() {

        //mMap.clear();
        //Create your Markers List

        //Add them to your list
        markers.add(userMarker);
        markers.add(locationMarker);


//get the latLngbuilder from the marker list
        builder = new LatLngBounds.Builder();
        for (Marker m : markers) {
            builder.include(m.getPosition());
        }

//Bounds padding here
        int padding = 50;

        //Create bounds here
        LatLngBounds bounds = builder.build();

//Create camera with bounds
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

//Check map is loaded
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //animate camera here
                mMap.animateCamera(cu);

            }
        });


    }*/

    private void traceMe(LatLng srcLatLng, LatLng destLatLng) {

        String srcParam = srcLatLng.latitude + "," + srcLatLng.longitude;
        String destParam = destLatLng.latitude + "," + destLatLng.longitude;

        String modes[] = {"driving", "walking", "bicycling", "transit"};

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + srcParam + "&destination=" + destParam + "&sensor=false&units=metric&mode=walking";
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MapDirectionsParser parser = new MapDirectionsParser();
                        List<List<HashMap<String, String>>> routes = null;
                        try {
                            routes = parser.parse(new JSONObject(response));

                            ArrayList<LatLng> points = null;

                            for (int i = 0; i < routes.size(); i++) {
                                points = new ArrayList<LatLng>();
                                // lineOptions = new PolylineOptions();

                                // Fetching i-th route
                                List<HashMap<String, String>> path = routes.get(i);

                                // Fetching all the points in i-th route
                                for (int j = 0; j < path.size(); j++) {
                                    HashMap<String, String> point = path.get(j);

                                    double lat = Double.parseDouble(point.get("lat"));
                                    double lng = Double.parseDouble(point.get("lng"));
                                    LatLng position = new LatLng(lat, lng);

                                    points.add(position);
                                }
                            }

                            drawPoints(points, mMap);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                });

        //MyApplication.getInstance().addToReqQueue(jsonObjectRequest);
        TripTipsController.getController().addToRequestQueue(jsonObjectRequest);

    }


    private void drawPoints(ArrayList<LatLng> points, GoogleMap mMaps) {
        if (points == null) {
            return;
        }
        traceOfMe = points;
        PolylineOptions polylineOpt = new PolylineOptions();
        for (LatLng latlng : traceOfMe) {
            polylineOpt.add(latlng);
        }
        polylineOpt.color(Color.BLUE);
        if (mPolyline != null) {
            mPolyline.remove();
            mPolyline = null;
        }
        if (mMap != null) {
            mPolyline = mMap.addPolyline(polylineOpt);

        } else {

        }
        if (mPolyline != null)
            mPolyline.setWidth(10);
    }
}
