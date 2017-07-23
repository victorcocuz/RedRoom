package com.example.android.redroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by victo on 7/22/2017.
 */

public class SearchToolbar extends AppCompatActivity {

    private static final String BOOKS_REQUEST_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private Activity activity;
    private Context context;
    private String title;
    private TextView generalTitleView;
    private EditText searchView;
    private String searchWord;
    private String finalURL;

    public SearchToolbar(Activity activity,  Context context, String title) {
        this.activity = activity;
        this.context = context;
        this.title = title;
    }

    public void runToolbar() {

        //Set current fragment title
        generalTitleView = (TextView) activity.findViewById(R.id.general_title);
        generalTitleView.setText(title);

        //Search editor on enter listener
        searchView = (EditText) activity.findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    StringBuilder requestUrl = new StringBuilder(BOOKS_REQUEST_BASE_URL);
                    searchWord = searchView.getText().toString();
                    requestUrl.append(searchWord);
                    requestUrl.append("&maxResults=10");
                    finalURL = requestUrl.toString();

                    Intent goToSearchActivity = new Intent(context, ActivitySearch.class);
                    goToSearchActivity.putExtra("requestURL", finalURL);
                    goToSearchActivity.putExtra("searchWord", searchWord);
                    context.startActivity(goToSearchActivity);
                }
                return false;
            }
        });

        //Toolbar on click listener
        ImageView searchBtn = (ImageView) activity.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setVisibility(View.VISIBLE);
                generalTitleView.setVisibility(View.GONE);
            }
        });
    }
}

