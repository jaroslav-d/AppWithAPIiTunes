package com.example.jaroslav.taskfromforasoft.models.item;

import android.graphics.Bitmap;

class ITunesItem {
    private String wrapperType;
    private String collectionType;
    private int artistId;
    private int collectionId;
    private int amgArtistId;
    private String collectionName;

    public int getArtistId() {
        return artistId;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getAmgArtistId() {
        return amgArtistId;
    }

    public String getName(){
        return collectionName;
    }
}