package com.example.android.redroom;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by victo on 7/4/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String url;

    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> volumes = QueryUtils.extractVolumes(url);
        return volumes;
    }
}
