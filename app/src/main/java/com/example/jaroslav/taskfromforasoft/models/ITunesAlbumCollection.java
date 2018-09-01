package com.example.jaroslav.taskfromforasoft.models;

import android.graphics.Bitmap;

public class ITunesAlbumCollection extends ITunesCollection {

    public void setPhotoForAlbum(int numberAlbum, Bitmap bitmap) {
        super.setPhotoFromUrl100(numberAlbum + 1,bitmap);
    }

    public int getAlbumCount() {
        return super.getResultCount() - 1;
    }

    public String getURLPhoto(int numberAlbum) {
        return super.getArtworkUrl100(numberAlbum + 1);
    }

    public String getAlbumName(int numberAlbum) {
        return super.getCollectionName(numberAlbum + 1);
    }

    public Bitmap getAlbumPhoto(int numberAlbum) {
        return super.getPhotoFromUrl100(numberAlbum + 1);
    }
}
