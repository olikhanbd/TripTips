package com.nerdbugger.triptips;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nerdbugger.triptips.adapters.LocationImagePagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

import static com.nerdbugger.triptips.R.id.map_btn;

public class LocationInfoActivity extends AppCompatActivity {

    private final int MY_REQUEST_CODE = 1;

    Toolbar toolbar;
    TextView tv;
    Button btn;
    ViewPager viewPager;
    LocationImagePagerAdapter adapter;

    double latitude;
    double longitude;
    String locationName;

    Timer timer;
    boolean progress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("locationid");
        latitude = extras.getDouble("latitude");
        longitude = extras.getDouble("longitude");
        locationName = extras.getString("locationName");

        tv = (TextView) findViewById(R.id.info_tv);
        btn = (Button) findViewById(map_btn);
        viewPager = findViewById(R.id.image_pager);
        toolbar = findViewById(R.id.locinfotb);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        adapter = new LocationImagePagerAdapter(getSupportFragmentManager(), id);
        viewPager.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(LocationInfoActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LocationInfoActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LocationInfoActivity.this,
                        Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocationInfoActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_REQUEST_CODE);
                } else {

                    Intent intent = new Intent(LocationInfoActivity.this, MapsActivity.class);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    intent.putExtra("locationName", locationName);

                    startActivity(intent);
                }


            }
        });

        setupUI(id);
        slider(0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void slider(final int initialPosition) {
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // As the TimerTask run on a separate thread from UI thread we have
                // to call runOnUiThread to do work on UI thread.
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (viewPager.getCurrentItem() == 4) {
                            //viewPager.setCurrentItem(initialPosition);
                            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                            progress = true;
                            //setView(viewPager.getCurrentItem());

                        } else if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            progress = false;

                        } else {

                            if (progress)
                                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                            else
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 4000, 4000);
    }

    void setupUI(int id) {
        switch (id) {
            case 1:
                //iv.setImageResource(R.drawable.boga_lake_original);
                toolbar.setTitle("Boga Lake");
                tv.setText(getResources().getString(R.string.bogalake_info));
                break;
            case 2:
                toolbar.setTitle("Chandranath Temple");
                tv.setText(getResources().getString(R.string.chandranath_info));
                break;
            case 3:
                toolbar.setTitle("Chittagong War Cemetery");
                tv.setText(getResources().getString(R.string.warcemetary_info));
                break;
            case 4:
                toolbar.setTitle("Foy's Lake");
                tv.setText(getString(R.string.foyslake_info));
                break;
            case 5:
                toolbar.setTitle("Kaptai");
                tv.setText(getResources().getString(R.string.kaptai_info));
                break;
            case 6:
                toolbar.setTitle("Nilgiri");
                tv.setText(getResources().getString(R.string.nilgiri_info));
                break;
            case 7:
                toolbar.setTitle("Parki Beach");
                tv.setText(getResources().getString(R.string.parki_info));
                break;
            case 8:
                toolbar.setTitle("Patenga");
                tv.setText(getResources().getString(R.string.patenga_info));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST_CODE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(LocationInfoActivity.this, MapsActivity.class);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    intent.putExtra("locationName", locationName);

                    startActivity(intent);

                } else {
                    Toast.makeText(LocationInfoActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
