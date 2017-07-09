package com.example.android.redroom;
/**
 * Created by victo on 7/2/2017.
 */

public class Book {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getVolumeTitle() {
        return title;
    }

    public String getVolumeAuthor() {
        return author;
    }
}
