package com.example.android.redroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCollection extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = FragmentCollection.class.getSimpleName();
    private static final String BOOKS_REQUEST_COLLECTION_URL = "https://www.googleapis.com/books/v1/volumes?q=harari&maxResults=10";
    private static final int BOOKS_REQUEST_COLLCTION_ID = 0;
    private RecyclerView recyclerViewCollection;
    BookAdapter bookAdapter;

    public FragmentCollection() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collection, container, false);

        bookAdapter = new BookAdapter();
        RecyclerView.LayoutManager layoutManagerCollection = new GridLayoutManager(getActivity(), 2);

        recyclerViewCollection = (RecyclerView) rootView.findViewById(R.id.recycler_view_collection);
        recyclerViewCollection.setHasFixedSize(true);
        recyclerViewCollection.setLayoutManager(layoutManagerCollection);
        recyclerViewCollection.setAdapter(bookAdapter);

        getLoaderManager().initLoader(BOOKS_REQUEST_COLLCTION_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(getActivity(), BOOKS_REQUEST_COLLECTION_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bookAdapter.AddAll(data);
//        recyclerViewCollection.setAdapter(new BookAdapter(new ArrayList<>(data)));
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }

}
