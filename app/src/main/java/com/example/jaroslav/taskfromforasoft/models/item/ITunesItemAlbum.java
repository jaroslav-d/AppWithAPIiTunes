package com.example.jaroslav.taskfromforasoft.models.item;

import android.graphics.Bitmap;

public class ITunesItemAlbum extends ITunesItem {
    private String artworkUrl100;
    private Bitmap photoFromUrl100;

    public String getPhotoUrl() {
        return artworkUrl100;
    }

    public void setPhoto(Bitmap photo) {
        photoFromUrl100 = photo;
    }

    public Bitmap getPhoto() { return photoFromUrl100; }

    public int getAlbumId() { return getCollectionId(); }
}
