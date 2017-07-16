package com.example.android.redroom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityBookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Bundle extras = getIntent().getExtras();

        TextView titleView = (TextView) findViewById(R.id.book_detail_title);
        titleView.setText(extras.getString("title"));

        TextView authorView = (TextView) findViewById(R.id.book_detail_author);
        authorView.setText(extras.getString("author"));

        ImageView imageView = (ImageView) findViewById(R.id.book_detail_image);
        Bitmap bitmap = extras.getParcelable("image");
        imageView.setImageBitmap(bitmap);

        //Set textSnippet
        TextView textSnippetView = (TextView) findViewById(R.id.book_detail_textSnippet);
        if (extras.getString("textSnippet" ) != null){
            textSnippetView.setText(extras.getString("textSnippet"));
        }
        else {
           textSnippetView.setText(R.string.book_no_text_snippet);
        }

        //Set description
        TextView descriptionView = (TextView) findViewById(R.id.book_detail_description);
        if (extras.getString("textDescription") != null) {
            descriptionView.setText(extras.getString("description"));
        } else {
            descriptionView.setText(R.string.book_no_description);
        }
    }
}
