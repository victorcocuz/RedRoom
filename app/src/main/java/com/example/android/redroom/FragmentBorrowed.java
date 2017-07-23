package com.example.android.redroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

public class FragmentBorrowed extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = FragmentBorrowed.class.getSimpleName();
    private static final int CARD_TYPE = 1;
    private static final String BOOKS_REQUEST_BORROWED_URL = "https://www.googleapis.com/books/v1/volumes?q=cooking&maxResults=10";
    private static final int BOOKS_REQUEST_BORROWED_ID = 0;
    BookAdapter bookAdapter;
    private ProgressBar progressBar;

    public FragmentBorrowed() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_borrowed, container, false);

        bookAdapter = new BookAdapter(CARD_TYPE);
        progressBar = (ProgressBar) rootView.findViewById(R.id.loading_spinner_borrow);

        RecyclerView.LayoutManager layoutManagerBorrowed = new GridLayoutManager(getActivity(), 2);
        RecyclerView recyclerViewBorrowed = (RecyclerView) rootView.findViewById(R.id.recycler_view_borrowed);
        recyclerViewBorrowed.setHasFixedSize(true);
        recyclerViewBorrowed.setAdapter(bookAdapter);
        recyclerViewBorrowed.setLayoutManager(layoutManagerBorrowed);

        getLoaderManager().initLoader(BOOKS_REQUEST_BORROWED_ID, null, this).forceLoad();

        return rootView;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(getActivity(), BOOKS_REQUEST_BORROWED_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bookAdapter.AddAll(data);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
    }
}
