package com.example.android.redroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

public class FragmentBrowse extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = FragmentBrowse.class.getSimpleName();
    private static final int CARD_TYPE = 0;
    private static final String BOOKS_REQUEST_GENRE1_URL = "https://www.googleapis.com/books/v1/volumes?q=fantasy&maxResults=10";
    private static final String BOOKS_REQUEST_GENRE2_URL = "https://www.googleapis.com/books/v1/volumes?q=biography&maxResults=10";
    private static final String BOOKS_REQUEST_GENRE3_URL = "https://www.googleapis.com/books/v1/volumes?q=thriller&maxResults=10";
    private static final int BOOKS_REQUEST_GENRE1_ID = 0;
    private static final int BOOKS_REQUEST_GENRE2_ID = 1;
    private static final int BOOKS_REQUEST_GENRE3_ID = 2;
    private BookAdapter bookAdapterGenre1;
    private BookAdapter bookAdapterGenre2;
    private BookAdapter bookAdapterGenre3;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;

    public FragmentBrowse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);

        bookAdapterGenre1 = new BookAdapter(CARD_TYPE);
        bookAdapterGenre2 = new BookAdapter(CARD_TYPE);
        bookAdapterGenre3 = new BookAdapter(CARD_TYPE);

        progressBar1 = (ProgressBar) rootView.findViewById(R.id.loading_spinner_browse_1);
        progressBar2 = (ProgressBar) rootView.findViewById(R.id.loading_spinner_browse_2);
        progressBar3 = (ProgressBar) rootView.findViewById(R.id.loading_spinner_browse_3);

        //Recycler View Genre 1
        RecyclerView recyclerViewGenre1 = (RecyclerView) rootView.findViewById(R.id.recycler_view_genre1);
        recyclerViewGenre1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerGenre1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenre1.setLayoutManager(layoutManagerGenre1);
        recyclerViewGenre1.setAdapter(bookAdapterGenre1);

        //Recycler View Genre 2
        RecyclerView recyclerViewGenre2 = (RecyclerView) rootView.findViewById(R.id.recycler_view_genre2);
        recyclerViewGenre2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerGenre2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenre2.setLayoutManager(layoutManagerGenre2);
        recyclerViewGenre2.setAdapter(bookAdapterGenre2);

        //Recycler View Genre 3
        RecyclerView recyclerViewGenre3 = (RecyclerView) rootView.findViewById(R.id.recycler_view_genre3);
        recyclerViewGenre3.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerGenre3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGenre3.setLayoutManager(layoutManagerGenre3);
        recyclerViewGenre3.setAdapter(bookAdapterGenre3);

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
        switch (loader.getId()) {
            case 0:
                bookAdapterGenre1.AddAll(data);
                progressBar1.setVisibility(View.GONE);
                break;
            case 1:
                bookAdapterGenre2.AddAll(data);
                progressBar2.setVisibility(View.GONE);
                break;
            case 2:
                bookAdapterGenre3.AddAll(data);
                progressBar3.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
    }
}
