package com.example.android.redroom;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by victo on 7/4/2017.
 */

public class VolumeCardLoader extends AsyncTaskLoader<List<VolumeCard>> {

    public VolumeCardLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public List<VolumeCard> loadInBackground() {
        return null;
    }
}
