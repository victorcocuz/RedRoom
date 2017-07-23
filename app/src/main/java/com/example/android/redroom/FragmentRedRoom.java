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

public class FragmentRedRoom extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = FragmentRedRoom.class.getSimpleName();
    private static final int CARD_TYPE = 0;
    private static final String BOOKS_REQUEST_USER1_URL = "https://www.googleapis.com/books/v1/volumes?q=harari&maxResults=10";
    private static final String BOOKS_REQUEST_USER2_URL = "https://www.googleapis.com/books/v1/volumes?q=federer&maxResults=10";
    private static final String BOOKS_REQUEST_USER3_URL = "https://www.googleapis.com/books/v1/volumes?q=potter&maxResults=10";
    private static final int BOOKS_REQUEST_USER1_ID = 0;
    private static final int BOOKS_REQUEST_USER2_ID = 1;
    private static final int BOOKS_REQUEST_USER3_ID = 2;
    private BookAdapter bookAdapterUser1;
    private BookAdapter bookAdapterUser2;
    private BookAdapter bookAdapterUser3;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;

    public FragmentRedRoom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_red_room, container, false);

        bookAdapterUser1 = new BookAdapter(CARD_TYPE);
        bookAdapterUser2 = new BookAdapter(CARD_TYPE);
        bookAdapterUser3 = new BookAdapter(CARD_TYPE);

        progressBar1 = (ProgressBar) rootView.findViewById(R.id.loading_spinner_red_room_1);
        progressBar2 = (ProgressBar) rootView.findViewById(R.id.loading_spinner_red_room_2);
        progressBar3 = (ProgressBar) rootView.findViewById(R.id.loading_spinner_red_room_3);

        //RecyclerView User 1
        RecyclerView recyclerViewUser1 = (RecyclerView) rootView.findViewById(R.id.recycler_view_red_room1);
        recyclerViewUser1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerUser1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUser1.setLayoutManager(layoutManagerUser1);
        recyclerViewUser1.setAdapter(bookAdapterUser1);

        //RecyclerView User 2
        RecyclerView recyclerViewUser2 = (RecyclerView) rootView.findViewById(R.id.recycler_view_red_room2);
        recyclerViewUser2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerUser2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUser2.setLayoutManager(layoutManagerUser2);
        recyclerViewUser2.setAdapter(bookAdapterUser2);

        //RecyclerView User 3
        RecyclerView recyclerViewUser3 = (RecyclerView) rootView.findViewById(R.id.recycler_view_red_room3);
        recyclerViewUser3.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerUser3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUser3.setLayoutManager(layoutManagerUser3);
        recyclerViewUser3.setAdapter(bookAdapterUser3);

        getLoaderManager().initLoader(BOOKS_REQUEST_USER1_ID, null, this).forceLoad();
        getLoaderManager().initLoader(BOOKS_REQUEST_USER2_ID, null, this).forceLoad();
        getLoaderManager().initLoader(BOOKS_REQUEST_USER3_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new BookLoader(getActivity(), BOOKS_REQUEST_USER1_URL);
            case 1:
                return new BookLoader(getActivity(), BOOKS_REQUEST_USER2_URL);
            case 2:
                return new BookLoader(getActivity(), BOOKS_REQUEST_USER3_URL);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        switch (loader.getId()) {
            case 0:
                bookAdapterUser1.AddAll(data);
                progressBar1.setVisibility(View.GONE);
                break;
            case 1:
                bookAdapterUser2.AddAll(data);
                progressBar2.setVisibility(View.GONE);
                break;
            case 2:
                bookAdapterUser3.AddAll(data);
                progressBar3.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
    }
}
