package com.example.android.redroom;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class ActivitySearch extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private final static int CARD_TYPE = 1;
    private static final int SEARCH_ACTIVITY_ID = 0;
    private String requestURL;
    private BookAdapter bookAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bookAdapter = new BookAdapter(CARD_TYPE);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner_search);

        requestURL = getIntent().getExtras().getString("requestURL");

        //Set Toolbar
        String searchWord = getIntent().getExtras().getString("searchWord");
        SearchToolbar searchToolbar = new SearchToolbar(ActivitySearch.this, this, searchWord);
        searchToolbar.runToolbar();

        //Set up recycler view;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);

        getSupportLoaderManager().initLoader(SEARCH_ACTIVITY_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, requestURL);
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
