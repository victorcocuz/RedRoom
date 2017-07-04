package com.example.android.redroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBrowse extends Fragment implements LoaderManager.LoaderCallbacks<List<VolumeCard>>{

    private static final String LOG_TAG = FragmentBrowse.class.getSimpleName();
    private static final String BOOKS_REQUEST_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final int EARTHQUAKE_LOADER_ID = 0;
    private RecyclerView.Adapter volumeAdapter;
    private EditText searchView;
    private String finalURL;

    public FragmentBrowse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);

        ArrayList<VolumeCard> volumes = new ArrayList<>();
        volumes.add(new VolumeCard("title 1", "author 1"));
        volumes.add(new VolumeCard("title 2", "author 3"));
        volumes.add(new VolumeCard("title 3", "author 3"));
        volumes.add(new VolumeCard("title 4", "author 4"));
        volumes.add(new VolumeCard("title 5", "author 5"));
        volumes.add(new VolumeCard("title 6", "author 6"));

        //Recycler View
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        volumeAdapter = new VolumeAdapter(volumes);
        recyclerView.setAdapter(volumeAdapter);

        //Search editor on enter listener
        searchView = (EditText) rootView.findViewById(R.id.search_view);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    StringBuilder requestUrl = new StringBuilder(BOOKS_REQUEST_BASE_URL);
                    requestUrl.append(searchView.getText().toString());
                    finalURL = requestUrl.toString();
                    Toast.makeText(getActivity(), finalURL, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public Loader<List<VolumeCard>> onCreateLoader(int id, Bundle args) {
        return new VolumeCardLoader(getActivity(), finalURL);
    }

    @Override
    public void onLoadFinished(Loader<List<VolumeCard>> loader, List<VolumeCard> data) {
        volumeAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<VolumeCard>> loader) {

    }
}
