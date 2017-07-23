package com.example.android.redroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityBookBuy extends AppCompatActivity {

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_buy);

        extras = getIntent().getExtras();

        //Set Toolbar
        String title = getString(R.string.book_detail_buy);
        SearchToolbar searchToolbar = new SearchToolbar(ActivityBookBuy.this, this, title);
        searchToolbar.runToolbar();

        //Set URL webReaderLink
        TextView textViewBuy = (TextView) findViewById(R.id.book_buy);
        goToWebReaderLink(textViewBuy);
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
