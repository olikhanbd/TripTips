package com.nerdbugger.triptips;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.nerdbugger.triptips.R.id.map_btn;

public class LocationInfoActivity extends AppCompatActivity {

    private final int MY_REQUEST_CODE = 1;

    ImageView iv;
    TextView tv;
    Button btn;

    double latitude;
    double longitude;
    String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("locationid");
        latitude = extras.getDouble("latitude");
        longitude = extras.getDouble("longitude");
        locationName = extras.getString("locationName");

        iv = (ImageView) findViewById(R.id.locationinfoIV);
        tv = (TextView) findViewById(R.id.info_tv);
        btn = (Button) findViewById(map_btn);


        switch (id){
            case 1:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.boga1));
                break;
            case 2:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.chandra1));
                break;
            case 3:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.war1));
                break;
            case 4:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.foys1));
                break;
            case 5:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.kaptai1));
                break;
            case 6:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.nilgiri1));
                break;
            case 7:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.parki1));
                break;
            case 8:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.patenga1));
                break;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ActivityCompat.checkSelfPermission(LocationInfoActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LocationInfoActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LocationInfoActivity.this,
                        Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(LocationInfoActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_REQUEST_CODE);
                } else{

                    Intent intent = new Intent(LocationInfoActivity.this, MapsActivity.class);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    intent.putExtra("locationName", locationName);

                    startActivity(intent);
                }


            }
        });

        setupUI(id);
    }

    void setupUI(int id){
        switch (id){
            case 1:
                //iv.setImageResource(R.drawable.boga_lake_original);
                tv.setText(getResources().getString(R.string.bogalake_info));
                break;
            case 2:
                tv.setText(getResources().getString(R.string.chandranath_info));
                break;
            case 3:
                tv.setText(getResources().getString(R.string.warcemetary_info));
                break;
            case 4:
                tv.setText(getString(R.string.foyslake_info));
                break;
            case 5:
                tv.setText(getResources().getString(R.string.kaptai_info));
                break;
            case 6:
                tv.setText(getResources().getString(R.string.nilgiri_info));
                break;
            case 7:
                tv.setText(getResources().getString(R.string.parki_info));
                break;
            case 8:
                tv.setText(getResources().getString(R.string.patenga_info));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_REQUEST_CODE:
                if(grantResults.length>0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED){

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
