package com.example.android.redroom;


import android.content.Intent;
import android.net.Uri;
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
public class FragmentBrowse extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>>{

    private static final String LOG_TAG = FragmentBrowse.class.getSimpleName();
    private static final String BOOKS_REQUEST_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final int EARTHQUAKE_LOADER_ID = 0;
    private RecyclerView.Adapter volumeAdapter;
    private RecyclerView recyclerView;
    private EditText searchView;
    private String finalURL;

    public FragmentBrowse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);

        //Recycler View
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        volumeAdapter = new BookAdapter(new ArrayList<Book>());
        recyclerView.setAdapter(volumeAdapter);

        //Search editor on enter listener
        searchView = (EditText) rootView.findViewById(R.id.search_view);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    StringBuilder requestUrl = new StringBuilder(BOOKS_REQUEST_BASE_URL);
                    requestUrl.append(searchView.getText().toString());
                    requestUrl.append("&maxResults=10");
                    finalURL = requestUrl.toString();
                    Toast.makeText(getActivity(), finalURL, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    startActivity(intent.setData(Uri.parse(finalURL)));
                }
                return false;
            }
        });

        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(getActivity(), finalURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        recyclerView.setAdapter(new BookAdapter(new ArrayList<>(data)));
        recyclerView.invalidate();
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
    }
}
