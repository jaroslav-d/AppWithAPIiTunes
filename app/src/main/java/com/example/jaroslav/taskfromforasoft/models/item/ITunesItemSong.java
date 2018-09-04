package com.example.jaroslav.taskfromforasoft.models.item;

import android.graphics.Bitmap;

// This class contains special variables for the list of songs returned from itunes
public class ITunesItemSong extends ITunesItem {
    private String artworkUrl100;
    private Bitmap photoFromUrl100;
    private String trackName;

    public String getPhotoUrl() {
        return artworkUrl100;
    }

    public void setPhoto(Bitmap photo) {
        photoFromUrl100 = photo;
    }

    public Bitmap getPhoto() {
        return photoFromUrl100;
    }

    public int getSongId() { return getCollectionId(); }

    public String getTrackName() {
        return trackName;
    }
}
