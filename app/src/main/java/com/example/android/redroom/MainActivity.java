package com.example.android.redroom;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ViewPager generalViewPager;
    private TextView generalTitleView;
    private EditText searchView;
    private ImageView menuRedRoom, menuBrowse, menuCollection, menuBorrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generalTitleView = (TextView) findViewById(R.id.general_title);
        searchView = (EditText) findViewById(R.id.search_view);

        generalViewPager = (ViewPager) findViewById(R.id.general_view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        generalViewPager.setAdapter(fragmentAdapter);

        //Set Toolbar
        String title = getString(R.string.fragment_red_room);
        SearchToolbar searchToolbar = new SearchToolbar(MainActivity.this, this, title);
        searchToolbar.runToolbar();

        //BottomMenu
        menuRedRoom = (ImageView) findViewById(R.id.menu_red_room);
        menuRedRoom.setImageResource(R.drawable.ic_share_bright_24px);
        menuRedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(0, true);
                generalTitleView.setText(getString(R.string.fragment_red_room));
                generalTitleView.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.INVISIBLE);
                menuRedRoom.setImageResource(R.drawable.ic_share_bright_24px);
                menuBrowse.setImageResource(R.drawable.ic_library_books_dark_24px);
                menuCollection.setImageResource(R.drawable.ic_collections_bookmark_dark_24px);
                menuBorrow.setImageResource(R.drawable.ic_book_dark_24px);
            }
        });

        menuBrowse = (ImageView) findViewById(R.id.menu_browse);
        menuBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(1, true);
                generalTitleView.setText(getString(R.string.fragment_browse));
                generalTitleView.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.INVISIBLE);
                menuRedRoom.setImageResource(R.drawable.ic_share_dark_24px);
                menuBrowse.setImageResource(R.drawable.ic_library_books_bright_24px);
                menuCollection.setImageResource(R.drawable.ic_collections_bookmark_dark_24px);
                menuBorrow.setImageResource(R.drawable.ic_book_dark_24px);
            }
        });

        menuCollection = (ImageView) findViewById(R.id.menu_collection);
        menuCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(2, true);
                generalTitleView.setText(getString(R.string.fragment_collection));
                generalTitleView.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.INVISIBLE);
                menuRedRoom.setImageResource(R.drawable.ic_share_dark_24px);
                menuBrowse.setImageResource(R.drawable.ic_library_books_dark_24px);
                menuCollection.setImageResource(R.drawable.ic_collections_bookmark_bright_24px);
                menuBorrow.setImageResource(R.drawable.ic_book_dark_24px);
            }
        });

        menuBorrow = (ImageView) findViewById(R.id.menu_borrow);
        menuBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(3, true);
                generalTitleView.setText(getString(R.string.fragment_borrow));
                generalTitleView.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.INVISIBLE);
                menuRedRoom.setImageResource(R.drawable.ic_share_dark_24px);
                menuBrowse.setImageResource(R.drawable.ic_library_books_dark_24px);
                menuCollection.setImageResource(R.drawable.ic_collections_bookmark_dark_24px);
                menuBorrow.setImageResource(R.drawable.ic_book_bright_24px);
            }
        });
    }
}
