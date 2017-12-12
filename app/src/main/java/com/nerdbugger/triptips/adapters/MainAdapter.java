package com.nerdbugger.triptips.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nerdbugger.triptips.LocationInfoActivity;
import com.nerdbugger.triptips.R;
import com.nerdbugger.triptips.models.LocationsModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<LocationsModel> locationsModels;
    private Context context;

    public MainAdapter(Context context, ArrayList<LocationsModel> locationsModels) {
        this.context = context;
        this.locationsModels = locationsModels;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        final LocationsModel model = locationsModels.get(position);
        //holder.iv.setImageResource(model.getImgId());
        holder.tv.setText(model.getLocationName());

        switch (model.getLocationid()){
            case 1:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.boga1));
                break;
            case 2:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.chandra1));
                break;
            case 3:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.war1));
                break;
            case 4:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.foys1));
                break;
            case 5:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.kaptai1));
                break;
            case 6:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.nilgiri1));
                break;
            case 7:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.parki1));
                break;
            case 8:
                holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.patenga1));
                break;
        }

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = model.getLocationid();
                Intent intent = new Intent(context, LocationInfoActivity.class);
                intent.putExtra("locationid", id);
                intent.putExtra("locationName", model.getLocationName());
                intent.putExtra("latitude", model.getLatitude());
                intent.putExtra("longitude", model.getLongitude());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout root;
        ImageView iv;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.itemll);
            iv = itemView.findViewById(R.id.locationIV);
            tv = itemView.findViewById(R.id.locationNameTV);
        }
    }
}
