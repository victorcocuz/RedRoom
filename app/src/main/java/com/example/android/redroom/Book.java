package com.example.android.redroom;

import android.graphics.Bitmap;

/**
 * Created by victo on 7/2/2017.
 */

public class Book {

    private String title;
    private String author;
    private Bitmap image;
    private String previewLink;
    private String webReaderLink;
    private String textSnippet;
    private String description;
    private String ratingAverage;
    private String ratingCount;

    public Book(String title, String author, Bitmap image, String previewLink, String webReaderLink, String textSnippet, String description, String ratingAverage, String ratingCount) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.previewLink = previewLink;
        this.webReaderLink = webReaderLink;
        this.textSnippet = textSnippet;
        this.description = description;
        this.ratingAverage = ratingAverage;
        this.ratingCount = ratingCount;

    }

    public String getBookTitle() {
        return title;
    }

    public String getBookAuthor() {
        return author;
    }

    public Bitmap getBookImage() { return image;}

    public String getBookPreviewLink() {return previewLink;}

    public String getBookWebReaderLink() {return webReaderLink;}

    public String getBookTextSnippet() {return textSnippet;}

    public String getBookDescription() {return description;}

    public String getBookRatingAverage() {return ratingAverage;}

    public String getBookRatingCount () {return ratingCount;}
}
