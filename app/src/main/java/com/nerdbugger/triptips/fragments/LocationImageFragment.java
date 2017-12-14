package com.nerdbugger.triptips.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nerdbugger.triptips.R;

/**
 * Created by oli on 12/14/17.
 */

public class LocationImageFragment extends Fragment {

    int locationid;
    int pos;
    Context mContext;

    ImageView imgview;

    public static LocationImageFragment getInstance(int locid, int position) {
        LocationImageFragment locationImageFragment = new LocationImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("locid", locid);
        bundle.putInt("position", position);
        locationImageFragment.setArguments(bundle);
        return locationImageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationid = getArguments().getInt("locid");
        pos = getArguments().getInt("position");
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imagepager, container, false);

        imgview = view.findViewById(R.id.imgpager_iv);

        if (locationid == 1) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boga1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boga2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boga3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boga4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boga5));
        } else if (locationid == 2) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chandra1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chandra2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chandra3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chandra4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chandra5));
        } else if (locationid == 3) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.war1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.war2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.war3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.war4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.war5));
        } else if (locationid == 4) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foys1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foys2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foys3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foys4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foys5));
        } else if (locationid == 5) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.kaptai1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.kaptai2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.kaptai3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.kaptai4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.kaptai5));
        } else if (locationid == 6) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nilgiri1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nilgiri2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nilgiri3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nilgiri4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nilgiri5));
        } else if (locationid == 7) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parki1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parki2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parki3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parki4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parki5));
        } else if (locationid == 8) {
            if (pos == 0) {
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.patenga1));
            } else if (pos == 1)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.patenga2));
            else if (pos == 2)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.patenga3));
            else if (pos == 3)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.patenga4));
            else if (pos == 4)
                imgview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.patenga5));
        }

        return view;
    }

}
