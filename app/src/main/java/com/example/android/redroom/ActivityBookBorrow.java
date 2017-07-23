package com.example.android.redroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityBookBorrow extends AppCompatActivity {

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_borrow);

        extras = getIntent().getExtras();

        //Set Toolbar
        String title = getString(R.string.book_detail_borrow);
        SearchToolbar searchToolbar = new SearchToolbar(ActivityBookBorrow.this, this, title);
        searchToolbar.runToolbar();

        //Set URL webReaderLink
        TextView textViewBorrow1 = (TextView) findViewById(R.id.book_borrow1);
        TextView textViewBorrow2 = (TextView) findViewById(R.id.book_borrow2);
        TextView textViewBorrow3 = (TextView) findViewById(R.id.book_borrow3);

        goToWebReaderLink(textViewBorrow1);
        goToWebReaderLink(textViewBorrow2);
        goToWebReaderLink(textViewBorrow3);
    }

    private void goToWebReaderLink(TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(extras.getString("webReaderLink")));
                startActivity(intent);
            }
        });
    }
}
