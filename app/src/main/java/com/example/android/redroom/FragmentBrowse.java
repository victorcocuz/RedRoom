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

import static android.R.attr.data;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBrowse extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = FragmentBrowse.class.getSimpleName();
    private static final String BOOKS_REQUEST_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String BOOKS_REQUEST_GENRE1_URL = "https://www.googleapis.com/books/v1/volumes?q=fantasy&maxResults=10";
    private static final String BOOKS_REQUEST_GENRE2_URL = "https://www.googleapis.com/books/v1/volumes?q=biography&maxResults=10";
    private static final String BOOKS_REQUEST_GENRE3_URL = "https://www.googleapis.com/books/v1/volumes?q=thriller&maxResults=10";
    private static final int BOOKS_REQUEST_GENRE1_ID = 0;
    private static final int BOOKS_REQUEST_GENRE2_ID = 1;
    private static final int BOOKS_REQUEST_GENRE3_ID = 2;
    private RecyclerView recyclerViewGenre1;
    private RecyclerView recyclerViewGenre2;
    private RecyclerView recyclerViewGenre3;
    private EditText searchView;
    private String finalURL;
    private BookAdapter bookAdapter;

    public FragmentBrowse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);

        bookAdapter = new BookAdapter();

        //Recycler View Genre 1
        recyclerViewGenre1 = (RecyclerView) rootView.findViewById(R.id.recycler_view_genre1);
        recyclerViewGenre1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerGenre1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenre1.setLayoutManager(layoutManagerGenre1);
        recyclerViewGenre1.setAdapter(bookAdapter);

        //Recycler View Genre 2
        recyclerViewGenre2 = (RecyclerView) rootView.findViewById(R.id.recycler_view_genre2);
        recyclerViewGenre2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerGenre2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenre2.setLayoutManager(layoutManagerGenre2);
        recyclerViewGenre2.setAdapter(bookAdapter);

        //Recycler View Genre 3
        recyclerViewGenre3 = (RecyclerView) rootView.findViewById(R.id.recycler_view_genre3);
        recyclerViewGenre3.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerGenre3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenre3.setLayoutManager(layoutManagerGenre3);
        recyclerViewGenre3.setAdapter(bookAdapter);

        //Search editor on enter listener
        searchView = (EditText) rootView.findViewById(R.id.search_view);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    StringBuilder requestUrl = new StringBuilder(BOOKS_REQUEST_BASE_URL);
                    requestUrl.append(searchView.getText().toString());
                    requestUrl.append("&maxResults=10");
                    Intent goToSearchActivity = new Intent(getActivity(), ActivitySearch.class);

                    finalURL = requestUrl.toString();
                    Toast.makeText(getActivity(), finalURL, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        getLoaderManager().initLoader(BOOKS_REQUEST_GENRE1_ID, null, this).forceLoad();
        getLoaderManager().initLoader(BOOKS_REQUEST_GENRE2_ID, null, this).forceLoad();
        getLoaderManager().initLoader(BOOKS_REQUEST_GENRE3_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new BookLoader(getActivity(), BOOKS_REQUEST_GENRE1_URL);
            case 1:
                return new BookLoader(getActivity(), BOOKS_REQUEST_GENRE2_URL);
            case 2:
                return new BookLoader(getActivity(), BOOKS_REQUEST_GENRE3_URL);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bookAdapter.AddAll(data);
//        switch (loader.getId()) {
//            case 0:
//                recyclerViewGenre1.setAdapter(new BookAdapter(new ArrayList<>(data)));
//                recyclerViewGenre1.invalidate();
//                break;
//            case 1:
//                recyclerViewGenre2.setAdapter(new BookAdapter(new ArrayList<>(data)));
//                recyclerViewGenre2.invalidate();
//                break;
//            case 2:
//                recyclerViewGenre3.setAdapter(new BookAdapter(new ArrayList<>(data)));
//                recyclerViewGenre3.invalidate();
//                break;
//        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
//        switch (loader.getId()) {
//            case 0:
//                recyclerViewGenre1.setAdapter(null);
//                break;
//            case 1:
//                recyclerViewGenre2.setAdapter(null);
//                break;
//            case 2:
//                recyclerViewGenre3.setAdapter(null);
//                break;
//        }
    }
}
