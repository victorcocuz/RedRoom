package com.example.android.redroom;

import static android.R.attr.name;

/**
 * Created by victo on 7/2/2017.
 */

public class VolumeCard {

    private String title;
    private String author;

    public VolumeCard(String title, String author) {
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
