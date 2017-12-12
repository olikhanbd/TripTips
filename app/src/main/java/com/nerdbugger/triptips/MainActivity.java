package com.nerdbugger.triptips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nerdbugger.triptips.adapters.MainAdapter;
import com.nerdbugger.triptips.models.LocationsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;

    ArrayList<LocationsModel> locationsModels;

    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationsModels = new ArrayList<>();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MainAdapter(this, locationsModels);
        rv.setAdapter(adapter);

        setupData();
    }

    void setupData() {
        LocationsModel model;

        model = new LocationsModel();
        model.setLocationid(1);
        //model.setImgid(R.drawable.boga_lake_original);
        model.setLocationName("Boga Lake");
        model.setLatitude(21.976633);
        model.setLongitude(92.462209);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(2);
        //model.setImgid(R.drawable.chandranath_temple_original);
        model.setLocationName("Chandranath Temple");
        model.setLatitude(22.603032);
        model.setLongitude(91.677560);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(3);
        //model.setImgid(R.drawable.chittagong_war_cemetery_original);
        model.setLocationName("Chittagong war cemetary");
        model.setLatitude(22.357472);
        model.setLongitude(91.828163);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(4);
        //model.setImgid(R.drawable.foys_lake_original);
        model.setLocationName("Foys Lake");
        model.setLatitude(22.365605);
        model.setLongitude(91.796758);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(5);
        //model.setImgid(R.drawable.kaptai_original);
        model.setLocationName("Kaptai");
        model.setLatitude(22.498956);
        model.setLongitude(92.216129);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(6);
        //model.setImgid(R.drawable.nil_giri_original);
        model.setLocationName("Nilgiri");
        model.setLatitude(21.911723);
        model.setLongitude(92.325414);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(7);
        //model.setImgid(R.drawable.parki_sea_beach_original);
        model.setLocationName("Parki Sea Beach");
        model.setLatitude(22.192948);
        model.setLongitude(91.815104);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(8);
        // model.setImgid(R.drawable.patenga_original);
        model.setLocationName("Patenga");
        model.setLatitude(22.234723);
        model.setLongitude(91.792508);
        locationsModels.add(model);

       /* model = new LocationsModel();
        model.setLocationid(9);
        //model.setImgid(R.drawable.peda_ting_ting_original);
        model.setLocationName("Peda Ting Ting");
        model.setLatitude(21.976633);
        model.setLongitude(92.462209);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(10);
        //model.setImgid(R.drawable.rangamati_original);
        model.setLocationName("Rangamati");
        model.setLatitude(21.976633);
        model.setLongitude(92.462209);
        locationsModels.add(model);

        model = new LocationsModel();
        model.setLocationid(11);
        //model.setImgid(R.drawable.sangu_river_original);
        model.setLocationName("Sangu River");
        model.setLatitude(21.976633);
        model.setLongitude(92.462209);
        locationsModels.add(model);*/

    }
}
