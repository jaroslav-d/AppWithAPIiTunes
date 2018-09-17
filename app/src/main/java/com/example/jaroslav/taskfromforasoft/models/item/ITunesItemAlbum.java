package com.example.jaroslav.taskfromforasoft.models.item;

import android.graphics.Bitmap;

// This class contains special variables for the list of albums returned from itunes
public class ITunesItemAlbum extends ITunesItem {
    private String artworkUrl100;
    private String collectionPrice;
    private String primaryGenreName;

    public String getPhotoUrl() {
        return artworkUrl100;
    }

    public int getAlbumId() { return getCollectionId(); }

    public String getPrice() {
        return collectionPrice;
    }

    public String getGenre() {
        return primaryGenreName;
    }
}
