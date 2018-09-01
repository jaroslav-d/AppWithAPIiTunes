package com.example.jaroslav.taskfromforasoft.models;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ITunesCollection {
    private int resultCount;
    private ArrayList<ITunesItem> results;

    protected class ITunesItem {
        String wrapperType;
        String collectionType;
        int artistId;
        int collectionId;
        int amgArtistId;
        String artworkUrl100;
        Bitmap photoFromUrl100;
        String collectionName;

        protected ITunesItem() {}
    }

    protected int getResultCount() {
        return resultCount;
    }

    protected ArrayList<ITunesItem> getResults() {
        return results;
    }

    protected int getArtistID() {
        if (resultCount == 1) {
            return results.get(0).artistId;
        } else {
            return Integer.parseInt(null);
        }
    }

    protected int getArtistID(int numberItem) {
        return results.get(numberItem).artistId;
    }

    protected String getArtworkUrl100(int numberItem) {
        return results.get(numberItem).artworkUrl100;
    }

    protected String getCollectionName(int numberItem) {
        return results.get(numberItem).collectionName;
    }

    protected void setPhotoFromUrl100(int numberItem, Bitmap bitmap){
        results.get(numberItem).photoFromUrl100 = bitmap;
    }

    protected Bitmap getPhotoFromUrl100(int numberItem) {
        return results.get(numberItem).photoFromUrl100;
    }
}