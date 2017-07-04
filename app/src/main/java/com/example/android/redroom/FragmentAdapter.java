package com.example.android.redroom;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by victo on 7/2/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentRedRoom();
            case 1:
                return new FragmentBrowse();
            case 2:
                return new FragmentCollection();
            case 3:
                return new FragmentBorrowed();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.fragment_red_room);
            case 1:
                return context.getString(R.string.fragment_browse);
            case 2:
                return context.getString(R.string.fragment_collection);
            case 3:
                return context.getString(R.string.fragment_borrow);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
