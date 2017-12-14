package com.nerdbugger.triptips.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        holder.iv.setOnClickListener(new View.OnClickListener() {
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

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopup(view);
            }
        });
    }

    private void showpopup(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_favourite, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_item_fav:
                        Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return locationsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv, overflow;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.thumbnail);
            tv = itemView.findViewById(R.id.titletv);
            overflow = itemView.findViewById(R.id.overflow);
        }
    }
}
