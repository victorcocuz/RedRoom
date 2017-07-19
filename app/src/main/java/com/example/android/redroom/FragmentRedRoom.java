package com.example.android.redroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRedRoom extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = FragmentRedRoom.class.getSimpleName();
    private static final String BOOKS_REQUEST_USER1_URL = "https://www.googleapis.com/books/v1/volumes?q=harari&maxResults=10";
    private static final String BOOKS_REQUEST_USER2_URL = "https://www.googleapis.com/books/v1/volumes?q=federer&maxResults=10";
    private static final String BOOKS_REQUEST_USER3_URL = "https://www.googleapis.com/books/v1/volumes?q=potter&maxResults=10";
    private static final int BOOKS_REQUEST_USER1_ID = 0;
    private static final int BOOKS_REQUEST_USER2_ID = 1;
    private static final int BOOKS_REQUEST_USER3_ID = 2;
    private RecyclerView recyclerViewUser1;
    private RecyclerView recyclerViewUser2;
    private RecyclerView recyclerViewUser3;
    private BookAdapter bookAdapter;

    public FragmentRedRoom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_red_room, container, false);

        bookAdapter = new BookAdapter();

        //RecyclerView User 1
        recyclerViewUser1 = (RecyclerView) rootView.findViewById(R.id.recycler_view_red_room1);
        recyclerViewUser1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerUser1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUser1.setLayoutManager(layoutManagerUser1);
        recyclerViewUser1.setAdapter(bookAdapter);

        //RecyclerView User 1
        recyclerViewUser2 = (RecyclerView) rootView.findViewById(R.id.recycler_view_red_room2);
        recyclerViewUser2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerUser2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUser2.setLayoutManager(layoutManagerUser2);
        recyclerViewUser2.setAdapter(bookAdapter);

        //RecyclerView User 1
        recyclerViewUser3 = (RecyclerView) rootView.findViewById(R.id.recycler_view_red_room3);
        recyclerViewUser3.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerUser3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUser3.setLayoutManager(layoutManagerUser3);
        recyclerViewUser3.setAdapter(bookAdapter);

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
        bookAdapter.AddAll(data);
//        switch (loader.getId()) {
//            case 0:
//                    recyclerViewUser1.invalidate();
//                    recyclerViewUser1.setAdapter(new BookAdapter(new List<>(data)));
//                break;
//            case 1:
////                recyclerViewUser2.invalidate();
////                recyclerViewUser2.setAdapter(new BookAdapter(new ArrayList<>(data)));
//                break;
//            case 2:
////                recyclerViewUser3.invalidate();
////                recyclerViewUser3.setAdapter(new BookAdapter(new ArrayList<>(data)));
//                break;
//        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
//        switch (loader.getId()) {
//            case 0:
//                Log.e(LOG_TAG, "Cancel 0");
//                recyclerViewUser1.setAdapter(null);
//                break;
//            case 1:
//                Log.e(LOG_TAG, "Cancel 0");
//                recyclerViewUser2.setAdapter(null);
//                break;
//            case 2:
//                Log.e(LOG_TAG, "Cancel 0");
//                recyclerViewUser3.setAdapter(null);
//                break;
//        }
//        recyclerViewUser1.setAdapter(null);
//        recyclerViewUser2.setAdapter(null);
//        recyclerViewUser3.setAdapter(null);
    }
}
