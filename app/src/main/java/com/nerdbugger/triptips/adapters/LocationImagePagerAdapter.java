package com.nerdbugger.triptips.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nerdbugger.triptips.R;
import com.nerdbugger.triptips.fragments.LocationImageFragment;

/**
 * Created by oli on 12/14/17.
 */

public class LocationImagePagerAdapter extends FragmentStatePagerAdapter {

    int locid;

    public LocationImagePagerAdapter(FragmentManager fm, int locid) {
        super(fm);
        this.locid = locid;
    }

    @Override
    public Fragment getItem(int position) {
        return LocationImageFragment.getInstance(locid, position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
