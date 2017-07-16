package com.example.android.redroom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import static android.R.attr.rating;

public class ActivityBookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        final Bundle extras = getIntent().getExtras();

        TextView titleView = (TextView) findViewById(R.id.book_detail_title);
        titleView.setText(extras.getString("title"));

        TextView authorView = (TextView) findViewById(R.id.book_detail_author);
        authorView.setText(extras.getString("author"));

        ImageView imageView = (ImageView) findViewById(R.id.book_detail_image);
        Bitmap bitmap = extras.getParcelable("image");
        imageView.setImageBitmap(bitmap);

        //Set ratingBar
        RatingBar ratingBar = (RatingBar) findViewById(R.id.book_detail_rating_bar);
        String ratingAverage = extras.getString("ratingAverage");
        if (ratingAverage != null) {
            float rating = Float.valueOf(ratingAverage);
            ratingBar.setRating(rating);
        } else {
            ratingBar.setRating(0f);
        }

        //Set ratingCount
        TextView ratingCountView = (TextView) findViewById(R.id.book_detail_rating_count);
        String ratingCount = extras.getString("ratingCount");
        if (ratingCount != null) {
            ratingCountView.setText(ratingCount);
        }
        else {
            ratingCountView.setText("0");
        }

        //Set textSnippet
        TextView textSnippetView = (TextView) findViewById(R.id.book_detail_textSnippet);
        if (extras.getString("textSnippet") != null) {
            textSnippetView.setText(extras.getString("textSnippet"));
        } else {
            textSnippetView.setText(R.string.book_no_text_snippet);
        }

        //Set description
        TextView descriptionView = (TextView) findViewById(R.id.book_detail_description);
        if (extras.getString("description") != null) {
            descriptionView.setText(R.string.book_description);
            descriptionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView descriptionView = (TextView) findViewById(R.id.book_detail_description);
                    descriptionView.setText(extras.getString("description"));
                }
            });
        } else {
            descriptionView.setText(R.string.book_no_description);
        }
    }
}
